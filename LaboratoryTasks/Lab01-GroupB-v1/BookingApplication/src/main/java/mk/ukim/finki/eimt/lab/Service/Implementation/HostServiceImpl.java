package mk.ukim.finki.eimt.lab.Service.Implementation;

import mk.ukim.finki.eimt.lab.Model.Host;
import mk.ukim.finki.eimt.lab.Repository.HostRepository;
import mk.ukim.finki.eimt.lab.Service.HostService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HostServiceImpl implements HostService {

    private final HostRepository hostRepository;

    public HostServiceImpl(HostRepository hostRepository) {
        this.hostRepository = hostRepository;
    }

    @Override
    public List<Host> findAllHosts() {
        return this.hostRepository.findAll();
    }

    @Override
    public Host create(Host host) {
        Host host_obj = new Host(host.getName(), host.getSurname(), host.getCountry());

        return this.hostRepository.save(host_obj);
    }

    @Override
    public Host update(Long ID, Host host) throws Exception {
        Host host_obj = this.hostRepository.findById(ID).orElseThrow(Exception::new);

        host_obj.setName(host.getName());
        host_obj.setSurname(host.getSurname());
        host_obj.setCountry(host.getCountry());

        return this.hostRepository.save(host_obj);
    }

    @Override
    public void delete(Long ID) {
        this.hostRepository.deleteById(ID);
    }
}
