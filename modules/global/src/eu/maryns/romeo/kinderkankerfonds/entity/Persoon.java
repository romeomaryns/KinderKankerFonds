package eu.maryns.romeo.kinderkankerfonds.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Past;
import com.haulmont.cuba.core.entity.StandardEntity;
import com.haulmont.chile.core.annotations.NamePattern;
import com.haulmont.chile.core.annotations.Composition;
import com.haulmont.cuba.core.entity.annotation.OnDelete;
import com.haulmont.cuba.core.global.DeletePolicy;
import java.util.List;
import javax.persistence.OneToMany;
import com.haulmont.cuba.core.entity.annotation.Lookup;
import com.haulmont.cuba.core.entity.annotation.LookupType;
import com.haulmont.cuba.core.entity.annotation.OnDeleteInverse;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import java.util.Collection;

@NamePattern("%s %s|voornaam,familienaam")
@Table(name = "KINDERKANKERFONDS_PERSOON")
@Entity(name = "kinderkankerfonds$Persoon")
public class Persoon extends StandardEntity {
    private static final long serialVersionUID = 5417610545967716680L;

    @Column(name = "VOORNAAM")
    protected String voornaam;

    @Column(name = "FAMILIENAAM")
    protected String familienaam;

    @Temporal(TemporalType.DATE)
    @Past(message = "geboortedatum moet in het verleden liggen")
    @Column(name = "GEBOORTEDATUM")
    protected Date geboortedatum;

    @Temporal(TemporalType.DATE)
    @Column(name = "OVERLIJDENSDATUM")
    protected Date overlijdensdatum;

    @Column(name = "FAMILIEDAG")
    protected Boolean familiedag;

    @Column(name = "ONTMOETINGSDAG")
    protected Boolean ontmoetingsdag;

    @Column(name = "RAAKPUNT")
    protected Boolean raakpunt;

    @Column(name = "OUDERCOMITE")
    protected Boolean oudercomite;

    @Column(name = "ACTIEF")
    protected Boolean actief;

    @Lookup(type = LookupType.DROPDOWN)
    @OnDeleteInverse(DeletePolicy.UNLINK)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "GESLACHT_ID")
    protected Geslacht geslacht;

    @Lookup(type = LookupType.DROPDOWN)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ZIEKENHUIS_ID")
    protected Ziekenhuis ziekenhuis;

    @JoinTable(name = "KINDERKANKERFONDS_PERSOON_CATEGORIE_LINK",
        joinColumns = @JoinColumn(name = "PERSOON_ID"),
        inverseJoinColumns = @JoinColumn(name = "CATEGORIE_ID"))
    @ManyToMany
    protected List<Categorie> tags;

    @OneToMany(mappedBy = "persoon")
    protected List<ContactInfo> contactinfo;

    @Composition
    @OnDelete(DeletePolicy.CASCADE)
    @OneToMany(mappedBy = "persoon")
    protected List<Adres> adressen;

    @OneToMany(mappedBy = "persoonOrigine")
    @OnDelete(DeletePolicy.CASCADE)
    protected List<Relatie> relaties;

    @OneToMany(mappedBy = "persoon")
    protected List<Notitie> notities;

    @Column(name = "UNIEKEID", unique = true)
    protected String uniekeid;

    public void setUniekeid(String uniekeid) {
        this.uniekeid = uniekeid;
    }

    public String getUniekeid() {
        return uniekeid;
    }


    public void setNotities(List<Notitie> notities) {
        this.notities = notities;
    }

    public List<Notitie> getNotities() {
        return notities;
    }


    public Boolean getOudercomite() {
        return oudercomite;
    }

    public void setOudercomite(Boolean oudercomite) {
        this.oudercomite = oudercomite;
    }



    public void setOntmoetingsdag(Boolean ontmoetingsdag) {
        this.ontmoetingsdag = ontmoetingsdag;
    }

    public Boolean getOntmoetingsdag() {
        return ontmoetingsdag;
    }


    public void setContactinfo(List<ContactInfo> contactinfo) {
        this.contactinfo = contactinfo;
    }

    public List<ContactInfo> getContactinfo() {
        return contactinfo;
    }


    public void setZiekenhuis(Ziekenhuis ziekenhuis) {
        this.ziekenhuis = ziekenhuis;
    }

    public Ziekenhuis getZiekenhuis() {
        return ziekenhuis;
    }


    public void setActief(Boolean actief) {
        this.actief = actief;
    }

    public Boolean getActief() {
        return actief;
    }


    public void setOverlijdensdatum(Date overlijdensdatum) {
        this.overlijdensdatum = overlijdensdatum;
    }

    public Date getOverlijdensdatum() {
        return overlijdensdatum;
    }

    public void setFamiliedag(Boolean familiedag) {
        this.familiedag = familiedag;
    }

    public Boolean getFamiliedag() {
        return familiedag;
    }

    public void setRaakpunt(Boolean raakpunt) {
        this.raakpunt = raakpunt;
    }

    public Boolean getRaakpunt() {
        return raakpunt;
    }


    public void setTags(List<Categorie> tags) {
        this.tags = tags;
    }

    public List<Categorie> getTags() {
        return tags;
    }


    public List<Relatie> getRelaties() {
        return relaties;
    }

    public void setRelaties(List<Relatie> relaties) {
        this.relaties = relaties;
    }











    public void setGeslacht(Geslacht geslacht) {
        this.geslacht = geslacht;
    }

    public Geslacht getGeslacht() {
        return geslacht;
    }


    public void setAdressen(List<Adres> adressen, Persoon persoon) {
        this.adressen = adressen;
    }

    public List<Adres> getAdressen() {
        return adressen;
    }


    public void setVoornaam(String voornaam) {
        this.voornaam = voornaam;
    }

    public String getVoornaam() {
        return voornaam;
    }

    public void setFamilienaam(String familienaam) {
        this.familienaam = familienaam;
    }

    public String getFamilienaam() {
        return familienaam;
    }

    public void setGeboortedatum(Date geboortedatum) {
        this.geboortedatum = geboortedatum;
    }

    public Date getGeboortedatum() {
        return geboortedatum;
    }


}