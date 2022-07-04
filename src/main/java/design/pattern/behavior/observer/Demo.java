package design.pattern.behavior.observer;

public class Demo {
    public static void main(String[] args) {
        ConcreteSubject subject = new ConcreteSubject();
        subject.registerObserver(new ConcreteObserver1());
        subject.registerObserver(new ConcreteObserver2());
        subject.notifyObservers(new Message());
    }
}
