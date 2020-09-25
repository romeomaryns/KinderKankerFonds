package eu.maryns.romeo.kinderkankerfonds.web.adres;

import com.haulmont.bali.util.ParamsMap;
import com.haulmont.cuba.core.global.DataManager;
import com.haulmont.cuba.gui.components.AbstractLookup;
import com.haulmont.cuba.gui.components.Button;
import com.haulmont.cuba.gui.components.GroupTable;
import com.haulmont.cuba.gui.screen.Subscribe;
import com.haulmont.reports.entity.Report;
import com.haulmont.reports.gui.ReportGuiManager;
import com.haulmont.reports.gui.actions.TablePrintFormAction;
import eu.maryns.romeo.kinderkankerfonds.entity.Adres;

import javax.inject.Inject;

public class AdresBrowse extends AbstractLookup {
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