package eu.maryns.romeo.kinderkankerfonds.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import com.haulmont.chile.core.annotations.Composition;
import com.haulmont.cuba.core.entity.annotation.OnDelete;
import com.haulmont.cuba.core.global.DeletePolicy;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import com.haulmont.cuba.core.entity.StandardEntity;
import com.haulmont.chile.core.annotations.NamePattern;
import com.haulmont.cuba.core.entity.annotation.Lookup;
import com.haulmont.cuba.core.entity.annotation.LookupType;
import javax.persistence.ManyToOne;
import com.haulmont.cuba.core.entity.annotation.CaseConversion;
import java.util.List;
import javax.persistence.OneToMany;

@NamePattern("%s|naam")
@Table(name = "KINDERKANKERFONDS_ZIEKENHUIS")
@Entity(name = "kinderkankerfonds$Ziekenhuis")
public class Ziekenhuis extends StandardEntity {
    private static final long serialVersionUID = -6369094302606452269L;

    @Column(name = "NAAM")
    protected String naam;

    @CaseConversion
    @Column(name = "AFKORTING")
    protected String afkorting;

    @OneToOne(fetch = FetchType.LAZY)
    @OnDelete(DeletePolicy.CASCADE)
    @JoinColumn(name = "ADRES_ID")
    protected Adres adres;

    @OneToMany(mappedBy = "ziekenhuis")
    protected List<Persoon> contactpersonen;

    @OneToMany(mappedBy = "ziekenhuis")
    protected List<Notitie> notities;

    public void setAfkorting(String afkorting) {
        this.afkorting = afkorting;
    }

    public String getAfkorting() {
        return afkorting;
    }

    public void setContactpersonen(List<Persoon> contactpersonen) {
        this.contactpersonen = contactpersonen;
    }

    public List<Persoon> getContactpersonen() {
        return contactpersonen;
    }

    public void setNotities(List<Notitie> notities) {
        this.notities = notities;
    }

    public List<Notitie> getNotities() {
        return notities;
    }


    public void setNaam(String naam) {
        this.naam = naam;
    }

    public String getNaam() {
        return naam;
    }

    public void setAdres(Adres adres) {
        this.adres = adres;
    }

    public Adres getAdres() {
        return adres;
    }


}