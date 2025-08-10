package mk.ukim.finki.eimt.lab.Listeners;

import mk.ukim.finki.eimt.lab.Events.AuthorEvent;
import mk.ukim.finki.eimt.lab.Service.Domain.AuthorDomainService;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class AuthorEventListener {

    private final AuthorDomainService authorDomainService;

    public AuthorEventListener(AuthorDomainService authorDomainService) {
        this.authorDomainService = authorDomainService;
    }

    @EventListener(AuthorEvent.class)
    public void refreshMaterializedView(AuthorEvent authorEvent) {
        this.authorDomainService.refreshMaterializedView();
    }

}
