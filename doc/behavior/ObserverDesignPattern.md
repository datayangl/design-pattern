
- [观察者模式 Observer Design Pattern](#观察者模式-observer-design-pattern)
- [观察者模式概述](#观察者模式概述)
- [观察者模式作用](#观察者模式作用)
- [模版](#模版)
- [异步非阻塞 EventBus 框架](#异步非阻塞-eventbus-框架)

# 观察者模式 Observer Design Pattern

# 观察者模式概述
> Define a one-to-many dependency between objects so that when one object changes state, all its dependents are notified and updated automatically.
> 翻译成中文：在对象之间定义一个一对多的依赖，当一个对象状态改变的时候，所有依赖的对象都会自动收到通知。

# 观察者模式作用
设计模式核心是解耦。创建型模式是将创建和使用代码解耦，结构型模式是将不同功能代码解耦，行为型模式是将不同的行为代码解耦，而观察者模式是将观察者和被观察者代码解耦。

# 模版
观察者的模版代码如下：
```java
public interface Subject {
    void registerObserver(Observer observer);
    void removeObserver(Observer observer);
    void notifyObservers(Message message);
}

public interface Observer {
    void update(Message message);
}

public class ConcreteSubject implements Subject {
    private List<Observer> observers = new ArrayList<Observer>();

    @Override
    public void registerObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers(Message message) {
        for (Observer observer : observers) {
            observer.update(message);
        }
    }

}

public class ConcreteObserverOne implements Observer {
    @Override
    public void update(Message message) {
        //TODO: 获取消息通知，执行自己的逻辑...
        System.out.println("ConcreteObserverOne is notified.");
    }
}

public class ConcreteObserverTwo implements Observer {
    @Override
    public void update(Message message) {
        //TODO: 获取消息通知，执行自己的逻辑...
        System.out.println("ConcreteObserverTwo is notified.");
    }
}

public class Demo {
    public static void main(String[] args) {
        ConcreteSubject subject = new ConcreteSubject();
        subject.registerObserver(new ConcreteObserverOne());
        subject.registerObserver(new ConcreteObserverTwo());
        subject.notifyObservers(new Message());
    }
}
```

# 异步非阻塞 EventBus 框架

[代码](https://github.com/datayangl/EventBus)
- [观察者模式 Observer Design Pattern](#观察者模式-observer-design-pattern)
- [观察者模式概述](#观察者模式概述)
- [观察者模式作用](#观察者模式作用)
- [模版](#模版)
- [异步非阻塞 EventBus 框架](#异步非阻塞-eventbus-框架)
