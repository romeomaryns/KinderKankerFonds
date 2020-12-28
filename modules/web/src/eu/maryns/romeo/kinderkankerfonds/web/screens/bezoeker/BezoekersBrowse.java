package eu.maryns.romeo.kinderkankerfonds.web.screens.bezoeker;

import com.haulmont.cuba.gui.screen.*;
import eu.maryns.romeo.kinderkankerfonds.entity.Persoon;

@UiController("kinderkankerfonds$Bezoekers.browse")
@UiDescriptor("bezoekers-browse.xml")
@LookupComponent("persoonsTable")
@LoadDataBeforeShow
public class BezoekersBrowse extends StandardLookup<Persoon> {
}