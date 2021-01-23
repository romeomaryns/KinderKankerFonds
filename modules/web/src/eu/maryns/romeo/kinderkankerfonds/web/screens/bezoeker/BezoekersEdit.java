package eu.maryns.romeo.kinderkankerfonds.web.screens.bezoeker;

import com.haulmont.cuba.gui.UiComponents;
import com.haulmont.cuba.gui.components.Component;
import com.haulmont.cuba.gui.components.Label;
import com.haulmont.cuba.gui.components.TabSheet;
import com.haulmont.cuba.gui.model.DataContext;
import com.haulmont.cuba.gui.screen.*;
import eu.maryns.romeo.kinderkankerfonds.entity.Notitie;
import eu.maryns.romeo.kinderkankerfonds.entity.Persoon;

import javax.inject.Inject;

@UiController("kinderkankerfonds$Bezoekers.edit")
@UiDescriptor("bezoekers-edit.xml")
@EditedEntityContainer("persoonDc")
@LoadDataBeforeShow
public class BezoekersEdit extends StandardEditor<Persoon> {
    @Inject
    private UiComponents uiComponents;
    @Inject
    private DataContext dataContext;

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

    @Subscribe("tabSheet")
    public void onTabSheetSelectedTabChange(TabSheet.SelectedTabChangeEvent event) {
        dataContext.commit();
    }


}