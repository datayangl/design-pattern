package design.pattern.build.factory.factory1;

import design.pattern.build.factory.RuleConfigParser;
import design.pattern.build.factory.impl.JsonRuleConfigParser;
import design.pattern.build.factory.impl.PropertiesRuleConfigParser;
import design.pattern.build.factory.impl.XmlRuleConfigParser;
import design.pattern.utils.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * 简单工厂模式
 * 需求：基于配置文件格式生成对应的解析器 {@link RuleConfigParser}
 * 适用场景：创建实例过程比较简单
 */
public class ParserSimpleFactory {
    private static final Map<String, RuleConfigParser> cachedParsers = new HashMap<>();
    static {
        cachedParsers.put("json", new JsonRuleConfigParser());
        cachedParsers.put("xml", new XmlRuleConfigParser());
        cachedParsers.put("properties", new PropertiesRuleConfigParser());
    }

    /**
     * 创建 RuleConfigParser 方式1：预缓存实例，获取时调用。
     * 这里的实现其实调用的都是单例，如果需要每次调用产生新的实例，可以存 class 对象，在每次调用时实例化。
     * @param configFormat
     * @return
     */
    public RuleConfigParser createParser1(String configFormat) {
        if (StringUtils.isBlank(configFormat)) {
            return null;
        }

        RuleConfigParser parser = cachedParsers.get(configFormat.toLowerCase());
        return parser;
    }

    /**
     * 创建 RuleConfigParser 方式2：if 分支判断并实例化。
     * 相比第一种方式而言会有比较多的 if 分支判断，在分支较少且变动不频繁的情况下是可以接受的。
     * @param configFormat
     * @return
     */
    public RuleConfigParser createParser2(String configFormat) {
        RuleConfigParser parser = null;
        if ("json".equalsIgnoreCase(configFormat)) {
            parser = new JsonRuleConfigParser();
        } else if ("xml".equalsIgnoreCase(configFormat)) {
            parser = new XmlRuleConfigParser();
        } else if ("properties".equalsIgnoreCase(configFormat)) {
            parser = new PropertiesRuleConfigParser();
        }

        return parser;
    }

}
