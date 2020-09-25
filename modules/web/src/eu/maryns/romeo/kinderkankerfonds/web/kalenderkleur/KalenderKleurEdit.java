package eu.maryns.romeo.kinderkankerfonds.web.kalenderkleur;

import com.haulmont.cuba.gui.screen.*;
import eu.maryns.romeo.kinderkankerfonds.entity.KalenderKleur;

@UiController("kinderkankerfonds_KalenderKleur.edit")
@UiDescriptor("kalender-kleur-edit.xml")
@EditedEntityContainer("kalenderKleurDc")
@LoadDataBeforeShow
public class KalenderKleurEdit extends StandardEditor<KalenderKleur> {
}