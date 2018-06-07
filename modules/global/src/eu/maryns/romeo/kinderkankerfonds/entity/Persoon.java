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

    @Lookup(type = LookupType.DROPDOWN)
    @OnDeleteInverse(DeletePolicy.UNLINK)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "GESLACHT_ID")
    protected Geslacht geslacht;

    @Composition
    @OnDelete(DeletePolicy.CASCADE)
    @OneToMany(mappedBy = "persoon")
    protected List<Adres> adressen;

    @Composition
    @OneToMany(mappedBy = "persoon")
    @OnDelete(DeletePolicy.CASCADE)
    protected Collection<Relatie> relaties;

    @Lookup(type = LookupType.SCREEN, actions = {"lookup", "open", "clear"})
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "RELATIE_ID")
    protected Relatie relatie;

    public void setRelatie(Relatie relatie) {
        this.relatie = relatie;
    }

    public Relatie getRelatie() {
        return relatie;
    }


    public Collection<Relatie> getRelaties() {
        return relaties;
    }

    public void setRelaties(Collection<Relatie> relaties) {
        this.relaties = relaties;
    }









    public void setGeslacht(Geslacht geslacht) {
        this.geslacht = geslacht;
    }

    public Geslacht getGeslacht() {
        return geslacht;
    }


    public void setAdressen(List<Adres> adressen) {
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