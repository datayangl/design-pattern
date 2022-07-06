package design.pattern.behavior.state.demo1;

import java.util.HashMap;
import java.util.Map;

/**
 * 状态抽象类
 */
public abstract class State {
    protected String name;

    protected Map<Event, IEventCallback> events = new HashMap<>();

    public final void triggerEvent(Event e, StateMachine sm) {
        IEventCallback call = events.get(e);
        if (call != null) {
            call.onEvent(sm);
        }
    }
}
