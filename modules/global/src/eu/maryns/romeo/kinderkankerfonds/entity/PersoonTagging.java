package eu.maryns.romeo.kinderkankerfonds.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import de.diedavids.cuba.taggable.entity.Tagging;
import com.haulmont.chile.core.annotations.NamePattern;

@NamePattern("%s|tag")
@Entity(name = "kinderkankerfonds$PersoonTagging")
public class PersoonTagging extends Tagging {
    private static final long serialVersionUID = 6500496847151756199L;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PERSOON_ID")
    protected Persoon persoon;

    public void setPersoon(Persoon persoon) {
        this.persoon = persoon;
    }

    public Persoon getPersoon() {
        return persoon;
    }


}