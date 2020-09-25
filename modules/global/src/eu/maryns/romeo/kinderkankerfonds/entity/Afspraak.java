package eu.maryns.romeo.kinderkankerfonds.entity;

import com.haulmont.chile.core.annotations.MetaProperty;
import com.haulmont.chile.core.annotations.NamePattern;
import com.haulmont.cuba.core.entity.StandardEntity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@NamePattern("%s|topic")
@Table(name = "KINDERKANKERFONDS_AFSPRAAK")
@Entity(name = "kinderkankerfonds_Afspraak")
public class Afspraak extends StandardClientEntity {
    private static final long serialVersionUID = -1679199206504790964L;

    @Column(name = "TOPIC")
    protected String topic;

    @Column(name = "START_DATE")
    protected LocalDateTime startDate;

    @Column(name = "END_DATE")
    protected LocalDateTime endDate;

    @Column(name = "PLANNED_START_DATE")
    protected LocalDateTime plannedStartDate;

    @Column(name = "PLANNED_END_DATE")
    protected LocalDateTime plannedEndDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PERSOON_ID")
    protected Persoon persoon;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "AFDELING_ID")
    protected Afdeling afdeling;

    @OneToMany(mappedBy = "afspraak")
    protected List<Notitie> notities;

    @Column(name = "DESCRIPTION")
    protected String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "KALENDER_KLEUR_ID")
    protected KalenderKleur kalenderKleur;

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

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
        return afdeling;
    }

    public void setAfdeling(Afdeling afdeling) {
        this.afdeling = afdeling;
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

}