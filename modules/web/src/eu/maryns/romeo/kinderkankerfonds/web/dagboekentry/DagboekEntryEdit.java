package eu.maryns.romeo.kinderkankerfonds.web.dagboekentry;

import com.haulmont.cuba.gui.components.DateField;
import com.haulmont.cuba.gui.screen.*;
import eu.maryns.romeo.kinderkankerfonds.entity.DagboekEntry;

import javax.inject.Inject;
import java.time.LocalDate;

import static java.time.LocalDate.now;

@UiController("kinderkankerfonds_DagboekEntry.edit")
@UiDescriptor("dagboek-entry-edit.xml")
@EditedEntityContainer("dagboekEntryDc")
@LoadDataBeforeShow
public class DagboekEntryEdit extends StandardEditor<DagboekEntry> {

    @Inject
    private DateField<LocalDate> dateField;


    @Subscribe
    public void onInitEntity(InitEntityEvent<DagboekEntry> event) {
        event.getEntity().setDate(now());
        event.getEntity().setAttentie(false);
        dateField.setValue(now());
    }


}