package eu.maryns.romeo.kinderkankerfonds.web.afdeling;

import com.haulmont.cuba.gui.screen.*;
import eu.maryns.romeo.kinderkankerfonds.entity.Afdeling;

@UiController("kinderkankerfonds_Afdeling.edit")
@UiDescriptor("afdeling-edit.xml")
@EditedEntityContainer("afdelingDc")
@LoadDataBeforeShow
public class AfdelingEdit extends StandardEditor<Afdeling> {
}