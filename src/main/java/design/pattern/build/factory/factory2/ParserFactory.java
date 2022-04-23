package design.pattern.build.factory.factory2;

import design.pattern.build.factory.RuleConfigParser;
import design.pattern.build.factory.RuleConfigParserFactory;
import design.pattern.build.factory.impl.*;
import design.pattern.utils.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * 工厂模式
 * 需求：基于配置文件格式生成对应的解析器 {@link RuleConfigParser}
 *
 * 相比简单工厂模式，这里其实又嵌套了一层工厂 {@RuleConfigParserFactory}，可以理解为 简单工厂模式 + 工厂。
 * 适合场景：如果创建 {@RuleConfigParser} 过程比较复杂，可以将实例化的工作交给了对应的工厂类 {@RuleConfigParserFactory}，
 * 这样 {@ParserFactory} 可以和复杂的实例化过程解耦。
 *
 * 核心思想：创建和使用分离。
 */
public class ParserFactory {
    private static final Map<String, RuleConfigParserFactory> cachedParsers = new HashMap<>();
    static {
        cachedParsers.put("json", new JsonRuleConfigParserFactory());
        cachedParsers.put("xml", new XmlRuleConfigParserFactory());
        cachedParsers.put("properties", new PropertiesRuleConfigParserFactory());
    }

    /**
     * RuleConfigParser 实例化：相比简单工厂模式，创建 RuleConfigParser 的工作交给了对应的工厂
     * @param configFormat
     * @return
     */
    public RuleConfigParser createParser(String configFormat) {
        if (StringUtils.isBlank(configFormat)) {
            return null;
        }

        RuleConfigParserFactory parserFactory = cachedParsers.get(configFormat.toLowerCase());
        return parserFactory.createParser();
    }
}
