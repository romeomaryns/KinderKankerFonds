package eu.maryns.romeo.kinderkankerfonds.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import com.haulmont.cuba.core.entity.StandardEntity;
import com.haulmont.chile.core.annotations.NamePattern;

@NamePattern("%s|naam")
@Table(name = "KINDERKANKERFONDS_GESLACHT")
@Entity(name = "kinderkankerfonds$Geslacht")
public class Geslacht extends StandardEntity {
    private static final long serialVersionUID = 4780412117516452643L;

    @NotNull
    @Column(name = "NAAM", nullable = false, unique = true)
    protected String naam;

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public String getNaam() {
        return naam;
    }


}