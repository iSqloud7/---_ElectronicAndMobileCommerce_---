package mk.ukim.finki.eimt.aud.Model.DTO;

import mk.ukim.finki.eimt.aud.Model.Entity.Manufacturer;

import java.util.List;
import java.util.stream.Collectors;

public record CreateManufacturerDTO(

        String name,
        String address
) {
    public static CreateManufacturerDTO from(Manufacturer manufacturer) {
        return new CreateManufacturerDTO(
                manufacturer.getName(),
                manufacturer.getAddress()
        );
    }

    public static List<CreateManufacturerDTO> from(List<Manufacturer> manufacturers) {
        return manufacturers.stream()
                .map(CreateManufacturerDTO::from)
                .collect(Collectors.toList());
    }

    public Manufacturer toManufacturer() {
        return new Manufacturer(name, address);
    }
}