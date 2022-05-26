package design.pattern.structure.bridge;

import java.util.List;

/**
 * 原始告警版本
 *
 * 问题：存在大量的 if-else 判断，而且 if-else 分支中的代码逻辑都比较复杂，发送通知的所有逻辑都扎堆在 Notification 类中。
 */
public class NotificationV0 {
    private List<String> emailAddresses;
    private List<String> telephones;
    private List<String> wechatIds;
    public NotificationV0() {}

    public void setEmailAddress(List<String> emailAddress) {
        this.emailAddresses = emailAddress;
    }

    public void setTelephones(List<String> telephones) {
        this.telephones = telephones;
    }

    public void setWechatIds(List<String> wechatIds) {
        this.wechatIds = wechatIds;
    }

    public void notify(NotificationEmergencyLevel level, String message) {
        if (level.equals(NotificationEmergencyLevel.SEVERE)) {
            //...自动语音电话
        } else if (level.equals(NotificationEmergencyLevel.URGENCY)) {
            //...发微信
        } else if (level.equals(NotificationEmergencyLevel.NORMAL)) {
            //...发邮件
        } else if (level.equals(NotificationEmergencyLevel.TRIVIAL)) {
            //...发邮件
        }
    }
}
