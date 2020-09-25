package eu.maryns.romeo.kinderkankerfonds.web.geslacht;

import com.haulmont.cuba.gui.screen.*;
import eu.maryns.romeo.kinderkankerfonds.entity.Geslacht;

@UiController("kinderkankerfonds$Geslacht.browse")
@UiDescriptor("geslacht-browse.xml")
@LookupComponent("geslachtsTable")
@LoadDataBeforeShow
public class GeslachtBrowse extends StandardLookup<Geslacht> {
}