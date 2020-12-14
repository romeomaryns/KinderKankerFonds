package eu.maryns.romeo.kinderkankerfonds.web.screens.afspraak;

import com.haulmont.cuba.gui.UiComponents;
import com.haulmont.cuba.gui.components.Component;
import com.haulmont.cuba.gui.components.Label;
import com.haulmont.cuba.gui.screen.*;
import eu.maryns.romeo.kinderkankerfonds.entity.Afspraak;

import javax.inject.Inject;

@UiController("kinderkankerfonds_Rapportage.browse")
@UiDescriptor("Rapportage-browse.xml")
@LookupComponent("afspraaksTable")
@LoadDataBeforeShow
public class RapportageBrowse extends StandardLookup<Afspraak> {

    @Inject
    private UiComponents uiComponents;

    public Component renderHTMLDescription(Afspraak entry) {

        Label label = uiComponents.create(Label.class);
        label.setHtmlEnabled(true);
        try {
            label.setValue(entry.getDescription());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return label;
    }
}