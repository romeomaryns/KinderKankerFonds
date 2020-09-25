package eu.maryns.romeo.kinderkankerfonds.web.categorie;

import com.haulmont.cuba.gui.components.Button;
import com.haulmont.cuba.gui.components.GroupTable;
import com.haulmont.cuba.gui.screen.*;
import com.haulmont.reports.gui.actions.TablePrintFormAction;
import eu.maryns.romeo.kinderkankerfonds.entity.Adres;
import eu.maryns.romeo.kinderkankerfonds.entity.Categorie;

import javax.inject.Inject;

@UiController("kinderkankerfonds$Alles.browse")
@UiDescriptor("alles-browse.xml")
@LookupComponent("adressenTable")
@LoadDataBeforeShow
public class AllesController extends StandardLookup<Categorie> {
    @Inject
    protected Button printReportButton;

    @Inject
    private GroupTable<Adres> adressenTable;


    @Subscribe
    private void onInit(InitEvent event) {
        TablePrintFormAction action = new TablePrintFormAction("Report-kkf", adressenTable);
        adressenTable.addAction(action);
        printReportButton.setAction(action);

    }
}