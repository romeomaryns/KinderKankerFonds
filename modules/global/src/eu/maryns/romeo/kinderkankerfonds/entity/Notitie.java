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




    @JoinTable(name = "KINDERKANKERFONDS_ADRES_NOTITIE_LINK",
        joinColumns = @JoinColumn(name = "NOTITIE_ID"),
        inverseJoinColumns = @JoinColumn(name = "ADRES_ID"))
    @ManyToMany
    protected List<Adres> adressen;

    public void setAdressen(List<Adres> adressen) {
        this.adressen = adressen;
    }

    public List<Adres> getAdressen() {
        return adressen;
    }


    public void setOmschrijving(String omschrijving) {
        this.omschrijving = omschrijving;
    }

    public String getOmschrijving() {
        return omschrijving;
    }


}