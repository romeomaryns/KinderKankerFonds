package eu.maryns.romeo.kinderkankerfonds.web.contactinfo;

import com.haulmont.cuba.gui.UiComponents;
import com.haulmont.cuba.gui.components.Component;
import com.haulmont.cuba.gui.components.Label;
import com.haulmont.cuba.gui.screen.*;
import eu.maryns.romeo.kinderkankerfonds.entity.ContactInfo;
import eu.maryns.romeo.kinderkankerfonds.entity.Notitie;

import javax.inject.Inject;

@UiController("kinderkankerfonds$ContactInfo.edit")
@UiDescriptor("contact-info-edit.xml")
@EditedEntityContainer("contactInfoDc")
@LoadDataBeforeShow
public class ContactInfoEdit extends StandardEditor<ContactInfo> {

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