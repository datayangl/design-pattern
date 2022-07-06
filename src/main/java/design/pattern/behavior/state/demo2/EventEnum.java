package design.pattern.behavior.state.demo2;

public enum EventEnum {
    Event1 {
        @Override
        public void trigger(StateMachine stateMachine, StateEnum state) {
            state.doEvent1(stateMachine);
        }
    },
    Event2 {
        @Override
        public void trigger(StateMachine stateMachine, StateEnum state) {
            state.doEvent2(stateMachine);
        }
    },
    // ......
    ;

    public abstract void trigger(StateMachine stateMachine, StateEnum state);
}
