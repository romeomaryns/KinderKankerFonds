package eu.maryns.romeo.kinderkankerfonds.web.notitie;

import com.haulmont.cuba.core.global.PersistenceHelper;
import com.haulmont.cuba.gui.UiComponents;
import com.haulmont.cuba.gui.actions.picker.ClearAction;
import com.haulmont.cuba.gui.actions.picker.LookupAction;
import com.haulmont.cuba.gui.actions.picker.OpenAction;
import com.haulmont.cuba.gui.components.Actions;
import com.haulmont.cuba.gui.components.Form;
import com.haulmont.cuba.gui.components.PickerField;
import com.haulmont.cuba.gui.components.RichTextArea;
import com.haulmont.cuba.gui.components.data.value.ContainerValueSource;
import com.haulmont.cuba.gui.model.InstanceContainer;
import com.haulmont.cuba.gui.screen.*;
import eu.maryns.romeo.kinderkankerfonds.entity.*;

import javax.inject.Inject;

@UiController("kinderkankerfonds$Notitie.edit")
@UiDescriptor("notitie-edit.xml")
@EditedEntityContainer("notitieDc")
@LoadDataBeforeShow
public class NotitieEdit extends StandardEditor<Notitie> {

    @Inject
    private UiComponents uiComponents;

    @Inject
    private InstanceContainer<Notitie> notitieDc;

    @Inject
    private Form form;

    @Inject
    private Actions actions;

