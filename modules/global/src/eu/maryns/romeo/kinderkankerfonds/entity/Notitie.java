package eu.maryns.romeo.kinderkankerfonds.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Lob;
import com.haulmont.cuba.core.entity.StandardEntity;
import com.haulmont.chile.core.annotations.NamePattern;
import com.haulmont.cuba.core.entity.annotation.Lookup;
import com.haulmont.cuba.core.entity.annotation.LookupType;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.List;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@NamePattern("%s|omschrijving")
@Table(name = "KINDERKANKERFONDS_NOTITIE")
@Entity(name = "kinderkankerfonds$Notitie")
public class Notitie extends StandardEntity {
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


}