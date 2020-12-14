package eu.maryns.romeo.kinderkankerfonds.web.screens.persoon;

import com.haulmont.cuba.gui.UiComponents;
import com.haulmont.cuba.gui.components.Component;
import com.haulmont.cuba.gui.components.Label;
import com.haulmont.cuba.gui.screen.*;
import eu.maryns.romeo.kinderkankerfonds.entity.Notitie;
import eu.maryns.romeo.kinderkankerfonds.entity.Persoon;

import javax.inject.Inject;

@UiController("kinderkankerfonds$Bezoeker.browse")
@UiDescriptor("bezoeker-browse.xml")
@LookupComponent("table")
@LoadDataBeforeShow
public class BezoekerBrowse extends MasterDetailScreen<Persoon> {
    @Inject
    private UiComponents uiComponents;

    public Component renderHTMLDescription(Notitie entry) {

        Label label = uiComponents.create(Label.class);
        label.setHtmlEnabled(true);
        try {
            label.setValue(entry.getOmschrijving());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return label;
    }
}