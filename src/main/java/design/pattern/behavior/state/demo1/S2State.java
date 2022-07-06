package design.pattern.behavior.state.demo1;

/**
 * S2状态类
 */
public class S2State extends State{
    private static final S2State instance = new S2State();

    public static S2State getInstance() {
        return instance;
    }

    //S2状态只关心B、C事件
    private S2State() {
        name = "S2";
        events.put(Event.B, sm -> {
            sm.score = 0;
        });
        events.put(Event.C, sm -> {
            sm.score--;
            sm.currentState = S1State.getInstance();
        });
    }
}
