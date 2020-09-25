package eu.maryns.romeo.kinderkankerfonds.service;

import com.haulmont.cuba.core.global.DataManager;
import eu.maryns.romeo.kinderkankerfonds.entity.Afspraak;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.time.LocalDateTime;

@Service(AfsprakenService.NAME)
public class AfsprakenServiceBean implements AfsprakenService {

    @Inject
    private DataManager dataManager;

    public Afspraak verzetAfspraak(Afspraak afspraak, LocalDateTime newStartDate){
        afspraak.setEndDate(newStartDate.plusMinutes(afspraak.getDuration()));
        afspraak.setStartDate(newStartDate);
        System.out.println("Moving afspraak : " + afspraak);
        return dataManager.commit(afspraak);
    }

    public void verwijderAfspraak(Afspraak afspraak){
        System.out.println("Deleting afspraak : " + afspraak);
        dataManager.remove(afspraak);
    }

    @Override
    public Afspraak resizeAfspraak(Afspraak afspraak, LocalDateTime newStartDate, LocalDateTime newEndDate) {
        afspraak.setStartDate(newStartDate);
        afspraak.setEndDate(newEndDate);
        System.out.println("Resizing afspraak : " + afspraak);
        return dataManager.commit(afspraak);
    }

}