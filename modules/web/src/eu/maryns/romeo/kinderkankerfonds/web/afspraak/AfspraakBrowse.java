package eu.maryns.romeo.kinderkankerfonds.web.afspraak;

import com.haulmont.cuba.core.global.MetadataTools;
import com.haulmont.cuba.gui.ScreenBuilders;
import com.haulmont.cuba.gui.UiComponents;
import com.haulmont.cuba.gui.components.*;
import com.haulmont.cuba.gui.components.actions.BaseAction;
import com.haulmont.cuba.gui.model.CollectionContainer;
import com.haulmont.cuba.gui.model.CollectionLoader;
import com.haulmont.cuba.gui.screen.LookupComponent;
import com.haulmont.cuba.gui.screen.*;
import eu.maryns.romeo.kinderkankerfonds.entity.Afspraak;
import eu.maryns.romeo.kinderkankerfonds.entity.Notitie;
import eu.maryns.romeo.kinderkankerfonds.service.AfsprakenService;
import eu.maryns.romeo.kinderkankerfonds.service.DataGridDetailsGeneratorService;

import javax.inject.Inject;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.time.DayOfWeek.MONDAY;
import static java.time.DayOfWeek.SUNDAY;
import static java.time.temporal.TemporalAdjusters.nextOrSame;
import static java.time.temporal.TemporalAdjusters.previousOrSame;

@UiController("kinderkankerfonds_Afspraak.browse")
@UiDescriptor("afspraak-browse.xml")
@LookupComponent("afspraaksTable")
@LoadDataBeforeShow
public class AfspraakBrowse extends StandardLookup<Afspraak> {

    @Inject
    private ScreenBuilders screenBuilders;

    @Inject
    private CollectionContainer<Afspraak> afsprakenDc;

    @Inject
    private CollectionLoader<Afspraak> afspraaksDl;

    @Inject
    private AfsprakenService afsprakenService;

    @Inject
    private Calendar<LocalDateTime> afsprakenCalendar;

    @Inject
    private DateField dateField;


    @Inject
    private DataGrid<Afspraak> logboekDataGrid;
    @Inject
    private UiComponents uiComponents;
    @Inject
    private DataGridDetailsGeneratorService service;
    @Inject
    private MetadataTools metadataTools;

    @Subscribe
    private void onInit(InitEvent event) {
        LocalDateTime dt = LocalDateTime.now();
        LocalDateTime startDag = dt.withSecond(0);
        LocalDateTime endDag = dt.withHour(23).withMinute(59).withSecond(59);
        afsprakenCalendar.setStartDate(startDag);
        afsprakenCalendar.setEndDate(endDag);
       // afsprakenCalendar.setNavigationButtonsVisible(true);
        datesChanged(startDag,endDag);
        afspraaksDl.load();

        logboekDataGrid.setItemClickAction(new BaseAction("itemClickAction")
                .withHandler(actionPerformedEvent ->
                        logboekDataGrid.setDetailsVisible(logboekDataGrid.getSingleSelected(), true)));

    }

    @Install(to = "logboekDataGrid", subject = "detailsGenerator")
    protected Component logboekDataGridDetailsGenerator(Afspraak afspraak) {
        VBoxLayout mainLayout = uiComponents.create(VBoxLayout.class);
        mainLayout.setWidth("100%");
        mainLayout.setMargin(true);

        VBoxLayout headerBox = uiComponents.create(VBoxLayout.class);
        headerBox.setWidth("100%");


        Label<String> commentaarLabel = uiComponents.create(Label.TYPE_STRING);
        commentaarLabel.setHtmlEnabled(true);
        commentaarLabel.setStyleName("h1");
        commentaarLabel.setValue("Commentaar:<br/> " );


        Label<String> commentLabel = uiComponents.create(Label.TYPE_STRING);
        commentLabel.setHtmlEnabled(true);
        commentLabel.setValue(afspraak.getDescription());

        HBoxLayout horizontalBox = uiComponents.create(HBoxLayout.class);
        horizontalBox.setWidth("100%");
        Label<String> infoLabel = uiComponents.create(Label.TYPE_STRING);
        infoLabel.setHtmlEnabled(true);
        infoLabel.setStyleName("h1");
        infoLabel.setValue("Notities voor afspraak: " +afspraak.getTopic());

        Component closeButton = createCloseButton(afspraak);
        headerBox.add(commentaarLabel);
        headerBox.add(commentLabel);
        horizontalBox.add(infoLabel);
        horizontalBox.add(closeButton);
        horizontalBox.expand(infoLabel);
        headerBox.add(horizontalBox);

        Component content = getContent(afspraak);

        mainLayout.add(headerBox);
        mainLayout.add(content);
        mainLayout.expand(content);

        return mainLayout;
    }

