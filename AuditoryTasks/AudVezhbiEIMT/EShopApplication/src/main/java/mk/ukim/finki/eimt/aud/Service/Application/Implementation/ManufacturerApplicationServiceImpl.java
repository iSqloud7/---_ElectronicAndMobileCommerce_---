package mk.ukim.finki.eimt.aud.Service.Application.Implementation;

import mk.ukim.finki.eimt.aud.Model.DTO.CreateManufacturerDTO;
import mk.ukim.finki.eimt.aud.Model.DTO.DisplayManufacturerDTO;
import mk.ukim.finki.eimt.aud.Service.Application.ManufacturerApplicationService;
import mk.ukim.finki.eimt.aud.Service.Domain.ManufacturerService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ManufacturerApplicationServiceImpl implements ManufacturerApplicationService {

    private final ManufacturerService manufacturerService;

    public ManufacturerApplicationServiceImpl(ManufacturerService manufacturerService) {
        this.manufacturerService = manufacturerService;
    }

    @Override
    public List<DisplayManufacturerDTO> findAll() {
        return DisplayManufacturerDTO.from(this.manufacturerService.findAll());
    }

    @Override
    public Optional<DisplayManufacturerDTO> findByID(Long ID) {
        return this.manufacturerService.findByID(ID)
                .map(DisplayManufacturerDTO::from);
    }

    @Override
    public Optional<DisplayManufacturerDTO> create(CreateManufacturerDTO createManufacturerDTO) {
        return this.manufacturerService.create(createManufacturerDTO.toManufacturer())
                .map(DisplayManufacturerDTO::from);
    }

    @Override
    public Optional<DisplayManufacturerDTO> update(Long ID, CreateManufacturerDTO createManufacturerDTO) {
        return this.manufacturerService.update(ID, createManufacturerDTO.toManufacturer())
                .map(DisplayManufacturerDTO::from);
    }

    @Override
    public void deleteByID(Long ID) {
        this.manufacturerService.deleteByID(ID);
    }
}