package eu.maryns.romeo.kinderkankerfonds.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import com.haulmont.cuba.core.entity.annotation.Lookup;
import com.haulmont.cuba.core.entity.annotation.LookupType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import com.haulmont.cuba.core.entity.StandardEntity;
import com.haulmont.chile.core.annotations.NamePattern;

@NamePattern("%s, %s, %s|telefoon,gsm,email")
@Table(name = "KINDERKANKERFONDS_CONTACT_INFO")
@Entity(name = "kinderkankerfonds$ContactInfo")
public class ContactInfo extends StandardEntity {
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