    private Component getContent(Afspraak entity) {
        Label<String> content = uiComponents.create(Label.TYPE_STRING);
        content.setHtmlEnabled(true);

        StringBuilder sb = new StringBuilder();
        sb.append("<table cellspacing=3px cellpadding=3px>")
                .append("<tr>")
                .append("<th>Notitie</th>")
                .append("<th>Aangemaakt op</th>")
                .append("<th>Aangemaakt door</th>")
                .append("</tr>");
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm");
        List<Notitie> notities = service.loadAfspraakNotitesById(entity.getId());
        for (Notitie notitie : notities) {
            sb.append("<tr>");
            sb.append("<td style=\"max-width:800px;\">").append(notitie.getOmschrijving()).append("</td>");
            sb.append("<td>").append(sdf.format(notitie.getCreateTs())).append("</td>");
            sb.append("<td>").append(notitie.getCreatedBy()).append("</td>");
            sb.append("</tr>");
        }

        sb.append("<tr>")
                .append("<th>Totaal:</th>")
                .append("<th></th>").append("<th></th>")
                .append("<th>").append(entity.getNotities().size()).append("</th>")
                .append("</tr>")
                .append("</table>");
        content.setValue(sb.toString());
        return content;
    }


    private Component createCloseButton(Afspraak entity) {
        Button closeButton = uiComponents.create(Button.class);
        closeButton.setIcon("icons/close.png");
        BaseAction closeAction = new BaseAction("closeAction")
                .withHandler(actionPerformedEvent ->
                        logboekDataGrid.setDetailsVisible(entity, false))
                .withCaption("");
        closeButton.setAction(closeAction);
        return closeButton;
    }

    @Subscribe
    public void onBeforeShow(BeforeShowEvent event) {
        Map<DayOfWeek, String> days = new HashMap<>(7);
        days.put(DayOfWeek.MONDAY,"Maandag");
        days.put(DayOfWeek.TUESDAY,"Dinsdag");
        days.put(DayOfWeek.WEDNESDAY,"Woensdag");
        days.put(DayOfWeek.THURSDAY,"Donderdag");
        days.put(DayOfWeek.FRIDAY,"Vrijdag");
        days.put(DayOfWeek.SATURDAY,"Zaterdag");
        days.put(DayOfWeek.SUNDAY,"Zondag");
        afsprakenCalendar.setDayNames(days);

        Map<Month, String> months = new HashMap<>(12);
        months.put(Month.JANUARY,"Januari");
        months.put(Month.FEBRUARY,"Februari");
        months.put(Month.MARCH,"Maart");
        months.put(Month.APRIL,"April");
        months.put(Month.MAY,"Mei");
        months.put(Month.JUNE,"Juni");
        months.put(Month.JULY,"Juli");
        months.put(Month.AUGUST,"Augustus");
        months.put(Month.SEPTEMBER,"September");
        months.put(Month.OCTOBER,"Oktober");
        months.put(Month.NOVEMBER,"November");
        months.put(Month.DECEMBER,"December");
        afsprakenCalendar.setMonthNames(months);
    }


