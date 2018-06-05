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

    @Composition
    @OnDelete(DeletePolicy.CASCADE)
    @OneToMany(mappedBy = "persoon")
    protected List<Adres> adressen;

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