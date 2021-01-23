package eu.maryns.romeo.kinderkankerfonds.web.notitie;

import com.haulmont.cuba.gui.UiComponents;
import com.haulmont.cuba.gui.components.Component;
import com.haulmont.cuba.gui.components.Label;
import com.haulmont.cuba.gui.screen.*;
import eu.maryns.romeo.kinderkankerfonds.entity.Notitie;

import javax.inject.Inject;

@UiController("kinderkankerfonds$Notitie.browse")
@UiDescriptor("notitie-browse.xml")
@LookupComponent("notitiesTable")
@LoadDataBeforeShow
public class NotitieBrowse extends StandardLookup<Notitie> {
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