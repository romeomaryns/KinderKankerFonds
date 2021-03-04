package eu.maryns.romeo.kinderkankerfonds.entity;

import com.haulmont.chile.core.annotations.Composition;
import com.haulmont.chile.core.annotations.NamePattern;
import com.haulmont.cuba.core.entity.annotation.Lookup;
import com.haulmont.cuba.core.entity.annotation.LookupType;
import com.haulmont.cuba.core.entity.annotation.OnDelete;
import com.haulmont.cuba.core.entity.annotation.OnDeleteInverse;
import com.haulmont.cuba.core.global.DeletePolicy;

import javax.annotation.Nullable;
import javax.persistence.*;
import javax.validation.constraints.Past;
import java.util.Date;
import java.util.List;

@NamePattern("%s %s|voornaam,familienaam")
@Table(name = "KINDERKANKERFONDS_PERSOON")
@Entity(name = "kinderkankerfonds$Persoon")
public class Persoon extends StandardClientEntity {
    private static final long serialVersionUID = 5417610545967716680L;

    @Nullable
    @Column(name = "VOORNAAM")
    protected String voornaam;

    @Column(name = "FAMILIENAAM")
    protected String familienaam;

    @Column(name = "AANSPREKING1")
    protected String aanspreking1;

    @Column(name = "AANSPREKING2")
    protected String aanspreking2;

    @Column(name = "VADER")
    protected String vader;

    @Column(name = "MOEDER")
    protected String moeder;


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

    @Column(name = "COMFORTFORFAIT")
    protected Boolean comfortforfait;

    @Column(name = "PALLIATIEFFORFAIT")
    protected Boolean palliatiefforfait;

    @Column(name = "PERSONEEL")
    protected Boolean personeel;

    @Column(name = "ACTIEF")
    protected Boolean actief;

    @Lookup(type = LookupType.DROPDOWN)
    @OnDeleteInverse(DeletePolicy.UNLINK)
    @ManyToOne
    @JoinColumn(name = "GESLACHT_ID")
    protected Geslacht geslacht;

    @Lookup(type = LookupType.DROPDOWN)
    @ManyToOne
    @JoinColumn(name = "ZIEKENHUIS_ID")
    protected Ziekenhuis ziekenhuis;

    @JoinTable(name = "KINDERKANKERFONDS_PERSOON_CATEGORIE_LINK",
        joinColumns = @JoinColumn(name = "PERSOON_ID"),
        inverseJoinColumns = @JoinColumn(name = "CATEGORIE_ID"))
    @ManyToMany
    protected List<Categorie> categorieen;

    @OnDelete(DeletePolicy.CASCADE)
    @OneToMany(mappedBy = "persoon")
    protected List<ContactInfo> contactinfo;

    @Composition
    @OnDelete(DeletePolicy.CASCADE)
    @OneToMany(mappedBy = "persoon")
    protected List<Adres> adressen;

    @OneToMany(mappedBy = "persoonOrigine")
    @OnDelete(DeletePolicy.CASCADE)
    protected List<Relatie> relaties;

    @Composition
    @OnDelete(DeletePolicy.CASCADE)
    @OneToMany(mappedBy = "persoon")
    protected List<Notitie> notities;

    @Column(name = "UNIEKEID", unique = true)
    protected String uniekeid;

    @ManyToOne
    @JoinColumn(name = "AFDELING_ID")
    protected Afdeling afdeling;

    @Column(name = "TOESTEMMING_FOTO")
    protected Boolean toestemmingFotos;

    @Column(name = "ADOWERKING")
    protected Boolean adowerking;

    @Column(name = "NADOWERKING")
    protected Boolean nadowerking;

    @Column(name = "RESIDENTIE_KOESTER")
    protected Boolean residentieKoester;

    @Column(name = "STUDIO_NONA")
    protected Boolean studioNona;

    @Column(name = "KOESTER_NIET_ONCO")
    protected Boolean koesterNietOnco;

    public void setCategorieen(List<Categorie> categorieen) {
        this.categorieen = categorieen;
    }

    public List<Categorie> getCategorieen() {
        return categorieen;
    }


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

    public void setComfortforfait(Boolean comfortforfait) {
        this.comfortforfait = comfortforfait;
    }

    public Boolean getComfortforfait() {
        return comfortforfait;
    }

    public void setPalliatiefforfait(Boolean palliatiefforfait) {
        this.palliatiefforfait = palliatiefforfait;
    }

    public Boolean getPalliatiefforfait() {
        return palliatiefforfait;
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

    public void setAanspreking1(String aanspreking1) {
        this.aanspreking1 = aanspreking1;
    }

    public String getAanspreking1() {
        return aanspreking1;
    }

    public void setAanspreking2(String aanspreking2) {
        this.aanspreking2 = aanspreking2;
    }

    public String getAanspreking2() {
        return aanspreking2;
    }

    public void setGeboortedatum(Date geboortedatum) {
        this.geboortedatum = geboortedatum;
    }

    public Date getGeboortedatum() {
        return geboortedatum;
    }

    public void setAdressen(List<Adres> adressen) {
        this.adressen = adressen;
    }

    public Boolean getPersoneel() {
        return personeel;
    }

    public void setPersoneel(Boolean personeel) {
        this.personeel = personeel;
    }

    public Afdeling getAfdeling() {
        return afdeling;
    }

    public void setAfdeling(Afdeling afdeling) {
        this.afdeling = afdeling;
    }

    public String getVader() {
        return vader;
    }

    public void setVader(String vader) {
        this.vader = vader;
    }

    public String getMoeder() {
        return moeder;
    }

    public void setMoeder(String moeder) {
        this.moeder = moeder;
    }

    public Boolean getToestemmingFotos() {
        return toestemmingFotos;
    }

    public void setToestemmingFotos(Boolean toestemmingFotos) {
        this.toestemmingFotos = toestemmingFotos;
    }


    public Boolean getAdowerking() {
        return adowerking;
    }

    public void setAdowerking(Boolean adowerking) {
        this.adowerking = adowerking;
    }

    public Boolean getNadowerking() {
        return nadowerking;
    }

    public void setNadowerking(Boolean nadowerking) {
        this.nadowerking = nadowerking;
    }

    public Boolean getResidentieKoester() {
        return residentieKoester;
    }

    public void setResidentieKoester(Boolean residentieKoester) {
        this.residentieKoester = residentieKoester;
    }

    public Boolean getStudioNona() {
        return studioNona;
    }

    public void setStudioNona(Boolean studioNona) {
        this.studioNona = studioNona;
    }

    public Boolean getKoesterNietOnco() {
        return koesterNietOnco;
    }

    public void setKoesterNietOnco(Boolean koesterNietOnco) {
        this.koesterNietOnco = koesterNietOnco;
    }
}