package mk.ukim.finki.eimt.lab.Jobs;

import mk.ukim.finki.eimt.lab.Service.Domain.BookDomainService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTasks {

    private final BookDomainService bookDomainService;

    public ScheduledTasks(BookDomainService bookDomainService) {
        this.bookDomainService = bookDomainService;
    }

    @Scheduled(cron = "0 * * * * *")
    public void refreshMaterializedView() {
        this.bookDomainService.refreshMaterializedView();
    }
}
