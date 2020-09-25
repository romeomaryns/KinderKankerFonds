package eu.maryns.romeo.kinderkankerfonds.entity;

import com.haulmont.chile.core.annotations.NamePattern;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@NamePattern("%s|naam")
@Table(name = "KINDERKANKERFONDS_LAND")
@Entity(name = "kinderkankerfonds$Land")
public class Land extends StandardClientEntity {
    private static final long serialVersionUID = 1752972966286283263L;

    @Column(name = "NAAM")
    protected String naam;

    @Column(name = "LANDCODE")
    protected String landcode;

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public String getNaam() {
        return naam;
    }

    public void setLandcode(String landcode) {
        this.landcode = landcode;
    }

    public String getLandcode() {
        return landcode;
    }


}