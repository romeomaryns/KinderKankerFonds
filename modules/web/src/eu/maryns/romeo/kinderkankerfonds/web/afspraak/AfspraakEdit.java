package eu.maryns.romeo.kinderkankerfonds.web.afspraak;

import com.haulmont.cuba.core.global.Metadata;
import com.haulmont.cuba.core.global.MetadataTools;
import com.haulmont.cuba.gui.UiComponents;
import com.haulmont.cuba.gui.components.Component;
import com.haulmont.cuba.gui.components.Label;
import com.haulmont.cuba.gui.components.SuggestionPickerField;
import com.haulmont.cuba.gui.model.CollectionContainer;
import com.haulmont.cuba.gui.model.CollectionLoader;
import com.haulmont.cuba.gui.model.InstanceContainer;
import com.haulmont.cuba.gui.screen.*;
import eu.maryns.romeo.kinderkankerfonds.entity.Afspraak;
import eu.maryns.romeo.kinderkankerfonds.entity.Notitie;
import eu.maryns.romeo.kinderkankerfonds.entity.Persoon;
import org.apache.commons.lang3.StringUtils;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@UiController("kinderkankerfonds_Afspraak.edit")
@UiDescriptor("afspraak-edit.xml")
@EditedEntityContainer("afspraakDc")
@LoadDataBeforeShow
public class AfspraakEdit extends StandardEditor<Afspraak> {
    @Inject
    private UiComponents uiComponents;
    @Inject
    private Metadata metadata;
    @Inject
    private InstanceContainer<Afspraak> afspraakDc;

    @Inject
    private CollectionContainer<Persoon> personenDc;
    @Inject
    private CollectionLoader<Persoon> personenDl;
    @Inject
    private SuggestionPickerField<Persoon> persoonSuggestionField;
    @Inject
    private SuggestionPickerField<Persoon> ingeplandSuggestionField;
    @Inject
    private SuggestionPickerField<Persoon> uitgevoerdSuggestionField;
    @Inject
    private MetadataTools metadataTools;

    @Subscribe
    public void onAfterInit(AfterInitEvent event) {
        Afspraak afspraak = metadata.create(Afspraak.class);
        afspraakDc.setItem(afspraak);

        personenDl.load();
        List<Persoon> personen = new ArrayList<>(personenDc.getItems());
        persoonSuggestionField.setSearchExecutor((searchString, searchParams) ->
                personen.stream()
                        .filter(persoon ->
                                StringUtils.containsIgnoreCase(metadataTools.getInstanceName(persoon), searchString))
                        .collect(Collectors.toList()));
        ingeplandSuggestionField.setSearchExecutor((searchString, searchParams) ->
                personen.stream()
                        .filter(persoon ->
                                StringUtils.containsIgnoreCase(metadataTools.getInstanceName(persoon), searchString))
                        .collect(Collectors.toList()));
        uitgevoerdSuggestionField.setSearchExecutor((searchString, searchParams) ->
                personen.stream()
                        .filter(persoon ->
                                StringUtils.containsIgnoreCase(metadataTools.getInstanceName(persoon), searchString))
                        .collect(Collectors.toList()));

    }
/*
    @Inject
    private InstanceContainer<Afspraak> afsprakenDc;
    @Inject
    private Metadata metadata;
    @Inject
    private InstanceLoader<Afspraak> afspraakDl;*/
/*
    @Subscribe
    public void onInitEntity(InitEntityEvent<Afspraak> event) {
        System.out.println("OnInitEntity " + event.getEntity().toString());
        //   afspraakDl.load();
        //Afspraak afspraak = metadata.create(Afspraak.class);
        Afspraak afspraak = new Afspraak();
        afsprakenDc.setItem(afspraak);
        getScreenData().loadAll();
    }*/


    public Component renderHtmlDescription(Notitie entity) {

        Label label = uiComponents.create(Label.class);
        label.setHtmlEnabled(true);
        try {
            label.setValue(entity.getOmschrijving());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return label;
    }
}