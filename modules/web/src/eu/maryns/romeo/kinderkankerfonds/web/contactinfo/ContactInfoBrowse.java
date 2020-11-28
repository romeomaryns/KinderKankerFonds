package eu.maryns.romeo.kinderkankerfonds.web.contactinfo;

import com.haulmont.cuba.gui.screen.*;
import eu.maryns.romeo.kinderkankerfonds.entity.ContactInfo;

@UiController("kinderkankerfonds$ContactInfo.browse")
@UiDescriptor("contact-info-browse.xml")
@LookupComponent("contactInfoesTable")
@LoadDataBeforeShow
public class ContactInfoBrowse extends StandardLookup<ContactInfo> {
}