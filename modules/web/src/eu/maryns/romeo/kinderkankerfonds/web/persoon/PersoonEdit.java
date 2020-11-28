package eu.maryns.romeo.kinderkankerfonds.web.persoon;

import com.haulmont.cuba.core.global.PersistenceHelper;
import com.haulmont.cuba.gui.ScreenBuilders;
import com.haulmont.cuba.gui.UiComponents;
import com.haulmont.cuba.gui.components.*;
import com.haulmont.cuba.gui.model.CollectionPropertyContainer;
import com.haulmont.cuba.gui.model.DataContext;
import com.haulmont.cuba.gui.model.InstanceContainer;
import com.haulmont.cuba.gui.screen.*;
import eu.maryns.romeo.kinderkankerfonds.entity.ContactInfo;
import eu.maryns.romeo.kinderkankerfonds.entity.Notitie;
import eu.maryns.romeo.kinderkankerfonds.entity.Persoon;

import javax.inject.Inject;

@UiController("kinderkankerfonds$Persoon.edit")
@UiDescriptor("persoon-edit.xml")
@EditedEntityContainer("persoonDc")
@LoadDataBeforeShow
public class PersoonEdit extends StandardEditor<Persoon> {
    @Inject
    private DataContext dataContext;


    @Inject
    private CheckBox actiefCheckbox;

    @Inject
    private CheckBox oudercomiteCheckbox;

    @Inject
    private CheckBox familieCheckbox;

    @Inject
    private CheckBox ontmoetingsdagCheckbox;

    @Inject
    private CheckBox raakpuntCheckbox;

    @Inject
    private CheckBox comfortforfaitCheckbox;

    @Inject
    private CheckBox palliatiefforfaitCheckbox;

    @Inject
    private CheckBox personeelCheckbox;

    @Inject
    private UiComponents uiComponents;
    @Inject
    private TabSheet tabSheet;

    @Inject
    private HBoxLayout editActions;

    @Inject
    private Button saveButton;

    @Inject
    private InstanceContainer<Persoon> persoonDc;

    @Inject
    private CollectionPropertyContainer<ContactInfo> contactinfoDc;

    @Inject
    private ScreenBuilders screenBuilders;


    @Subscribe
    public void onInitEntity(InitEntityEvent<Persoon> event) {
        if (PersistenceHelper.isNew(getEditedEntity())) {
            System.out.println("edited Entity is NEW");
            getEditedEntity().setActief(true);
            actiefCheckbox.setValue(true);
            actiefCheckbox.commit();
            editActions.setVisible(true);
            tabSheet.setVisible(false);
            saveButton.setVisible(true);
        }
    }

    @Subscribe
    public void onInit(InitEvent event) {
        System.out.println("InitEvent ");
        com.vaadin.ui.CheckBox vaadinCheckBox = actiefCheckbox.unwrap(com.vaadin.ui.CheckBox.class);
        vaadinCheckBox.setCaptionAsHtml(true);
        vaadinCheckBox.setCaption("<span class=\"onoffswitch-inner\"></span><span class=\"onoffswitch-switch\"></span>");

        com.vaadin.ui.CheckBox vaadin2CheckBox = oudercomiteCheckbox.unwrap(com.vaadin.ui.CheckBox.class);
        vaadin2CheckBox.setCaptionAsHtml(true);
        vaadin2CheckBox.setCaption("<span class=\"onoffswitch-inner\"></span><span class=\"onoffswitch-switch\"></span>");

        com.vaadin.ui.CheckBox vaadin3CheckBox = familieCheckbox.unwrap(com.vaadin.ui.CheckBox.class);
        vaadin3CheckBox.setCaptionAsHtml(true);
        vaadin3CheckBox.setCaption("<span class=\"onoffswitch-inner\"></span><span class=\"onoffswitch-switch\"></span>");

        com.vaadin.ui.CheckBox vaadin4CheckBox = ontmoetingsdagCheckbox.unwrap(com.vaadin.ui.CheckBox.class);
        vaadin4CheckBox.setCaptionAsHtml(true);
        vaadin4CheckBox.setCaption("<span class=\"onoffswitch-inner\"></span><span class=\"onoffswitch-switch\"></span>");

        com.vaadin.ui.CheckBox vaadin5CheckBox = raakpuntCheckbox.unwrap(com.vaadin.ui.CheckBox.class);
        vaadin5CheckBox.setCaptionAsHtml(true);
        vaadin5CheckBox.setCaption("<span class=\"onoffswitch-inner\"></span><span class=\"onoffswitch-switch\"></span>");

        com.vaadin.ui.CheckBox vaadin6CheckBox = comfortforfaitCheckbox.unwrap(com.vaadin.ui.CheckBox.class);
        vaadin6CheckBox.setCaptionAsHtml(true);
        vaadin6CheckBox.setCaption("<span class=\"onoffswitch-inner\"></span><span class=\"onoffswitch-switch\"></span>");

        com.vaadin.ui.CheckBox vaadin7CheckBox = palliatiefforfaitCheckbox.unwrap(com.vaadin.ui.CheckBox.class);
        vaadin7CheckBox.setCaptionAsHtml(true);
        vaadin7CheckBox.setCaption("<span class=\"onoffswitch-inner\"></span><span class=\"onoffswitch-switch\"></span>");

        com.vaadin.ui.CheckBox vaadin8CheckBox = personeelCheckbox.unwrap(com.vaadin.ui.CheckBox.class);
        vaadin8CheckBox.setCaptionAsHtml(true);
        vaadin8CheckBox.setCaption("<span class=\"onoffswitch-inner\"></span><span class=\"onoffswitch-switch\"></span>");
    }

