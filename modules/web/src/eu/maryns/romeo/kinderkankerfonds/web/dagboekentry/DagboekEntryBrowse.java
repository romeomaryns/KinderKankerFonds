package eu.maryns.romeo.kinderkankerfonds.web.dagboekentry;

import com.haulmont.cuba.gui.screen.*;
import eu.maryns.romeo.kinderkankerfonds.entity.DagboekEntry;

@UiController("kinderkankerfonds_DagboekEntry.browse")
@UiDescriptor("dagboek-entry-browse.xml")
@LookupComponent("dagboekEntriesTable")
@LoadDataBeforeShow
public class DagboekEntryBrowse extends StandardLookup<DagboekEntry> {
}