package mk.ukim.finki.eimt.lab.Web.RestController;

import mk.ukim.finki.eimt.lab.Model.Country;
import mk.ukim.finki.eimt.lab.Service.CountryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/countries")
public class CountryRestController {

    private final CountryService countryService;

    public CountryRestController(CountryService countryService) {
        this.countryService = countryService;
    }


    @GetMapping
    public ResponseEntity<List<Country>> findAll() {
        return ResponseEntity.ok(this.countryService.findAll());
    }
}