    @Subscribe
    public void onAfterShow(AfterShowEvent event) {
        Notitie notitie = getEditedEntity();
        System.out.println("Notitie in Init : " + notitie);
        if(notitie != null) {
            System.out.println("on Init notitie is not null");

            if (PersistenceHelper.isNew(getEditedEntity())) {
                System.out.println("edited Entity is NEW");
                getScreenData().getDataContext().commit();
                getScreenData().loadAll();
            }
            try {
                Adres adres = notitie.getAdressen();
                if (adres != null) {
                    System.out.println("Adres : " + adres.toString());
                    PickerField<Adres> field = uiComponents.create(PickerField.NAME);
                    field.setCaption("Adres");
                    field.setWidthFull();
                    field.setFieldEditable(true);
                    field.addAction(actions.create(LookupAction.class));
                    field.addAction(actions.create(OpenAction.class));
                    field.addAction(actions.create(ClearAction.class));
                    field.setValueSource(new ContainerValueSource<>(notitieDc, "adressen"));
                    form.add(field);
                }
            }catch(Exception e)
            {
                // do nothing
                e.printStackTrace();
            }
            try{
                Afspraak afspraak = notitie.getAfspraak();
                if (afspraak != null){
                    System.out.println("afspraak : " +afspraak.toString());
                    PickerField<Afspraak> afspraakPickerField = uiComponents.create(PickerField.NAME);
                    afspraakPickerField.setCaption("Afspraak");
                    afspraakPickerField.setWidthFull();
                    afspraakPickerField.setFieldEditable(true);
                    afspraakPickerField.addAction(actions.create(LookupAction.class));
                    afspraakPickerField.addAction(actions.create(OpenAction.class));
                    afspraakPickerField.addAction(actions.create(ClearAction.class));
                    afspraakPickerField.setValueSource(new ContainerValueSource<>(notitieDc, "afspraak"));
                    form.add(afspraakPickerField);
                }
            }catch (Exception e)
            {
                // do nothing
                e.printStackTrace();
            }
            try {
                ContactInfo contactInfo = notitie.getContactinfo();
                if (contactInfo != null) {
                    System.out.println("contactInfo : " + contactInfo.toString());
                    PickerField<ContactInfo> contactInfoPickerField = uiComponents.create(PickerField.NAME);
                    contactInfoPickerField.setCaption("Contactinfo");
                    contactInfoPickerField.setWidthFull();
                    contactInfoPickerField.setFieldEditable(true);
                    contactInfoPickerField.addAction(actions.create(LookupAction.class));
                    contactInfoPickerField.addAction(actions.create(OpenAction.class));
                    contactInfoPickerField.addAction(actions.create(ClearAction.class));
                    contactInfoPickerField.setValueSource(new ContainerValueSource<>(notitieDc, "contactinfo"));
                    form.add(contactInfoPickerField);
                }
            }catch (Exception e)
            {
                // do nothing
                e.printStackTrace();
            }
            try {
                Persoon persoon = notitie.getPersoon();
                if (persoon != null){
                    System.out.println("persoon : " + persoon.toString());
                    PickerField<Persoon> persoonPickerField = uiComponents.create(PickerField.NAME);
                    persoonPickerField.setCaption("Persoon");
                    persoonPickerField.setWidthFull();
                    persoonPickerField.setFieldEditable(true);
                    persoonPickerField.addAction(actions.create(LookupAction.class));
                    persoonPickerField.addAction(actions.create(OpenAction.class));
                    persoonPickerField.addAction(actions.create(ClearAction.class));
                    persoonPickerField.setValueSource(new ContainerValueSource<>(notitieDc, "persoon"));
                    form.add(persoonPickerField);
                }
            }catch (Exception e)
            {
                // do nothing
                e.printStackTrace();
            }
            try {
                Ziekenhuis ziekenhuis = notitie.getZiekenhuis();
                if (ziekenhuis != null){
                    System.out.println("ziekenhuis : " + ziekenhuis.toString());
                    PickerField<Ziekenhuis> ziekenhuisPickerField = uiComponents.create(PickerField.NAME);
                    ziekenhuisPickerField.setCaption("Ziekenhuis");
                    ziekenhuisPickerField.setWidthFull();
                    ziekenhuisPickerField.setFieldEditable(true);
                    ziekenhuisPickerField.addAction(actions.create(LookupAction.class));
                    ziekenhuisPickerField.addAction(actions.create(OpenAction.class));
                    ziekenhuisPickerField.addAction(actions.create(ClearAction.class));
                    ziekenhuisPickerField.setValueSource(new ContainerValueSource<>(notitieDc, "ziekenhuis"));
                    form.add(ziekenhuisPickerField);
                }
            }catch (Exception e)
            {
                // do nothing
                e.printStackTrace();
            }


            RichTextArea omschrijving = uiComponents.create(RichTextArea.NAME);
            omschrijving.setCaption("Omschrijving");
            omschrijving.setWidthFull();
            omschrijving.setEditable(true);
            omschrijving.setValueSource(new ContainerValueSource<>(notitieDc,"omschrijving"));
            form.add(omschrijving);
        }
    }

    
    /*
    @Subscribe
    public void onInitEntity(InitEntityEvent<Notitie> event) {
        Notitie notitie = event.getEntity();
        System.out.println("Notitie in InitEntity : " + notitie);
        if(notitie != null) {
            System.out.println("notitie is not null");
            try{
                Adres adres = notitie.getAdressen();
                if (adres != null) {
                    System.out.println("Adres : " + adres.toString());
                    PickerField<Adres> field = uiComponents.create(PickerField.NAME);
                    field.setCaption("Adres");
                    field.setWidthFull();
                    field.setFieldEditable(true);
                    field.addAction(actions.create(LookupAction.class));
                    field.addAction(actions.create(OpenAction.class));
                    field.addAction(actions.create(ClearAction.class));
                    field.setValueSource(new ContainerValueSource<>(notitieDc, "adressen"));
                    form.add(field);
                }
            }catch(Exception e)
            {
                // do nothing
                e.printStackTrace();
            }
            try{
                Afspraak afspraak = notitie.getAfspraak();
                if (afspraak != null){
                    System.out.println("afspraak : " +afspraak.toString());
                    PickerField<Afspraak> afspraakPickerField = uiComponents.create(PickerField.NAME);
                    afspraakPickerField.setCaption("Afspraak");
                    afspraakPickerField.setWidthFull();
                    afspraakPickerField.setFieldEditable(true);
                    afspraakPickerField.addAction(actions.create(LookupAction.class));
                    afspraakPickerField.addAction(actions.create(OpenAction.class));
                    afspraakPickerField.addAction(actions.create(ClearAction.class));
                    afspraakPickerField.setValueSource(new ContainerValueSource<>(notitieDc, "afspraak"));
                    form.add(afspraakPickerField);
                }
            }catch (Exception e)
            {
                // do nothing
                e.printStackTrace();
            }
            try {
                ContactInfo contactInfo = notitie.getContactinfo();
                if (contactInfo != null) {
                    System.out.println("contactInfo : " + contactInfo.toString());
                    PickerField<ContactInfo> contactInfoPickerField = uiComponents.create(PickerField.NAME);
                    contactInfoPickerField.setCaption("Contactinfo");
                    contactInfoPickerField.setWidthFull();
                    contactInfoPickerField.setFieldEditable(true);
                    contactInfoPickerField.addAction(actions.create(LookupAction.class));
                    contactInfoPickerField.addAction(actions.create(OpenAction.class));
                    contactInfoPickerField.addAction(actions.create(ClearAction.class));
                    contactInfoPickerField.setValueSource(new ContainerValueSource<>(notitieDc, "contactinfo"));
                    form.add(contactInfoPickerField);
                }
            }catch (Exception e)
            {
                // do nothing
                e.printStackTrace();
            }
            try {
                Persoon persoon = notitie.getPersoon();
                if (persoon != null){
                    System.out.println("persoon : " + persoon.toString());
                    PickerField<Persoon> persoonPickerField = uiComponents.create(PickerField.NAME);
                    persoonPickerField.setCaption("Persoon");
                    persoonPickerField.setWidthFull();
                    persoonPickerField.setFieldEditable(true);
                    persoonPickerField.addAction(actions.create(LookupAction.class));
                    persoonPickerField.addAction(actions.create(OpenAction.class));
                    persoonPickerField.addAction(actions.create(ClearAction.class));
                    persoonPickerField.setValueSource(new ContainerValueSource<>(notitieDc, "persoon"));
                    form.add(persoonPickerField);
                }
            }catch (Exception e)
            {
                // do nothing
                e.printStackTrace();
            }
            try {
                Ziekenhuis ziekenhuis = notitie.getZiekenhuis();
                if (ziekenhuis != null){
                    System.out.println("ziekenhuis : " + ziekenhuis.toString());
                    PickerField<Ziekenhuis> ziekenhuisPickerField = uiComponents.create(PickerField.NAME);
                    ziekenhuisPickerField.setCaption("Ziekenhuis");
                    ziekenhuisPickerField.setWidthFull();
                    ziekenhuisPickerField.setFieldEditable(true);
                    ziekenhuisPickerField.addAction(actions.create(LookupAction.class));
                    ziekenhuisPickerField.addAction(actions.create(OpenAction.class));
                    ziekenhuisPickerField.addAction(actions.create(ClearAction.class));
                    ziekenhuisPickerField.setValueSource(new ContainerValueSource<>(notitieDc, "ziekenhuis"));
                    form.add(ziekenhuisPickerField);
                }
            }catch (Exception e)
            {
                // do nothing
                e.printStackTrace();
            }


            RichTextArea omschrijving = uiComponents.create(RichTextArea.NAME);
            omschrijving.setCaption("Omschrijving");
            omschrijving.setSizeFull();
            omschrijving.setEditable(true);
            omschrijving.setValueSource(new ContainerValueSource<>(notitieDc,"omschrijving"));
            form.add(omschrijving);
        }
    }*/

}