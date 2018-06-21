package eu.maryns.romeo.kinderkankerfonds.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Column;
import com.haulmont.cuba.core.entity.StandardEntity;
import com.haulmont.chile.core.annotations.NamePattern;

@NamePattern("%s|naam")
@Table(name = "KINDERKANKERFONDS_LAND")
@Entity(name = "kinderkankerfonds$Land")
public class Land extends StandardEntity {
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