package eu.maryns.romeo.kinderkankerfonds.web.notitie;

import com.haulmont.cuba.gui.screen.*;
import eu.maryns.romeo.kinderkankerfonds.entity.Notitie;

@UiController("kinderkankerfonds$Notitie.browse")
@UiDescriptor("notitie-browse.xml")
@LookupComponent("notitiesTable")
@LoadDataBeforeShow
public class NotitieBrowse extends StandardLookup<Notitie> {
}