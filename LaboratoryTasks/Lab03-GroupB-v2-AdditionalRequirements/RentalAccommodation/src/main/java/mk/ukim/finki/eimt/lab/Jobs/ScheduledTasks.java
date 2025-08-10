package mk.ukim.finki.eimt.lab.Jobs;

import mk.ukim.finki.eimt.lab.Service.Domain.BookingDomainService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTasks {

    private final BookingDomainService bookingService;

    public ScheduledTasks(BookingDomainService bookingService) {
        this.bookingService = bookingService;
    }

    @Scheduled(cron = "0 * * * * *") // "0 0 12 * * *" every day at 12 pm
    public void refreshMaterializedView(){
        this.bookingService.refreshMaterializedView();
    }
}
