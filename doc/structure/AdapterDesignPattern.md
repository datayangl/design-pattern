- [适配器模式 Adapter Design Pattern](#适配器模式-adapter-design-pattern)
- [适配器模式的实现方式](#适配器模式的实现方式)
- [适配器模式适用场景](#适配器模式适用场景)
- [适配器模式的应用 - 日志](#适配器模式的应用---日志)
- [代理、桥接、装饰器、适配器 4 种设计模式的区别](#代理桥接装饰器适配器-4-种设计模式的区别)

# 适配器模式 Adapter Design Pattern

# 适配器模式的实现方式
配器模式有两种实现方式：类适配器和对象适配器。其中，类适配器使用继承关系来实现，对象适配器使用组合关系来实现。
```java
// 类适配器: 基于继承
public interface ITarget { 
    void f1(); 
    void f2(); 
    void fc();
}

public class Adaptee { 
    public void fa() { 
        //... 
    } 
    
    public void fb() { 
        //... 
    } 
    
    public void fc() { 
        //... 
    }
}
    
public class Adaptor extends Adaptee implements ITarget { 
    public void f1() { 
        super.fa(); 
    }
    public void f2() { 
        //...重新实现f2()... 
    } 
    // 这里fc()不需要实现，直接继承自Adaptee，这是跟对象适配器最大的不同点
}

// 对象适配器：基于组合
public interface ITarget {
    void f1();
    void f2();
    void fc();
}

public class Adaptee {
    public void fa() {
        //... 
    }

    public void fb() {
        //... 
    }
    
    public void fc() { 
        //... 
    }
}

public class Adaptor implements ITarget {
    private Adaptee adaptee;

    public Adaptor(Adaptee adaptee) {
        this.adaptee = adaptee;
    }

    public void f1() {
        adaptee.fa(); 
        //委托给Adaptee 
    } 
    public void f2() {
        //...重新实现f2()... 
    }
    public void fc() {
        adaptee.fc();
    }
}
```
# 适配器模式适用场景
1. 封装有缺陷的接口设计
假设我们依赖的外部系统在接口设计方面有缺陷（比如包含大量静态方法），引入之后会影响到我们自身代码的可测试性。为了隔离设计上的缺陷，我们希望对外部系统提供的接口进行二次封装，抽象出更好的接口设计，这个时候可以使用适配器模式。
```java
//这个类来自外部sdk，我们无权修改它的代码
public class CD {
  //...
  public static void staticFunction1() { 
      //... 
  }
  
  public void uglyNamingFunction2() { 
      //... 
  }

  public void tooManyParamsFunction3(int... paramA) { 
      //...
  }
    
  public void lowPerformanceFunction4() { 
      //... 
  }
}

// 使用适配器模式进行重构
public interface ITarget {
  void function1();
  void function2();
  void fucntion3(ParamsWrapperDefinition paramsWrapper);
  void function4();
}
// 注意：适配器类的命名不一定非得末尾带Adaptor
public class CDAdaptor extends CD implements ITarget {
  //...
  public void function1() {
     super.staticFunction1();
  }
  
  public void function2() {
    super.uglyNamingFucntion2();
  }
  
  public void function3(ParamsWrapperDefinition paramsWrapper) {
     super.tooManyParamsFunction3(paramsWrapper.getParamA());
  }
  
  public void function4() {
    //...reimplement it...
  }
}
```
2. 统一多个类的接口设计
   某个功能的实现依赖多个外部系统（或者说类）。通过适配器模式，将它们的接口适配为统一的接口定义，然后我们就可以使用多态的特性来复用代码逻辑。
   假设我们的系统要对用户输入的文本内容做敏感词过滤，为了提高过滤的召回率，我们引入了多款第三方敏感词过滤系统，依次对用户输入的内容进行过滤，过滤掉尽可能多的敏感词。但是，每个系统提供的过滤接口都是不同的。这就意味着我们没法复用一套逻辑来调用各个系统。这个时候，我们就可以使用适配器模式，将所有系统的接口适配为统一的接口定义，这样我们可以复用调用敏感词过滤的代码.
```java

public class ASensitiveWordsFilter { // A敏感词过滤系统提供的接口
  //text是原始文本，函数输出用***替换敏感词之后的文本
  public String filterSexyWords(String text) {
    // ...
  }
  
  public String filterPoliticalWords(String text) {
    // ...
  } 
}

public class BSensitiveWordsFilter  { // B敏感词过滤系统提供的接口
  public String filter(String text) {
    //...
  }
}

public class CSensitiveWordsFilter { // C敏感词过滤系统提供的接口
  public String filter(String text, String mask) {
    //...
  }
}

// 未使用适配器模式之前的代码：代码的可测试性、扩展性不好
public class RiskManagement {
  private ASensitiveWordsFilter aFilter = new ASensitiveWordsFilter();
  private BSensitiveWordsFilter bFilter = new BSensitiveWordsFilter();
  private CSensitiveWordsFilter cFilter = new CSensitiveWordsFilter();
  
  public String filterSensitiveWords(String text) {
    String maskedText = aFilter.filterSexyWords(text);
    maskedText = aFilter.filterPoliticalWords(maskedText);
    maskedText = bFilter.filter(maskedText);
    maskedText = cFilter.filter(maskedText, "***");
    return maskedText;
  }
}

// 使用适配器模式进行改造
public interface ISensitiveWordsFilter { // 统一接口定义
  String filter(String text);
}

public class ASensitiveWordsFilterAdaptor implements ISensitiveWordsFilter {
  private ASensitiveWordsFilter aFilter;
  public String filter(String text) {
    String maskedText = aFilter.filterSexyWords(text);
    maskedText = aFilter.filterPoliticalWords(maskedText);
    return maskedText;
  }
}
//...省略BSensitiveWordsFilterAdaptor、CSensitiveWordsFilterAdaptor...

// 扩展性更好，更加符合开闭原则，如果添加一个新的敏感词过滤系统，
// 这个类完全不需要改动；而且基于接口而非实现编程，代码的可测试性更好。
public class RiskManagement { 
  private List<ISensitiveWordsFilter> filters = new ArrayList<>();
 
  public void addSensitiveWordsFilter(ISensitiveWordsFilter filter) {
    filters.add(filter);
  }
  
  public String filterSensitiveWords(String text) {
    String maskedText = text;
    for (ISensitiveWordsFilter filter : filters) {
      maskedText = filter.filter(maskedText);
    }
    return maskedText;
  }
}
```

3. 替换依赖的外部系统
```java
// 外部系统A
public interface IA {
  void fa();
}
public class A implements IA {
  public void fa() { 
      //... 
  }
}

// 在我们的项目中，外部系统A的使用示例
public class Demo {
  private IA a;
  public Demo(IA a) {
    this.a = a;
  }
}

// Demo d = new Demo(new A());

// 将外部系统A替换成外部系统B
public class BAdaptor implemnts IA {
  private B b;
  public BAdaptor(B b) {
    this.b= b;
  }
  public void fa() {
    //...
    b.fb();
  }
}
// 借助BAdaptor，Demo的代码中，调用IA接口的地方都无需改动，
// 只需要将BAdaptor如下注入到Demo即可。
//Demo d = new Demo(new BAdaptor(new B()));
```
4. 兼容老版本接口
   在做版本升级的时候，对于一些要废弃的接口，我们不直接将其删除，而是暂时保留，并且标注为 deprecated，并将内部实现逻辑委托为新的接口实现。这样做的好处是，让使用它的项目有个过渡期，而不是强制进行代码修改。这也可以粗略地看作适配器模式的一个应用场景。
   JDK1.0 中包含一个遍历集合容器的类 Enumeration。JDK2.0 对这个类进行了重构，将它改名为 Iterator 类，并且对它的代码实现做了优化。但是考虑到如果将 Enumeration 直接从 JDK2.0 中删除，那使用 JDK1.0 的项目如果切换到 JDK2.0，代码就会编译不通过。为了避免这种情况的发生，我们必须把项目中所有使用到 Enumeration 的地方，都修改为使用 Iterator 才行。
```java
public class Collections {
  public static Emueration emumeration(final Collection c) {
    return new Enumeration() {
      Iterator i = c.iterator();
      
      public boolean hasMoreElments() {
        return i.hashNext();
      }
      
      public Object nextElement() {
        return i.next():
      }
    }
  }
}
```
5. 适配不同格式的数据
   比如，Java 中的 Arrays.asList() 也可以看作一种数据适配器，将数组类型的数据转化为集合容器类型
```java
List<String> stooges = Arrays.asList("Larry", "Moe", "Curly");
```

# 适配器模式的应用 - 日志
Slf4j 日志框架定义好了统一的日志打印规范和接口。但是，Slf4j 只定义了接口并没有提供具体的实现，需要配合其他日志框架（log4j、logback...）。

Slf4j 的出现晚于 JUL、JCL、log4j 等日志框架，所以，这些日志框架也不可能牺牲掉版本兼容性，将接口改造成符合 Slf4j 接口规范。Slf4j 也事先考虑到了这个问题，所以，它不仅仅提供了统一的接口定义，还提供了针对不同日志框架的适配器。对不同日志框架的接口进行二次封装，适配成统一的 Slf4j 接口定义。

示例代码如下：
```java
// slf4j统一的接口定义
package org.slf4j;
public interface Logger {
  public boolean isTraceEnabled();
  public void trace(String msg);
  public void trace(String format, Object arg);
  public void trace(String format, Object arg1, Object arg2);
  public void trace(String format, Object[] argArray);
  public void trace(String msg, Throwable t);
 
  public boolean isDebugEnabled();
  public void debug(String msg);
  public void debug(String format, Object arg);
  public void debug(String format, Object arg1, Object arg2)
  public void debug(String format, Object[] argArray)
  public void debug(String msg, Throwable t);

  //...省略info、warn、error等一堆接口
}

// log4j日志框架的适配器
// Log4jLoggerAdapter实现了LocationAwareLogger接口，
// 其中LocationAwareLogger继承自Logger接口，
// 也就相当于Log4jLoggerAdapter实现了Logger接口。
package org.slf4j.impl;
public final class Log4jLoggerAdapter extends MarkerIgnoringBase
  implements LocationAwareLogger, Serializable {
  final transient org.apache.log4j.Logger logger; // log4j
 
  public boolean isDebugEnabled() {
    return logger.isDebugEnabled();
  }
 
  public void debug(String msg) {
    logger.log(FQCN, Level.DEBUG, msg, null);
  }
 
  public void debug(String format, Object arg) {
    if (logger.isDebugEnabled()) {
      FormattingTuple ft = MessageFormatter.format(format, arg);
      logger.log(FQCN, Level.DEBUG, ft.getMessage(), ft.getThrowable());
    }
  }
 
  public void debug(String format, Object arg1, Object arg2) {
    if (logger.isDebugEnabled()) {
      FormattingTuple ft = MessageFormatter.format(format, arg1, arg2);
      logger.log(FQCN, Level.DEBUG, ft.getMessage(), ft.getThrowable());
    }
  }
 
  public void debug(String format, Object[] argArray) {
    if (logger.isDebugEnabled()) {
      FormattingTuple ft = MessageFormatter.arrayFormat(format, argArray);
      logger.log(FQCN, Level.DEBUG, ft.getMessage(), ft.getThrowable());
    }
  }
 
  public void debug(String msg, Throwable t) {
    logger.log(FQCN, Level.DEBUG, msg, t);
  }
  //...省略一堆接口的实现...
}
```

# 代理、桥接、装饰器、适配器 4 种设计模式的区别
* 代理模式：代理模式在不改变原始类接口的条件下，为原始类定义一个代理类，主要目的是控制访问，而非加强功能，这是它跟装饰器模式最大的不同。
* 桥接模式：桥接模式的目的是将接口部分和实现部分分离，从而让它们可以较为容易、也相对独立地加以改变。
* 装饰器模式：装饰者模式在不改变原始类接口的情况下，对原始类功能进行增强，并且支持多个装饰器的嵌套使用。
* 适配器模式：适配器模式是一种事后的补救策略。适配器提供跟原始类不同的接口，而代理模式、装饰器模式提供的都是跟原始类相同的接口。

再简化可以理解为：
装饰模式是“新增行为”，代理模式是“控制访问行为”，适配器模式是"转换行为"，桥接模式是一种"简化行为"。

