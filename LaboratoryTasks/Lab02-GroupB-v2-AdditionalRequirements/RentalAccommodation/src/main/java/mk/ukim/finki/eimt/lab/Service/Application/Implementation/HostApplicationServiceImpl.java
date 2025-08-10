package mk.ukim.finki.eimt.lab.Service.Application.Implementation;

import mk.ukim.finki.eimt.lab.Model.Domain.Host;
import mk.ukim.finki.eimt.lab.Repository.HostRepository;
import mk.ukim.finki.eimt.lab.Service.Application.HostApplicationService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HostApplicationServiceImpl implements HostApplicationService {

    private final HostRepository hostRepository;

    public HostApplicationServiceImpl(HostRepository hostRepository) {
        this.hostRepository = hostRepository;
    }

    @Override
    public List<Host> findAll() {
        return this.hostRepository.findAll();
    }
}
