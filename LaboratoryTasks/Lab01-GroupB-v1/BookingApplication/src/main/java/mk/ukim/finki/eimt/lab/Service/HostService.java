package mk.ukim.finki.eimt.lab.Service;

import mk.ukim.finki.eimt.lab.Model.Host;

import java.util.List;
import java.util.Optional;

public interface HostService {

    List<Host> findAllHosts();

    Host create(Host host);

    Host update(Long ID, Host host) throws Exception;

    void delete(Long ID);
}
