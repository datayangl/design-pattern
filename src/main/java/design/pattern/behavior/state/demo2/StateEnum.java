package design.pattern.behavior.state.demo2;

public enum StateEnum {
    State1 {
        @Override
        public void doEvent1(StateMachine stateMachine) {
            // set next state
            // do something else
        }

        @Override
        public void doEvent2(StateMachine stateMachine) {
            // set next state
            // do something else
        }
        // ......
    },
    // .......
    ;

    public abstract void doEvent1(StateMachine stateMachine);
    public abstract void doEvent2(StateMachine stateMachine);

}
