package eu.maryns.romeo.kinderkankerfonds.core.jmx;

import com.haulmont.cuba.core.Persistence;
import com.haulmont.cuba.core.Transaction;
import com.haulmont.cuba.security.app.Authenticated;
import eu.maryns.romeo.kinderkankerfonds.entity.Persoon;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component("kinderkankerfonds_ImportsMBean")
public class Imports implements ImportsMBean{

    private Logger log = LoggerFactory.getLogger(Persoon.class);

    @Inject
    private Persistence persistence;


    @Override
    @Authenticated
    public String importPersons() {
        try {
            List<Persoon> personen = new ArrayList<>();
            try (Transaction tx = persistence.createTransaction()) {
                // personen.csv , openen , lijn voor lijn persoonObject maken en bijhorende AdresObject/NotitieObject

                tx.commit();
            }
            return "Updated records for " + personen.size() + " personen";
        } catch (Throwable e) {
            log.error("Error importing personen", e);
            return ExceptionUtils.getStackTrace(e);
        }
    }

    @Override
    @Authenticated
    public String importAddresses(String personId) {
        if (personId == null) {
            return "Enter a person id";
        }
        try {
            UUID uuid = UUID.fromString(personId);
            try (Transaction tx = persistence.createTransaction()) {
                Persoon persoon = persistence.getEntityManager().find(Persoon.class, uuid);
                if (persoon == null) {
                    return "Persoon with id " + personId + " does not exist";
                }

                tx.commit();
            }
            return "Updated record " + personId;
        } catch (Throwable e) {
            log.error("Error importing addressen", e);
            return ExceptionUtils.getStackTrace(e);

        }
    }
}
