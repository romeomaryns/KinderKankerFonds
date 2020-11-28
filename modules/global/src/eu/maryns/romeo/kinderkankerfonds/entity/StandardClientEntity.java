package eu.maryns.romeo.kinderkankerfonds.entity;


import com.haulmont.cuba.core.entity.StandardEntity;
import com.haulmont.cuba.core.global.AppBeans;
import com.haulmont.cuba.core.global.UserSessionSource;

import javax.annotation.PostConstruct;
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class StandardClientEntity extends StandardEntity {

    private static final long serialVersionUID = 7674432058310847563L;

    @Column(name = "CLIENT", nullable = false)
    protected Integer client;

    public void setClient(Integer client) {
    //    System.out.println("Setting client to : " + client);
        this.client = client;
    }

    public Integer getClient() {
        return client;
    }

    @PostConstruct
    protected void init() {
        Integer client = AppBeans.get(UserSessionSource.class).getUserSession().getAttribute("client_id");
       // System.out.println("CLIENT in postConstruct : " + client);
        if (client == null) {
            throw new IllegalStateException("'client_id' user session attribute is absent. Log in as a client's user.");
        }
        setClient(client);
    }
}
