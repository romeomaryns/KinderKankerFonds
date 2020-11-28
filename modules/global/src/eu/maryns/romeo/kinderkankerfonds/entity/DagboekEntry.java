package eu.maryns.romeo.kinderkankerfonds.entity;

import com.haulmont.chile.core.annotations.NamePattern;

import javax.persistence.*;
import java.time.LocalDate;


@NamePattern("%s|tekst")
@Table(name = "KINDERKANKERFONDS_DAGBOEK_ENTRY")
@Entity(name = "kinderkankerfonds_DagboekEntry")
public class DagboekEntry extends StandardClientEntity {
    private static final long serialVersionUID = 2749831976592916786L;

    @Column(name = "TEXT")
    protected String tekst;

    @ManyToOne
    @JoinColumn(name = "PERSOON_ID")
    protected Persoon auteur;

    @Column(name = "ATTENTION")
    protected Boolean attentie=true;

    @Column(name = "DATE")
    protected LocalDate date;

    public String getTekst() {
        return tekst;
    }

    public void setTekst(String tekst) {
        this.tekst = tekst;
    }

    public Persoon getAuteur() {
        return auteur;
    }

    public void setAuteur(Persoon auteur) {
        this.auteur = auteur;
    }

    public Boolean getAttentie() {
        return attentie;
    }

    public void setAttentie(Boolean attentie) {
        this.attentie = attentie;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}