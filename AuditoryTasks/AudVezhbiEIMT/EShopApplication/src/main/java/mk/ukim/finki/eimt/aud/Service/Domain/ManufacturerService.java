package mk.ukim.finki.eimt.aud.Service.Domain;

import mk.ukim.finki.eimt.aud.Model.Entity.Manufacturer;

import java.util.List;
import java.util.Optional;

public interface ManufacturerService {

    List<Manufacturer> findAll();

    Optional<Manufacturer> findByID(Long ID);

    Optional<Manufacturer> create(Manufacturer manufacturer);

    Optional<Manufacturer> update(Long ID, Manufacturer manufacturer);

    void deleteByID(Long ID);
}