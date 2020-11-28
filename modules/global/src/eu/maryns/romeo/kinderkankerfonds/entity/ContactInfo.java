package eu.maryns.romeo.kinderkankerfonds.entity;

import com.haulmont.chile.core.annotations.Composition;
import com.haulmont.chile.core.annotations.NamePattern;
import com.haulmont.cuba.core.entity.annotation.Lookup;
import com.haulmont.cuba.core.entity.annotation.LookupType;
import com.haulmont.cuba.core.entity.annotation.OnDelete;
import com.haulmont.cuba.core.global.DeletePolicy;

import javax.persistence.*;
import java.util.List;

@NamePattern("%s %s %s|telefoon,gsm,email")
@Table(name = "KINDERKANKERFONDS_CONTACT_INFO")
@Entity(name = "kinderkankerfonds$ContactInfo")
public class ContactInfo extends StandardClientEntity {
    private static final long serialVersionUID = 666116618164946212L;

    @Column(name = "TELEFOON")
    protected String telefoon;

    @Column(name = "GSM")
    protected String gsm;

    @Column(name = "EMAIL")
    protected String email;

    @Lookup(type = LookupType.SCREEN)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PERSOON_ID")
    protected Persoon persoon;

    @Composition
    @OnDelete(DeletePolicy.CASCADE)
    @OneToMany(mappedBy = "contactinfo")
    protected List<Notitie> notities;

    @Column(name = "ACTIEF")
    protected Boolean actief;

    public void setActief(Boolean actief) {
        this.actief = actief;
    }

    public Boolean getActief() {
        return actief;
    }


    public void setNotities(List<Notitie> notities) {
        this.notities = notities;
    }

    public List<Notitie> getNotities() {
        return notities;
    }


    public void setTelefoon(String telefoon) {
        this.telefoon = telefoon;
    }

    public String getTelefoon() {
        return telefoon;
    }

    public void setGsm(String gsm) {
        this.gsm = gsm;
    }

    public String getGsm() {
        return gsm;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setPersoon(Persoon persoon) {
        this.persoon = persoon;
    }

    public Persoon getPersoon() {
        return persoon;
    }


}