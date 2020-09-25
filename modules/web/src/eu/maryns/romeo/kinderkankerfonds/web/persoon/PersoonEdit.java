package eu.maryns.romeo.kinderkankerfonds.web.persoon;

import com.haulmont.cuba.gui.components.AbstractEditor;
import com.haulmont.cuba.gui.components.CheckBox;
import eu.maryns.romeo.kinderkankerfonds.entity.Persoon;

import javax.inject.Inject;
import java.util.Map;

public class PersoonEdit extends AbstractEditor<Persoon> {

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

    @Override
    protected void initNewItem(Persoon item) {
        item.setActief(true);
    }

    @Override
    public void init(Map<String, Object> params) {
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
}