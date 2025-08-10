package mk.ukim.finki.eimt.lab.Service.Application;

import mk.ukim.finki.eimt.lab.Model.DTO.HostDTO.CreateHostDTO;
import mk.ukim.finki.eimt.lab.Model.DTO.HostDTO.DisplayHostDTO;
import mk.ukim.finki.eimt.lab.Model.Domain.Host;
import mk.ukim.finki.eimt.lab.Model.Projections.HostProjection;
import mk.ukim.finki.eimt.lab.Model.Views.HostsPerCountryView;

import java.util.List;
import java.util.Optional;

public interface HostApplicationService {

    List<DisplayHostDTO> findAll();

    Optional<DisplayHostDTO> findByID(Long ID);

    Optional<DisplayHostDTO> create(CreateHostDTO createHostDTO);

    Optional<DisplayHostDTO> update(Long ID, CreateHostDTO createHostDTO);

    void delete(Long ID);

    Optional<List<HostsPerCountryView>> findAllHostsPerCountryView();

    Optional<List<HostProjection>> findAllHostsWithProjection();
}
