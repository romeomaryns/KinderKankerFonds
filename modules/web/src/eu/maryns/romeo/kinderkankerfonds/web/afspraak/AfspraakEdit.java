package eu.maryns.romeo.kinderkankerfonds.web.afspraak;

import com.haulmont.cuba.core.global.Metadata;
import com.haulmont.cuba.gui.ScreenBuilders;
import com.haulmont.cuba.gui.components.Button;
import com.haulmont.cuba.gui.components.GroupTable;
import com.haulmont.cuba.gui.model.CollectionContainer;
import com.haulmont.cuba.gui.model.CollectionLoader;
import com.haulmont.cuba.gui.model.InstanceContainer;
import com.haulmont.cuba.gui.model.InstanceLoader;
import com.haulmont.cuba.gui.screen.*;
import eu.maryns.romeo.kinderkankerfonds.entity.*;
import eu.maryns.romeo.kinderkankerfonds.web.notitie.NotitieEdit;

import javax.inject.Inject;


@UiController("kinderkankerfonds_Afspraak.edit")
@UiDescriptor("afspraak-edit.xml")
@EditedEntityContainer("afsprakenDc")
//@LoadDataBeforeShow
public class AfspraakEdit extends StandardEditor<Afspraak> {

    @Inject
    private CollectionLoader<Notitie> geselecteerdeAfspraakNotitiesDl;

    @Inject
    private ScreenBuilders screenBuilders;

    @Inject
    private CollectionLoader<Persoon> personenDl;
    @Inject
    private CollectionLoader<KalenderKleur> kleurenDl;
    @Inject
    private CollectionContainer<Notitie> notitiesDs;

    @Inject
    private GroupTable<Notitie> notitiesTable;

    @Inject
    private CollectionLoader<Afdeling> afdelingDl;

    @Inject
    private InstanceLoader<Afspraak> alleAfsprakenDl;

    @Subscribe
    protected void onBeforeShow(BeforeShowEvent event)
    {
        Afspraak afspraak =getEditedEntity();
        if(null != afspraak && null != afspraak.getCreateTs()) {
            System.out.println("Edited Entity start : " + getEditedEntity().getPlannedStartDate());
            System.out.println("Edited Entity end : " + getEditedEntity().getPlannedEndDate());
            getScreenData().loadAll();
        }
        else {
            //alleAfsprakenDl.load();
            afdelingDl.load();
            personenDl.load();
            kleurenDl.load();
        }
    }

    @Subscribe(id = "afsprakenDc", target = Target.DATA_CONTAINER)
    public void onAfsprakenDcItemChange(InstanceContainer.ItemChangeEvent<Afspraak> event) {
        geselecteerdeAfspraakNotitiesDl.setParameter("afspraak",event.getItem());
        geselecteerdeAfspraakNotitiesDl.load();
    }

    @Subscribe("createBtn")
    public void onCreateBtnClick(Button.ClickEvent event) {
        screenBuilders.editor(Notitie.class,this)
                .withScreenClass(NotitieEdit.class)
                .newEntity()
                .withOpenMode(OpenMode.DIALOG)
                .withInitializer(notitie -> {
                            notitie.setAfspraak((Afspraak) geselecteerdeAfspraakNotitiesDl.getParameter("afspraak"));
                        }
                )
                .withAfterCloseListener(notitieEditAfterScreenCloseEvent -> {
                    CloseAction closeAction = notitieEditAfterScreenCloseEvent.getCloseAction();
                    if(closeAction.equals(WINDOW_COMMIT_AND_CLOSE_ACTION)){
                        Notitie editedEntity = notitieEditAfterScreenCloseEvent.getScreen().getEditedEntity();
                        notitiesDs.replaceItem(editedEntity);
                        geselecteerdeAfspraakNotitiesDl.load();
                    }
                }).show();
    }

    @Subscribe("editBtn")
    public void onEditBtnClick(Button.ClickEvent event) {
         screenBuilders.editor(notitiesTable)
                .withScreenClass(NotitieEdit.class)
                .withOpenMode(OpenMode.DIALOG)
               // .editEntity(notitiesTable.getSingleSelected())
                .withAfterCloseListener(notitieEditAfterScreenCloseEvent -> {
                    CloseAction closeAction = notitieEditAfterScreenCloseEvent.getCloseAction();
                    if(closeAction.equals(WINDOW_COMMIT_AND_CLOSE_ACTION)){
                        Notitie editedEntity = notitieEditAfterScreenCloseEvent.getScreen().getEditedEntity();
                        notitiesDs.replaceItem(editedEntity);
                        geselecteerdeAfspraakNotitiesDl.load();
                    }
                }).show();
    }
    
    
    
    



}