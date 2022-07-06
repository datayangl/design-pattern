package design.pattern.behavior.state.demo1;

/**
 * 基于「状态模式」的状态机实现
 */
public class StateMachine {
    int score;
    State currentState;

    public StateMachine() {
        score = 0;
        currentState = S1State.getInstance();
    }

    public void triggerA() {
        currentState.triggerEvent(Event.A, this);
    }

    public void triggerB() {
        currentState.triggerEvent(Event.B, this);
    }

    public void triggerC() {
        currentState.triggerEvent(Event.C, this);
    }
}
