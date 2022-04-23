package design.pattern.build.factory.impl;

import design.pattern.build.factory.RuleConfigParser;
import design.pattern.build.factory.RuleConfigParserFactory;

public class PropertiesRuleConfigParserFactory implements RuleConfigParserFactory {
    @Override
    public RuleConfigParser createParser() {
        return new PropertiesRuleConfigParser();
    }
}
