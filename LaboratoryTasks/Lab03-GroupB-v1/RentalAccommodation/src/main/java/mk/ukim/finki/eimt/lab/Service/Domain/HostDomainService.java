package mk.ukim.finki.eimt.lab.Service.Domain;

import mk.ukim.finki.eimt.lab.Model.Domain.Host;
import mk.ukim.finki.eimt.lab.Model.Projections.HostProjection;
import mk.ukim.finki.eimt.lab.Model.Views.HostsPerCountryView;

import java.util.List;
import java.util.Optional;

public interface HostDomainService {

    List<Host> findAll();

    Optional<Host> findByID(Long ID);

    Optional<Host> create(Host host);

    Optional<Host> update(Long ID, Host host);

    void delete(Long ID);

    void refreshMaterializedView();

    Optional<List<HostsPerCountryView>> findAllHostsPerCountryView();

    Optional<List<HostProjection>> findAllHostsWithProjection();
}
