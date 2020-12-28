package eu.maryns.romeo.kinderkankerfonds.web.screens.bezoeker;

import com.haulmont.cuba.gui.screen.*;
import eu.maryns.romeo.kinderkankerfonds.entity.Persoon;

@UiController("kinderkankerfonds$Bezoekers.edit")
@UiDescriptor("bezoekers-edit.xml")
@EditedEntityContainer("persoonDc")
@LoadDataBeforeShow
public class BezoekersEdit extends StandardEditor<Persoon> {
}