package design.pattern.build.factory.factory2;

import design.pattern.build.factory.RuleConfig;
import design.pattern.build.factory.RuleConfigParser;

public class RuleConfigSource {
    public RuleConfig load(String ruleConfigFilePath) {
        String ruleConfigFileExtension = getFileExtension(ruleConfigFilePath);

        design.pattern.build.factory.RuleConfigParserFactory parserFactory = RuleConfigParserFactoryMap.getParserFactory(ruleConfigFileExtension);
        if (parserFactory == null) {
            throw new IllegalStateException("Rule config file format is not supported: " + ruleConfigFilePath);
        }
        RuleConfigParser parser = parserFactory.createParser();
        //从ruleConfigFilePath文件中读取配置文本到configText中
        RuleConfig ruleConfig = parser.parse(ruleConfigFilePath);
        return ruleConfig;
    }

    private String getFileExtension(String filePath) {
        //...解析文件名获取扩展名，比如rule.json，返回json
        // for example, return "json";
        return "json";
    }
}
