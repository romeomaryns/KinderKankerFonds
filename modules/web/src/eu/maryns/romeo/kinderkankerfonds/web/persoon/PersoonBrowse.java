package eu.maryns.romeo.kinderkankerfonds.web.persoon;

import com.haulmont.cuba.gui.ScreenBuilders;
import com.haulmont.cuba.gui.components.GroupTable;
import com.haulmont.cuba.gui.model.CollectionContainer;
import com.haulmont.cuba.gui.model.DataContext;
import com.haulmont.cuba.gui.screen.*;
import eu.maryns.romeo.kinderkankerfonds.entity.Persoon;

import javax.inject.Inject;

@UiController("kinderkankerfonds$Persoon.browse")
@UiDescriptor("persoon-browse.xml")
@LookupComponent("persoonsTable")
@LoadDataBeforeShow
public class PersoonBrowse extends StandardLookup<Persoon> {


    @Inject
    private ScreenBuilders screenBuilders;


    @Inject
    private GroupTable<Persoon> persoonsTable;

    @Inject
    private CollectionContainer<Persoon> persoonsDc;

    @Inject
    private DataContext dataContext;

    @Subscribe("createBtn")
    public void onCreateBtnClick() {
        System.out.println("onCreateBtnClick : ");

        screenBuilders.editor(Persoon.class, this)
                .withScreenClass(PersoonEdit.class)
                //    .withParentDataContext(dataContext)
                .newEntity()
                .withLaunchMode(OpenMode.DIALOG)
                /*          .withInitializer(persoon -> {
                                      persoon.setActief(true);
                                  }
                          )*/
                .withAfterCloseListener(persoonAfterScreenCloseEvent -> {
                    CloseAction closeAction = persoonAfterScreenCloseEvent.getCloseAction();
                    if (closeAction.equals(WINDOW_COMMIT_AND_CLOSE_ACTION)) {
                        Persoon editedEntity = persoonAfterScreenCloseEvent.getScreen().getEditedEntity();
                        persoonsDc.replaceItem(editedEntity);
                        //    getScreenData().loadAll();
                    }
                })
                .build()
                .show();
    }

}