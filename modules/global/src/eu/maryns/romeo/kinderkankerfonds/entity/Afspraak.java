package eu.maryns.romeo.kinderkankerfonds.entity;

import com.haulmont.chile.core.annotations.Composition;
import com.haulmont.chile.core.annotations.MetaProperty;
import com.haulmont.chile.core.annotations.NamePattern;
import com.haulmont.cuba.core.entity.annotation.IgnoreUserTimeZone;
import com.haulmont.cuba.core.entity.annotation.OnDelete;
import com.haulmont.cuba.core.global.DeletePolicy;

import javax.persistence.*;
import javax.validation.constraints.Positive;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@NamePattern("%s|persoon")
@Table(name = "KINDERKANKERFONDS_AFSPRAAK")
@Entity(name = "kinderkankerfonds_Afspraak")
public class Afspraak extends StandardClientEntity {
    private static final long serialVersionUID = -1679199206504790964L;

    @IgnoreUserTimeZone
    @Column(name = "START_DATE")
    protected LocalDateTime startDate;

    @IgnoreUserTimeZone
    @Column(name = "END_DATE")
    protected LocalDateTime endDate;

    @IgnoreUserTimeZone
    @Column(name = "PLANNED_START_DATE")
    protected LocalDateTime plannedStartDate;

    @IgnoreUserTimeZone
    @Column(name = "PLANNED_END_DATE")
    protected LocalDateTime plannedEndDate;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "PERSOON_ID")
    protected Persoon persoon;

    @Composition
    @OnDelete(DeletePolicy.CASCADE)
    @OneToMany(mappedBy = "afspraak")
    protected List<Notitie> notities;

    @Column(name = "DESCRIPTION")
    protected String description;

    @ManyToOne
    @JoinColumn(name = "KALENDER_KLEUR_ID")
    protected KalenderKleur kalenderKleur;

    @ManyToOne
    @JoinColumn(name = "INGEPLAND_ID")
    protected Persoon ingepland;

    @ManyToOne
    @JoinColumn(name = "UITGEVOERD_ID")
    protected Persoon uitgevoerd;

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public Persoon getPersoon() {
        return persoon;
    }

    public void setPersoon(Persoon persoon) {
        this.persoon = persoon;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public List<Notitie> getNotities() {
        return notities;
    }

    public void setNotities(List<Notitie> notities) {
        this.notities = notities;
    }

    public LocalDateTime getPlannedStartDate() {
        return plannedStartDate;
    }

    public void setPlannedStartDate(LocalDateTime plannedStartDate) {
        this.plannedStartDate = plannedStartDate;
    }

    public LocalDateTime getPlannedEndDate() {
        return plannedEndDate;
    }

    public void setPlannedEndDate(LocalDateTime plannedEndDate) {
        this.plannedEndDate = plannedEndDate;
    }

    public Afdeling getAfdeling() {
        return persoon.getAfdeling();
    }

    public void setAfdeling(Afdeling afdeling) {
        this.persoon.setAfdeling(afdeling);
    }

    public Persoon getIngepland() {
        return ingepland;
    }

    public void setIngepland(Persoon ingepland) {
        this.ingepland = ingepland;
    }

    public Persoon getUitgevoerd() {
        return uitgevoerd;
    }

    public void setUitgevoerd(Persoon uitgevoerd) {
        this.uitgevoerd = uitgevoerd;
    }

    @Positive
    @Transient
    @MetaProperty(related = {"plannedStartDate","plannedEndDate"})
    public Long getPlannedDuration()
    {
        return (plannedStartDate != null && plannedEndDate != null)? plannedStartDate.until(plannedEndDate, ChronoUnit.MINUTES):null;
    }


    @Positive
    @Transient
    @MetaProperty(related = {"startDate","endDate"})
    public Long getDuration()
    {
        return (startDate != null && endDate != null)? startDate.until(endDate, ChronoUnit.MINUTES):null;
    }

    public KalenderKleur getKalenderKleur() {
        return kalenderKleur;
    }

    public void setKalenderKleur(KalenderKleur kalenderKleur) {
        this.kalenderKleur = kalenderKleur;
    }

    @Transient
    @MetaProperty(related = {"kalenderKleur"})
    public String getKleur() {
        if(kalenderKleur != null)
            return kalenderKleur.getCssStyleName();
        else return null;
    }

    @Transient
    @MetaProperty(related = {"persoon"})
    public String getTopic() {
        String result = "";
        if (persoon != null) {
            result = persoon.getVoornaam() + " " + persoon.getFamilienaam();
           /* if (persoon.getAfdeling() != null)
                result += " : " + persoon.getAfdeling().getNaam();*/
        }
        return result;
    }

}