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

@NamePattern(" %s: |type,persoonDoel")
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
    @JoinColumn(name = "PERSOON_ORIGINE_ID")
    protected Persoon persoonOrigine;

    @Lookup(type = LookupType.SCREEN, actions = {"lookup", "open", "clear"})
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PERSOON_DOEL_ID")
    protected Persoon persoonDoel;


    public void setPersoonOrigine(Persoon persoonOrigine) {
        this.persoonOrigine = persoonOrigine;
    }

    public Persoon getPersoonOrigine() {
        return persoonOrigine;
    }


    public void setPersoonDoel(Persoon persoonDoel) {
        this.persoonDoel = persoonDoel;
    }

    public Persoon getPersoonDoel() {
        return persoonDoel;
    }



    public void setType(RelatieType type) {
        this.type = type;
    }

    public RelatieType getType() {
        return type;
    }



}