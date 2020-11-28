package eu.maryns.romeo.kinderkankerfonds.web.contactinfo;

import com.haulmont.cuba.gui.screen.*;
import eu.maryns.romeo.kinderkankerfonds.entity.ContactInfo;

@UiController("kinderkankerfonds$ContactInfo.edit")
@UiDescriptor("contact-info-edit.xml")
@EditedEntityContainer("contactInfoDc")
@LoadDataBeforeShow
public class ContactInfoEdit extends StandardEditor<ContactInfo> {
}