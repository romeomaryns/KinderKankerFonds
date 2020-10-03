package eu.maryns.romeo.kinderkankerfonds.entity;

import com.haulmont.chile.core.annotations.NamePattern;
import com.haulmont.cuba.core.entity.StandardEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@NamePattern("%s|naam")
@Table(name = "KINDERKANKERFONDS_KALENDER_KLEUR")
@Entity(name = "kinderkankerfonds_KalenderKleur")
public class KalenderKleur extends StandardClientEntity {
    private static final long serialVersionUID = 1353067598681157411L;

    @NotNull
    @Column(name = "NAAM", nullable = false)
    protected String naam;

    @NotNull
    @Column(name = "CSS_STYLE_NAME", nullable = false)
    protected String cssStyleName;

    @Column(name = "LEGENDE")
    protected String legende;

    public void setNaam(String naam) {
        this.naam = naam;
    }
    public String getNaam() {
        return naam;
    }

    public String getCssStyleName() {
        return cssStyleName;
    }

    public void setCssStyleName(String cssStyleName) {
        this.cssStyleName = cssStyleName;
    }

    public String getLegende() {
        return legende;
    }

    public void setLegende(String legende) {
        this.legende = legende;
    }
}