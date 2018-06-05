package eu.maryns.romeo.kinderkankerfonds.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import com.haulmont.cuba.core.entity.StandardEntity;
import com.haulmont.chile.core.annotations.NamePattern;

@NamePattern("%s|naam")
@Table(name = "KINDERKANKERFONDS_ADRES_TYPE")
@Entity(name = "kinderkankerfonds$AdresType")
public class AdresType extends StandardEntity {
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