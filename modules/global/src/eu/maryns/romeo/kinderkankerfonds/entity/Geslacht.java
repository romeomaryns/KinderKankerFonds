package eu.maryns.romeo.kinderkankerfonds.entity;

import com.haulmont.chile.core.annotations.NamePattern;
import com.haulmont.cuba.core.entity.StandardEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@NamePattern("%s|naam")
@Table(name = "KINDERKANKERFONDS_GESLACHT")
@Entity(name = "kinderkankerfonds$Geslacht")
public class Geslacht extends StandardClientEntity {
    private static final long serialVersionUID = 4780412117516452645L;

    @NotNull
    @Column(name = "NAAM", nullable = false)
    protected String naam;

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public String getNaam() {
        return naam;
    }

}