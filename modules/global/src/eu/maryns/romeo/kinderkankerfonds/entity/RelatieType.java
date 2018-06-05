package eu.maryns.romeo.kinderkankerfonds.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import com.haulmont.cuba.core.entity.StandardEntity;
import com.haulmont.chile.core.annotations.NamePattern;

@NamePattern(" %s|naam")
@Table(name = "KINDERKANKERFONDS_RELATIE_TYPE")
@Entity(name = "kinderkankerfonds$RelatieType")
public class RelatieType extends StandardEntity {
    private static final long serialVersionUID = -78811367229851292L;

    @NotNull(message = "Naam van het adrestype mag niet leeg zijn")
    @Column(name = "NAAM")
    protected String naam;


    public void setNaam(String naam) {
        this.naam = naam;
    }

    public String getNaam() {
        return naam;
    }


}