package mk.ukim.finki.eimt.lab.Events;

import org.springframework.context.ApplicationEvent;

public class HostEvent extends ApplicationEvent {

    public HostEvent(Object source) {
        super(source);
    }
}
