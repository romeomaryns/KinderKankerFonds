package eu.maryns.romeo.kinderkankerfonds.web.adres;

import com.haulmont.cuba.gui.components.Button;
import com.haulmont.cuba.gui.components.GroupTable;
import com.haulmont.cuba.gui.screen.*;
import com.haulmont.reports.gui.actions.TablePrintFormAction;
import eu.maryns.romeo.kinderkankerfonds.entity.Adres;

import javax.inject.Inject;

@UiController("kinderkankerfonds$Adres.browse")
@UiDescriptor("adres-browse.xml")
@LookupComponent("adresesTable")
@LoadDataBeforeShow
public class AdresBrowse extends StandardLookup<Adres> {

    @Inject
    protected Button printList;

    @Inject
    private GroupTable<Adres> adresesTable;


    @Subscribe
    private void onInit(InitEvent event) {
        TablePrintFormAction action = new TablePrintFormAction("AdresLijst", adresesTable);
        adresesTable.addAction(action);
        printList.setAction(action);

    }
}