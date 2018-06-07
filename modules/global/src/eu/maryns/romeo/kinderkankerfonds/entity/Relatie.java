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
import java.util.Collection;
import com.haulmont.chile.core.annotations.Composition;
import javax.persistence.OneToMany;

@NamePattern(" %s: %s|type,personen")
@Table(name = "KINDERKANKERFONDS_RELATIE")
@Entity(name = "kinderkankerfonds$Relatie")
public class Relatie extends StandardEntity {
    private static final long serialVersionUID = -371006789725188469L;

    @Lookup(type = LookupType.DROPDOWN)
    @OnDelete(DeletePolicy.CASCADE)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TYPE_ID")
    protected RelatieType type;












    @Lookup(type = LookupType.SCREEN, actions = {"lookup", "open", "clear"})
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PERSOON_ID")
    protected Persoon persoon;

    @Composition
    @OnDelete(DeletePolicy.CASCADE)
    @OneToMany(mappedBy = "relatie")
    protected List<Persoon> personen;

    public void setPersonen(List<Persoon> personen) {
        this.personen = personen;
    }

    public List<Persoon> getPersonen() {
        return personen;
    }


    public void setPersoon(Persoon persoon) {
        this.persoon = persoon;
    }

    public Persoon getPersoon() {
        return persoon;
    }


    public void setType(RelatieType type) {
        this.type = type;
    }

    public RelatieType getType() {
        return type;
    }



}