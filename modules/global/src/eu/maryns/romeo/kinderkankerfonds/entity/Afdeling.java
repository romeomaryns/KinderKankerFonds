package eu.maryns.romeo.kinderkankerfonds.entity;

import com.haulmont.chile.core.annotations.NamePattern;
import com.haulmont.cuba.core.entity.StandardEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


@NamePattern("%s |naam")
@Table(name = "KINDERKANKERFONDS_AFDELING")
@Entity(name = "kinderkankerfonds_Afdeling")
public class Afdeling extends StandardClientEntity {
    private static final long serialVersionUID = 1024336621294269314L;

    @Column(name = "NAAM")
    protected String naam;

    @Column(name = "ACTIEF")
    protected Boolean actief=true;

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public Boolean getActief() {
        return actief;
    }

    public void setActief(Boolean actief) {
        this.actief = actief;
    }
}