    @Subscribe("afsprakenCalendar")
    public void onAfspraakCalendarEventClick(Calendar.CalendarEventClickEvent<LocalDateTime> event){
        System.out.println("onAfspraakCalendarEventClick : " + event);
        screenBuilders.editor(Afspraak.class,this)
                .withScreenClass(AfspraakEdit.class)
                .editEntity((Afspraak)event.getEntity())
                .withOpenMode(OpenMode.DIALOG)
                .withAfterCloseListener(afspraakEditAfterScreenCloseEvent -> {
                    CloseAction closeAction = afspraakEditAfterScreenCloseEvent.getCloseAction();
                    if(closeAction.equals(WINDOW_COMMIT_AND_CLOSE_ACTION)){
                        Afspraak editedEntity = (Afspraak)afspraakEditAfterScreenCloseEvent.getScreen().getEditedEntity();
                        afsprakenDc.replaceItem(editedEntity);
                        if(afsprakenDc.containsItem(editedEntity))
                            afsprakenDc.replaceItem(editedEntity);
                        getScreenData().loadAll();
                    }
                }).show();
    }


    @Subscribe("afsprakenCalendar")
    public void onAfspraakCalendarEventMove(Calendar.CalendarEventMoveEvent<LocalDateTime> event)
    {
        Afspraak afspraak = afsprakenService.verzetAfspraak((Afspraak) event.getEntity(),event.getNewStart());
        afsprakenDc.replaceItem(afspraak);
    }

    @Subscribe("afsprakenCalendar")
    public void onAfspraakCalendarEventResize(Calendar.CalendarEventResizeEvent<LocalDateTime> event)
    {
        Afspraak afspraak = afsprakenService.resizeAfspraak((Afspraak) event.getEntity(),event.getNewStart(),event.getNewEnd());
        afsprakenDc.replaceItem(afspraak);
    }

    @Subscribe("afsprakenCalendar")
    public void onAfspraakCalendarDateClick(Calendar.CalendarDateClickEvent<LocalDateTime> event)
    {
        switchNaarDagDate(event.getDate());
    }

    @Subscribe("afsprakenCalendar")
    public void onAfspraakCalendarDayClick(Calendar.CalendarDayClickEvent<LocalDateTime> event)
    {
        System.out.println("onAfspraakCalendarDayClick : " + event);

        screenBuilders.editor(Afspraak.class,this)
                .withScreenClass(AfspraakEdit.class)
                .newEntity()
                .withLaunchMode(OpenMode.DIALOG)
                .withInitializer(afspraak -> {
                    afspraak.setStartDate(event.getDate());
                    afspraak.setPlannedStartDate(event.getDate());

                        }
                )
                .withAfterCloseListener(afspraakEditAfterScreenCloseEvent -> {
                    CloseAction closeAction = afspraakEditAfterScreenCloseEvent.getCloseAction();
                    if(closeAction.equals(WINDOW_COMMIT_AND_CLOSE_ACTION)){
                        Afspraak editedEntity = afspraakEditAfterScreenCloseEvent.getScreen().getEditedEntity();
                        afsprakenDc.replaceItem(editedEntity);
                    }
                })
                .build()
                .show();
    }


    @Subscribe("afsprakenCalendar")
    public void onAfspraakCalendarRangeClick(Calendar.CalendarRangeSelectEvent<LocalDateTime> event)
    {

        LocalDateTime start = event.getStart();
        LocalDateTime end = event.getEnd();

     screenBuilders.editor(Afspraak.class,this)
                .withScreenClass(AfspraakEdit.class)
                .withOpenMode(OpenMode.DIALOG)
                .newEntity()
                .withInitializer(afspraak1 -> {
                       afspraak1.setPlannedStartDate(start);
                       afspraak1.setPlannedEndDate(end);
                        afspraak1.setStartDate(start);
                        afspraak1.setEndDate(end);
                           }
                   )
                .withAfterCloseListener(afspraakEditAfterScreenCloseEvent -> {
                    CloseAction closeAction = afspraakEditAfterScreenCloseEvent.getCloseAction();
                    if (closeAction.equals(WINDOW_COMMIT_AND_CLOSE_ACTION)) {
                        Afspraak editedEntity = afspraakEditAfterScreenCloseEvent.getScreen().getEditedEntity();
                        if(afsprakenDc.containsItem(editedEntity))
                            afsprakenDc.replaceItem(editedEntity);
                        else
                            getScreenData().loadAll();
                    }
                })
                .show();
    }

