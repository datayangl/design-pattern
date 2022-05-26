package design.pattern.structure.bridge.impl;

import design.pattern.structure.bridge.MsgSender;
import design.pattern.structure.bridge.NotificationV1;

public class SevereNotification extends NotificationV1 {
    public SevereNotification(MsgSender msgSender) {
        super(msgSender);
    }

    @Override  public void notify(String message) {
        msgSender.send(message);
    }
}

