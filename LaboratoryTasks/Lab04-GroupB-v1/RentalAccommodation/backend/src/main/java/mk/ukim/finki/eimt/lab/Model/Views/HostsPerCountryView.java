package mk.ukim.finki.eimt.lab.Model.Views;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

@Entity
@Subselect("select * from public.hosts_per_country")
@Immutable
public class HostsPerCountryView {

    @Id
    @Column(name = "country_id")
    private Long countryID;

    @Column(name = "num_of_hosts")
    private Integer numOfHosts;

    public HostsPerCountryView() {
    }

    public HostsPerCountryView(Long countryID, Integer numOfHosts) {
        this.countryID = countryID;
        this.numOfHosts = numOfHosts;
    }

    public Long getCountryID() {
        return countryID;
    }

    public void setCountryID(Long countryID) {
        this.countryID = countryID;
    }

    public Integer getNumOfHosts() {
        return numOfHosts;
    }

    public void setNumOfHosts(Integer numOfHosts) {
        this.numOfHosts = numOfHosts;
    }
}
