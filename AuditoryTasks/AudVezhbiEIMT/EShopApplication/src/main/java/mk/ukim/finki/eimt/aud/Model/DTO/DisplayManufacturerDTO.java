package mk.ukim.finki.eimt.aud.Model.DTO;

import mk.ukim.finki.eimt.aud.Model.Entity.Manufacturer;

import java.util.List;
import java.util.stream.Collectors;

public record DisplayManufacturerDTO(

        Long ID,
        String name,
        String address
) {
    public static DisplayManufacturerDTO from(Manufacturer manufacturer) {
        return new DisplayManufacturerDTO(
                manufacturer.getID(),
                manufacturer.getName(),
                manufacturer.getAddress()
        );
    }

    public static List<DisplayManufacturerDTO> from(List<Manufacturer> manufacturers) {
        return manufacturers.stream()
                .map(DisplayManufacturerDTO::from)
                .collect(Collectors.toList());
    }

    public Manufacturer toManufacturers(){
        return new Manufacturer(name, address);
    }
}