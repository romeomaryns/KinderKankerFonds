package eu.maryns.romeo.kinderkankerfonds.web.afdeling;

import com.haulmont.cuba.gui.screen.*;
import eu.maryns.romeo.kinderkankerfonds.entity.Afdeling;

@UiController("kinderkankerfonds_Afdeling.browse")
@UiDescriptor("afdeling-browse.xml")
@LookupComponent("afdelingsTable")
@LoadDataBeforeShow
public class AfdelingBrowse extends StandardLookup<Afdeling> {
}