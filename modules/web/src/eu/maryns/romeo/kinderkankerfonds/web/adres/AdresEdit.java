package eu.maryns.romeo.kinderkankerfonds.web.adres;

import com.haulmont.cuba.gui.screen.*;
import eu.maryns.romeo.kinderkankerfonds.entity.Adres;

@UiController("kinderkankerfonds$Adres.edit")
@UiDescriptor("adres-edit.xml")
@EditedEntityContainer("adresDc")
@LoadDataBeforeShow
public class AdresEdit extends StandardEditor<Adres> {
}