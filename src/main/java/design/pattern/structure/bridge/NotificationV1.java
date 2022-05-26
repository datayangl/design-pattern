package design.pattern.structure.bridge;

/**
 * 引入桥接模式版本
 *
 * <h> Bridge Design Pattern: 桥接模式 </h>
 *
 * <p>
 *     桥接模式定义：Decouple an abstraction from its implementation so that the two can vary independently. <<设计模式>> by GOF
 *
 *     针对 {@link NotificationV0 } 中的问题，我们将不同渠道的发送逻辑剥离出来，形成独立的消息发送类（MsgSender 相关类）。
 *     其中，Notification 类相当于抽象，MsgSender 类相当于实现，两者可以独立开发，通过组合关系（也就是桥梁）任意组合在一起。所谓任意组合的意思就是，
 *     不同紧急程度的消息和发送渠道之间的对应关系，不是在代码中固定写死的，我们可以动态地去指定（比如，通过读取配置来获取对应关系）。
 * </p>
 */
public abstract class NotificationV1 {
    protected MsgSender msgSender;

    public NotificationV1(MsgSender msgSender) {
        this.msgSender = msgSender;
    }

    public abstract void notify(String message);
}
