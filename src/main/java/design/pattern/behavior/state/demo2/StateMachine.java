package design.pattern.behavior.state.demo2;

/**
 * 基于「枚举」的状态机实现
 */
public class StateMachine {
    private StateEnum state;

    public StateMachine(StateEnum state) {
        this.state = state;
    }

    public void execute(EventEnum event) {
        event.trigger(this, this.state);
    }


}
