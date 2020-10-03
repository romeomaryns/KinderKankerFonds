package eu.maryns.romeo.kinderkankerfonds.web.dagboekentry;

import com.haulmont.cuba.gui.screen.*;
import eu.maryns.romeo.kinderkankerfonds.entity.DagboekEntry;

@UiController("kinderkankerfonds_DagboekEntry.edit")
@UiDescriptor("dagboek-entry-edit.xml")
@EditedEntityContainer("dagboekEntryDc")
@LoadDataBeforeShow
public class DagboekEntryEdit extends StandardEditor<DagboekEntry> {
}