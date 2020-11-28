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

    public void verwijderAfspraak(Afspraak afspraak){
        System.out.println("Deleting afspraak : " + afspraak);
        dataManager.remove(afspraak);
    }

    @Override
    public Afspraak resizeAfspraak(Afspraak afspraak, LocalDateTime newStartDate, LocalDateTime newEndDate) {
        afspraak.setPlannedStartDate(newStartDate);
        afspraak.setPlannedEndDate(newEndDate);
        System.out.println("Resizing afspraak : " + afspraak);
        return dataManager.commit(afspraak);
    }

}