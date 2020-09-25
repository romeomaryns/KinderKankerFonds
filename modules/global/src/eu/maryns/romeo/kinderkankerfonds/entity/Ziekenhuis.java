package eu.maryns.romeo.kinderkankerfonds.entity;

import com.haulmont.chile.core.annotations.NamePattern;
import com.haulmont.cuba.core.entity.annotation.CaseConversion;
import com.haulmont.cuba.core.entity.annotation.OnDelete;
import com.haulmont.cuba.core.global.DeletePolicy;

import javax.persistence.*;
import java.util.List;

@NamePattern("%s|naam")
@Table(name = "KINDERKANKERFONDS_ZIEKENHUIS")
@Entity(name = "kinderkankerfonds$Ziekenhuis")
public class Ziekenhuis extends StandardClientEntity {
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