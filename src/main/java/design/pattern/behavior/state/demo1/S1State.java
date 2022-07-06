package design.pattern.behavior.state.demo1;

import java.util.HashMap;
import java.util.Map;

/**
 * S1 状态类
 */
public class S1State extends State{
    private static final S1State instance = new S1State();

    Map<Event, IEventCallback> events = new HashMap<>();

    public static S1State getInstance() {
        return instance;
    }

    //S1状态只关心A、B事件
    private S1State() {
        name = "S1";
        events.put(Event.A, sm -> {
            sm.currentState = S2State.getInstance();
            sm.score++;
        });
        events.put(Event.B, sm -> {
            sm.score *= 10;
        });
    }
}
