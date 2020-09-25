package eu.maryns.romeo.kinderkankerfonds.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Column;
import com.haulmont.cuba.core.entity.StandardEntity;
import com.haulmont.chile.core.annotations.NamePattern;
import java.util.List;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Table(name = "KINDERKANKERFONDS_CATEGORIE")
@NamePattern("%s|naam")
@Entity(name = "kinderkankerfonds$Categorie")
public class Categorie extends StandardClientEntity {
    private static final long serialVersionUID = 2291352221909398491L;

    @Column(name = "NAAM")
    protected String naam;

    @JoinTable(name = "KINDERKANKERFONDS_PERSOON_CATEGORIE_LINK",
        joinColumns = @JoinColumn(name = "CATEGORIE_ID"),
        inverseJoinColumns = @JoinColumn(name = "PERSOON_ID"))
    @ManyToMany
    protected List<Persoon> personen;

    public void setPersonen(List<Persoon> personen) {
        this.personen = personen;
    }

    public List<Persoon> getPersonen() {
        return personen;
    }


    public void setNaam(String naam) {
        this.naam = naam;
    }

    public String getNaam() {
        return naam;
    }


}