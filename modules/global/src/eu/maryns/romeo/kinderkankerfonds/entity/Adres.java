package eu.maryns.romeo.kinderkankerfonds.entity;

import com.haulmont.chile.core.annotations.Composition;
import com.haulmont.chile.core.annotations.NamePattern;
import com.haulmont.cuba.core.entity.annotation.Lookup;
import com.haulmont.cuba.core.entity.annotation.LookupType;
import com.haulmont.cuba.core.entity.annotation.OnDelete;
import com.haulmont.cuba.core.global.DeletePolicy;

import javax.persistence.*;
import java.util.List;

@NamePattern("%s %s, %s %s|straatnaam,huisnummer,postcode,stad")
@Table(name = "KINDERKANKERFONDS_ADRES")
@Entity(name = "kinderkankerfonds$Adres")
public class Adres extends StandardClientEntity {
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

    @Lookup(type = LookupType.DROPDOWN)
    @ManyToOne
    @JoinColumn(name = "LAND_ID")
    protected Land land;

    @ManyToOne
    @JoinColumn(name = "PERSOON_ID")
    protected Persoon persoon;

    @Lookup(type = LookupType.DROPDOWN)
    @ManyToOne
    @JoinColumn(name = "TYPE_ID")
    protected AdresType type;

    @OnDelete(DeletePolicy.CASCADE)
    @JoinTable(name = "KINDERKANKERFONDS_ADRES_CATEGORIE_LINK",
            joinColumns = @JoinColumn(name = "ADRES_ID"),
            inverseJoinColumns = @JoinColumn(name = "CATEGORIE_ID"))
    @ManyToMany
    protected List<Categorie> categorie;

    @Composition
    @OnDelete(DeletePolicy.CASCADE)
    @OneToMany(mappedBy = "adressen")
    protected List<Notitie> notities;

    @Column(name = "ACTIEF")
    protected Boolean actief;

    @Column(name = "UNIEKEID")
    protected String uniekeid;

    public void setUniekeid(String uniekeid) {
        this.uniekeid = uniekeid;
    }

    public String getUniekeid() {
        return uniekeid;
    }


    public void setActief(Boolean actief) {
        this.actief = actief;
    }

    public Boolean getActief() {
        return actief;
    }


    public void setNotities(List<Notitie> notities) {
        this.notities = notities;
    }

    public List<Notitie> getNotities() {
        return notities;
    }


    public void setLand(Land land) {
        this.land = land;
    }

    public Land getLand() {
        return land;
    }


    public void setCategorie(List<Categorie> categorie) {
        this.categorie = categorie;
    }

    public List<Categorie> getCategorie() {
        return categorie;
    }


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