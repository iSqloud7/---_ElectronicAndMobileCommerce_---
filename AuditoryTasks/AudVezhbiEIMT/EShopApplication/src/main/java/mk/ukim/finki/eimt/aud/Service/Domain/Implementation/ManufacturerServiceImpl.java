package mk.ukim.finki.eimt.aud.Service.Domain.Implementation;

import mk.ukim.finki.eimt.aud.Model.Entity.Manufacturer;
import mk.ukim.finki.eimt.aud.Repository.ManufacturerRepository;
import mk.ukim.finki.eimt.aud.Service.Domain.ManufacturerService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ManufacturerServiceImpl implements ManufacturerService {

    private final ManufacturerRepository manufacturerRepository;

    public ManufacturerServiceImpl(ManufacturerRepository manufacturerRepository) {
        this.manufacturerRepository = manufacturerRepository;
    }

    @Override
    public List<Manufacturer> findAll() {
        return this.manufacturerRepository.findAll();
    }

    @Override
    public Optional<Manufacturer> findByID(Long ID) {
        return this.manufacturerRepository.findById(ID);
    }

    @Override
    public Optional<Manufacturer> create(Manufacturer manufacturer) {
        return Optional.of(this.manufacturerRepository.save(manufacturer));
    }

    @Override
    public Optional<Manufacturer> update(Long ID, Manufacturer manufacturer) {
        return this.manufacturerRepository.findById(ID)
                .map(existingManufacturer_obj -> {
                    if (existingManufacturer_obj.getName() != null) {
                        existingManufacturer_obj.setName(existingManufacturer_obj.getName());
                    }
                    if (existingManufacturer_obj.getAddress() != null) {
                        existingManufacturer_obj.setAddress(existingManufacturer_obj.getAddress());
                    }

                    return this.manufacturerRepository.save(existingManufacturer_obj);
                });
    }

    @Override
    public void deleteByID(Long ID) {
        this.manufacturerRepository.deleteById(ID);
    }
}