    @Subscribe("dateField")
    public void onDateFieldChange(DateField.ValueChangeEvent event)
    {
        switchNaarDagDateDatePicker((Date) event.getValue());
    }

    public void switchNaarDagDate(LocalDateTime date) {
        LocalDateTime dt = LocalDateTime.now();
        if(date != null)
            dt = date;
        LocalDateTime startDag = dt.withSecond(0);
        LocalDateTime endDag = dt.withHour(23).withMinute(59).withSecond(59);
    /*    System.out.println("startDag : " + startDag.toString());
        System.out.println("endDag : " + endDag.toString());*/
        afsprakenCalendar.setStartDate(startDag);
        afsprakenCalendar.setEndDate(endDag);
     //   datesChanged(startDag,endDag);
    }


    public void switchNaarDagDateDatePicker(Date date) {
        LocalDate dt = LocalDate.now();
        if(date != null)
            dt = new java.sql.Date(date.getTime()).toLocalDate();
        LocalDateTime startDag = dt.minusDays(0).atStartOfDay();
        LocalDateTime endDag = dt.plusDays(1).atStartOfDay();
    /*    System.out.println("startDag : " + startDag.toString());
        System.out.println("endDag : " + endDag.toString());*/
        datesChanged(startDag,endDag);
    }



    public void switchNaarDag() {
        LocalDateTime dt = LocalDateTime.now();
        LocalDateTime startDag = dt.withSecond(0);
        LocalDateTime endDag = dt.withHour(23).withMinute(59).withSecond(59);
        afsprakenCalendar.setStartDate(startDag);
        afsprakenCalendar.setEndDate(endDag);
      //  datesChanged(startDag,endDag);
    }

    public void switchNaarWeek() {
        LocalDate today = LocalDate.now();
        LocalDateTime monday = today.with(previousOrSame(MONDAY)).atStartOfDay();
        LocalDateTime sunday = today.with(nextOrSame(SUNDAY)).atTime(23,59,59);
        afsprakenCalendar.setStartDate(monday);
        afsprakenCalendar.setEndDate(sunday);
       // datesChanged(monday,sunday);
    }

    public void switchNaarMaand() {
        LocalDate today = LocalDate.now();
        LocalDateTime firstDay = today.withDayOfMonth(1).atStartOfDay();
        LocalDateTime lastDay = today.withDayOfMonth(today.lengthOfMonth()).atTime(23,59,59);
        afsprakenCalendar.setStartDate(firstDay);
        afsprakenCalendar.setEndDate(lastDay);
       // datesChanged(firstDay,lastDay);
    }

    private void datesChanged(LocalDateTime startDate,LocalDateTime endDate) {
        LocalDate today = LocalDate.now();
        LocalDateTime begin = today.minusYears(1).atStartOfDay();
        LocalDateTime end = today.plusYears(1).atTime(23,59,59);
        if (startDate != null) {
            afspraaksDl.setParameter("startDate", startDate);
        }
        else {
            afspraaksDl.setParameter("startDate", begin);
        }
        if (endDate != null) {
            afspraaksDl.setParameter("endDate",  endDate);
        }
        else{
            afspraaksDl.setParameter("endDate",  end);
        }
        afspraaksDl.load();
    }

    public void toonAlles() {
        dateField.setValue(null);
        datesChanged(null,null);
        for (Afspraak afspraak:afsprakenDc.getItems()
             ) {
            logboekDataGrid.setDetailsVisible(afspraak,false);
        }
    }
}