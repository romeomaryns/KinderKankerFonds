package eu.maryns.romeo.kinderkankerfonds.web.dagboekentry;

import com.haulmont.cuba.gui.UiComponents;
import com.haulmont.cuba.gui.components.Component;
import com.haulmont.cuba.gui.components.Label;
import com.haulmont.cuba.gui.screen.*;
import eu.maryns.romeo.kinderkankerfonds.entity.DagboekEntry;

import javax.inject.Inject;

@UiController("kinderkankerfonds_DagboekEntry.browse")
@UiDescriptor("dagboek-entry-browse.xml")
@LookupComponent("dagboekEntriesTable")
@LoadDataBeforeShow
public class DagboekEntryBrowse extends StandardLookup<DagboekEntry> {

    @Inject
    private UiComponents uiComponents;

    public Component renderHtmlDescription(DagboekEntry entry) {

        Label label = uiComponents.create(Label.class);
        label.setHtmlEnabled(true);
        try {
            label.setValue(entry.getTekst());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return label;
    }
}