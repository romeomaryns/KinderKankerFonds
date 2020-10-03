package eu.maryns.romeo.kinderkankerfonds.entity;

import com.haulmont.chile.core.annotations.NamePattern;
import com.haulmont.cuba.core.entity.annotation.Lookup;
import com.haulmont.cuba.core.entity.annotation.LookupType;

import javax.persistence.*;

@NamePattern("%s|omschrijving")
@Table(name = "KINDERKANKERFONDS_NOTITIE")
@Entity(name = "kinderkankerfonds$Notitie")
public class Notitie extends StandardClientEntity {
    private static final long serialVersionUID = 2177009611870868993L;

    @Lob
    @Column(name = "OMSCHRIJVING")
    protected String omschrijving;


    @Lookup(type = LookupType.SCREEN)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ADRESSEN_ID")
    protected Adres adressen;

    @Lookup(type = LookupType.DROPDOWN)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ZIEKENHUIS_ID")
    protected Ziekenhuis ziekenhuis;

    @Lookup(type = LookupType.SCREEN)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CONTACTINFO_ID")
    protected ContactInfo contactinfo;

    @Lookup(type = LookupType.SCREEN)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PERSOON_ID")
    protected Persoon persoon;

    @Lookup(type = LookupType.SCREEN)
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "AFSPRAAK_ID")
    protected Afspraak afspraak;

    public void setContactinfo(ContactInfo contactinfo) {
        this.contactinfo = contactinfo;
    }

    public ContactInfo getContactinfo() {
        return contactinfo;
    }

    public void setPersoon(Persoon persoon) {
        this.persoon = persoon;
    }

    public Persoon getPersoon() {
        return persoon;
    }


    public Adres getAdressen() {
        return adressen;
    }

    public void setAdressen(Adres adressen) {
        this.adressen = adressen;
    }


    public void setZiekenhuis(Ziekenhuis ziekenhuis) {
        this.ziekenhuis = ziekenhuis;
    }

    public Ziekenhuis getZiekenhuis() {
        return ziekenhuis;
    }


    public void setOmschrijving(String omschrijving) {
        this.omschrijving = omschrijving;
    }

    public String getOmschrijving() {
        return omschrijving;
    }

    public Afspraak getAfspraak() {
        return afspraak;
    }

    public void setAfspraak(Afspraak afspraak) {
        this.afspraak = afspraak;
    }
}