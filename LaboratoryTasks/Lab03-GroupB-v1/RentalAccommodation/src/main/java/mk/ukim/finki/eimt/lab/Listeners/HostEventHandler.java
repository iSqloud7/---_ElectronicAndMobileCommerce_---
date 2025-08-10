package mk.ukim.finki.eimt.lab.Listeners;

import mk.ukim.finki.eimt.lab.Events.HostEvent;
import mk.ukim.finki.eimt.lab.Service.Domain.HostDomainService;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class HostEventHandler {

    private final HostDomainService hostService;

    public HostEventHandler(HostDomainService hostService) {
        this.hostService = hostService;
    }

    @EventListener(HostEvent.class)
    public void refreshMaterializedView() {
        this.hostService.refreshMaterializedView();
    }
}
