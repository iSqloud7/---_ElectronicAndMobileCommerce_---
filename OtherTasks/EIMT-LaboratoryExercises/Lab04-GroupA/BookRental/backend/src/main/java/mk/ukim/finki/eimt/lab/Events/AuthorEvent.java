package mk.ukim.finki.eimt.lab.Events;

import org.springframework.context.ApplicationEvent;

public class AuthorEvent extends ApplicationEvent {

    public AuthorEvent(Object source) {
        super(source);
    }
}
