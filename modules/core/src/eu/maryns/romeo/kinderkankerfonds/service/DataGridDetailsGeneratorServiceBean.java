package eu.maryns.romeo.kinderkankerfonds.service;

import com.haulmont.cuba.core.global.DataManager;
import com.haulmont.cuba.core.global.LoadContext;
import eu.maryns.romeo.kinderkankerfonds.entity.Notitie;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;
import java.util.UUID;

@Service(DataGridDetailsGeneratorService.NAME)
public class DataGridDetailsGeneratorServiceBean implements DataGridDetailsGeneratorService {

    @Inject
    private DataManager dataManager;

    @Override
    public List<Notitie> loadAfspraakNotitesById(UUID afspraakId) {
        LoadContext<Notitie> lc = LoadContext.create(Notitie.class);
        LoadContext.Query query =
                LoadContext.createQuery("select e from kinderkankerfonds$Notitie e where e.afspraak.id = :afspraakId")
                        .setParameter("afspraakId", afspraakId);
        lc.setView("notitie-view-full");
        lc.setQuery(query);
        return dataManager.loadList(lc);
    }
}