package mk.ukim.finki.eimt.lab.Web.RestController;

import mk.ukim.finki.eimt.lab.Model.Host;
import mk.ukim.finki.eimt.lab.Service.HostService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/hosts")
public class HostRestController {

    private final HostService hostService;

    public HostRestController(HostService hostService) {
        this.hostService = hostService;
    }

    @GetMapping
    public ResponseEntity<List<Host>> findAll() {
        return ResponseEntity.ok(this.hostService.findAllHosts());
    }
}
