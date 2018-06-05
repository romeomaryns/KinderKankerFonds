package eu.maryns.romeo.kinderkankerfonds.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Column;
import com.haulmont.cuba.core.entity.StandardEntity;
import com.haulmont.chile.core.annotations.NamePattern;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import com.haulmont.cuba.core.entity.annotation.Lookup;
import com.haulmont.cuba.core.entity.annotation.LookupType;

@NamePattern("%s %s, %s %s|straatnaam,huisnummer,postcode,stad")
@Table(name = "KINDERKANKERFONDS_ADRES")
@Entity(name = "kinderkankerfonds$Adres")
public class Adres extends StandardEntity {
    private static final long serialVersionUID = 8728189552192102273L;

    @Column(name = "STRAATNAAM")
    protected String straatnaam;

    @Column(name = "HUISNUMMER")
    protected Integer huisnummer;

    @Column(name = "BUS", length = 10)
    protected String bus;

    @Column(name = "POSTCODE")
    protected String postcode;

    @Column(name = "STAD")
    protected String stad;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PERSOON_ID")
    protected Persoon persoon;

    @Lookup(type = LookupType.DROPDOWN)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TYPE_ID")
    protected AdresType type;

    public void setType(AdresType type) {
        this.type = type;
    }

    public AdresType getType() {
        return type;
    }


    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }


    public void setPersoon(Persoon persoon) {
        this.persoon = persoon;
    }

    public Persoon getPersoon() {
        return persoon;
    }


    public void setStraatnaam(String straatnaam) {
        this.straatnaam = straatnaam;
    }

    public String getStraatnaam() {
        return straatnaam;
    }

    public void setHuisnummer(Integer huisnummer) {
        this.huisnummer = huisnummer;
    }

    public Integer getHuisnummer() {
        return huisnummer;
    }

    public void setBus(String bus) {
        this.bus = bus;
    }

    public String getBus() {
        return bus;
    }

    public void setStad(String stad) {
        this.stad = stad;
    }

    public String getStad() {
        return stad;
    }


}