  /*  @Subscribe("contactinfoTable.create")
    public void onContactinfoTableCreate(Action.ActionPerformedEvent event) {

        System.out.println("onContactinfoTableCreate : " +event.getSource());

        screenBuilders.editor(ContactInfo.class,this)
                .withScreenClass(ContactInfoEdit.class)
                .withParentDataContext(dataContext)
                .newEntity()
                .withLaunchMode(OpenMode.DIALOG)
                          .withInitializer(contactInfo -> {
                                      contactInfo.setActief(true);
                                  }
                          )
                .withAfterCloseListener(contactInfoAfterScreenCloseEvent -> {
                    CloseAction closeAction = contactInfoAfterScreenCloseEvent.getCloseAction();
                    if(closeAction.equals(WINDOW_COMMIT_AND_CLOSE_ACTION)){
                        ContactInfo editedEntity = (ContactInfo) contactInfoAfterScreenCloseEvent.getScreen().getEditedEntity();
                        Persoon editedPersoon = persoonDc.getItemOrNull();
                        if(null != editedPersoon)
                        {
                            editedPersoon.getContactinfo().add(editedEntity);
                        }
                        editedPersoon = dataContext.merge(editedPersoon);
                        persoonDc.setItem(editedPersoon);
                        contactinfoDc.getMutableItems().add(editedEntity);
                        //dataContext.commit();
                     //   getScreenData().loadAll();
                    }
                })
                .build()
                .show();
    }

*/


    public Component renderHtmlDescription(Notitie note) {

        Label label = uiComponents.create(Label.class);
        label.setHtmlEnabled(true);
        try {
            label.setValue(note.getOmschrijving());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return label;
    }

    public void Save() {
        dataContext.commit();
        getEditedEntity().setActief(true);
        //  dataContext.merge(getEditedEntity());
        //getScreenData().getDataContext().commit();
        //getScreenData().loadAll();
        //getEditedEntityLoader().load();

        editActions.setVisible(true);
        tabSheet.setVisible(true);
        saveButton.setVisible(false);
    }

    @Subscribe
    public void onAfterCommitChanges(AfterCommitChangesEvent event) {
        System.out.println("onAfterCommitChanges : " + event.toString());
        //    dataContext.merge(getEditedEntity());
        // getScreenData().getDataContext().commit();
        // getScreenData().loadAll();
    }


    @Subscribe("tabSheet")
    public void onTabSheetSelectedTabChange(TabSheet.SelectedTabChangeEvent event) {
        // getScreenData().loadAll();
        System.out.println("TAB Change : " + event.getSelectedTab().getName());
    }


    @Subscribe(target = Target.DATA_CONTEXT)
    public void onChange(DataContext.ChangeEvent event) {
//        getScreenData().getDataContext().commit();
        //    getScreenData().loadAll();
        //  dataContext.merge(getEditedEntity());
        System.out.println("Change : " + event.getEntity().toString());
    }


}