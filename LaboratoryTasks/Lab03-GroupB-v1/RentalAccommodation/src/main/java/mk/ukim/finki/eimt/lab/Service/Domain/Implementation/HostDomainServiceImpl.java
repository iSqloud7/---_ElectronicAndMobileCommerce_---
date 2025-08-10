package mk.ukim.finki.eimt.lab.Service.Domain.Implementation;

import mk.ukim.finki.eimt.lab.Events.HostEvent;
import mk.ukim.finki.eimt.lab.Model.Domain.Host;
import mk.ukim.finki.eimt.lab.Model.Projections.HostProjection;
import mk.ukim.finki.eimt.lab.Model.Views.HostsPerCountryView;
import mk.ukim.finki.eimt.lab.Repository.HostRepository;
import mk.ukim.finki.eimt.lab.Repository.HostsPerCountryViewRepository;
import mk.ukim.finki.eimt.lab.Service.Domain.HostDomainService;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HostDomainServiceImpl implements HostDomainService {

    private final HostRepository hostRepository;
    private final HostsPerCountryViewRepository hostsPerCountryViewRepository;
    private final ApplicationEventPublisher applicationEventPublisher;

    public HostDomainServiceImpl(HostRepository hostRepository, HostsPerCountryViewRepository hostsPerCountryViewRepository, ApplicationEventPublisher applicationEventPublisher) {
        this.hostRepository = hostRepository;
        this.hostsPerCountryViewRepository = hostsPerCountryViewRepository;
        this.applicationEventPublisher = applicationEventPublisher;
    }

    @Override
    public List<Host> findAll() {
        return this.hostRepository.findAll();
    }

    @Override
    public Optional<Host> findByID(Long ID) {
        return this.hostRepository.findById(ID);
    }

    @Override
    public Optional<Host> create(Host host) {
        Host host_obj = this.hostRepository.save(host);
        HostEvent hostEvent = new HostEvent(host_obj);
        this.applicationEventPublisher.publishEvent(hostEvent);

        return Optional.of(host_obj);
    }

    @Override
    public Optional<Host> update(Long ID, Host host) {
        Optional<Host> host_obj = this.hostRepository.findById(ID);

        if (host_obj.isPresent()) {
            Host existing_host = host_obj.get();
            existing_host.setName(host.getName());
            existing_host.setSurname(host.getSurname());
            existing_host.setCountry(host.getCountry());

            this.hostRepository.save(existing_host);
            HostEvent hostEvent = new HostEvent(existing_host);
            this.applicationEventPublisher.publishEvent(hostEvent);

            return Optional.of(existing_host);
        }

        return Optional.empty();
    }

    @Override
    public void delete(Long ID) {
        this.hostRepository.deleteById(ID);
        HostEvent hostEvent = new HostEvent(null);
        this.applicationEventPublisher.publishEvent(hostEvent);
    }

    @Override
    public void refreshMaterializedView() {
        this.hostsPerCountryViewRepository.refreshMaterializedView();
    }

    @Override
    public Optional<List<HostsPerCountryView>> findAllHostsPerCountryView() {
        return Optional.of(this.hostsPerCountryViewRepository.findAll());
    }

    @Override
    public Optional<List<HostProjection>> findAllHostsWithProjection() {
        return Optional.of(this.hostRepository.findAllByNameAndSurname());
    }
}
