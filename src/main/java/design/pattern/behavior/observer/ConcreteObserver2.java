package design.pattern.behavior.observer;

public class ConcreteObserver2 implements Observer{
    @Override public void update(Message message) {
        //TODO: 获取消息通知，执行自己的逻辑...
        System.out.println("ConcreteObserverTwo is notified.");
    }
}
