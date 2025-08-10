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
    public List<Host> findAll() {
        return this.hostRepository.findAll();
    }
}
