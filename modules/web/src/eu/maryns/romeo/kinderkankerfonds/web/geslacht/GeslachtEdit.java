package eu.maryns.romeo.kinderkankerfonds.web.geslacht;

import com.haulmont.cuba.gui.screen.*;
import eu.maryns.romeo.kinderkankerfonds.entity.Geslacht;

@UiController("kinderkankerfonds$Geslacht.edit")
@UiDescriptor("geslacht-edit.xml")
@EditedEntityContainer("geslachtDc")
public class GeslachtEdit extends StandardEditor<Geslacht> {
}