package eu.maryns.romeo.kinderkankerfonds.web.categorie;

import com.haulmont.cuba.gui.screen.*;
import eu.maryns.romeo.kinderkankerfonds.entity.Categorie;

@UiController("kinderkankerfonds$Categorie.edit")
@UiDescriptor("categorie-edit.xml")
@EditedEntityContainer("categorieDc")
@LoadDataBeforeShow
public class CategorieEdit extends StandardEditor<Categorie> {
}