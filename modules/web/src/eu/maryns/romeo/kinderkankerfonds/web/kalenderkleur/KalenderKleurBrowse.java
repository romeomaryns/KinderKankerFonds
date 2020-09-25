package eu.maryns.romeo.kinderkankerfonds.web.kalenderkleur;

import com.haulmont.cuba.gui.screen.*;
import eu.maryns.romeo.kinderkankerfonds.entity.KalenderKleur;

@UiController("kinderkankerfonds_KalenderKleur.browse")
@UiDescriptor("kalender-kleur-browse.xml")
@LookupComponent("kalenderKleursTable")
@LoadDataBeforeShow
public class KalenderKleurBrowse extends StandardLookup<KalenderKleur> {
}