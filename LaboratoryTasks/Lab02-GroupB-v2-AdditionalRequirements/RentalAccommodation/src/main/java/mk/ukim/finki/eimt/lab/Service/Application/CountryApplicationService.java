package mk.ukim.finki.eimt.lab.Service.Application;

import mk.ukim.finki.eimt.lab.Model.Domain.Country;

import java.util.List;

public interface CountryApplicationService {

    List<Country> findAll();
}
