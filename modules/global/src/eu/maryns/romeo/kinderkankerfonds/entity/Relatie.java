package eu.maryns.romeo.kinderkankerfonds.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Column;
import com.haulmont.cuba.core.entity.StandardEntity;
import com.haulmont.chile.core.annotations.NamePattern;
import com.haulmont.cuba.core.entity.annotation.Lookup;
import com.haulmont.cuba.core.entity.annotation.LookupType;
import com.haulmont.cuba.core.entity.annotation.OnDelete;
import com.haulmont.cuba.core.global.DeletePolicy;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.List;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@NamePattern("%s - %s|naam,type")
@Table(name = "KINDERKANKERFONDS_RELATIE")
@Entity(name = "kinderkankerfonds$Relatie")
public class Relatie extends StandardEntity {
    private static final long serialVersionUID = -371006789725188469L;

    @Column(name = "NAAM")
    protected String naam;

    @Lookup(type = LookupType.DROPDOWN)
    @OnDelete(DeletePolicy.CASCADE)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TYPE_ID")
    protected RelatieType type;


    @JoinTable(name = "KINDERKANKERFONDS_PERSOON_RELATIE_LINK",
        joinColumns = @JoinColumn(name = "RELATIE_ID"),
        inverseJoinColumns = @JoinColumn(name = "PERSOON_ID"))
    @ManyToMany
    protected List<Persoon> personen;

    public void setPersonen(List<Persoon> personen) {
        this.personen = personen;
    }

    public List<Persoon> getPersonen() {
        return personen;
    }


    public void setType(RelatieType type) {
        this.type = type;
    }

    public RelatieType getType() {
        return type;
    }


    public void setNaam(String naam) {
        this.naam = naam;
    }

    public String getNaam() {
        return naam;
    }


}