package design.pattern.build.factory.factory2;

import design.pattern.build.factory.RuleConfigParserFactory;
import design.pattern.build.factory.impl.JsonRuleConfigParserFactory;
import design.pattern.build.factory.impl.PropertiesRuleConfigParserFactory;
import design.pattern.build.factory.impl.XmlRuleConfigParserFactory;
import design.pattern.utils.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * 工厂类只包含方法，不包含成员变量，完全可以复用，不需要每次都创建新的工厂类对象
 */
public class RuleConfigParserFactoryMap {
    private static final Map<String, RuleConfigParserFactory> cachedFactories = new HashMap<>();
    static {
        cachedFactories.put("json", new JsonRuleConfigParserFactory());
        cachedFactories.put("xml", new XmlRuleConfigParserFactory());
        cachedFactories.put("properties", new PropertiesRuleConfigParserFactory());
    }

    public static RuleConfigParserFactory getParserFactory(String type) {
        if (StringUtils.isBlank(type)) {
            return null;
        }

        RuleConfigParserFactory parserFactory = cachedFactories.get(type.toLowerCase()); return parserFactory;
    }
}
