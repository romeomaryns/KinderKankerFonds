package eu.maryns.romeo.kinderkankerfonds.web.afspraak;

import com.haulmont.cuba.gui.Notifications;
import com.haulmont.cuba.gui.ScreenBuilders;
import com.haulmont.cuba.gui.UiComponents;
import com.haulmont.cuba.gui.components.Calendar;
import com.haulmont.cuba.gui.components.*;
import com.haulmont.cuba.gui.model.CollectionContainer;
import com.haulmont.cuba.gui.model.CollectionLoader;
import com.haulmont.cuba.gui.screen.LookupComponent;
import com.haulmont.cuba.gui.screen.*;
import com.haulmont.cuba.web.widgets.CubaCalendar;
import eu.maryns.romeo.kinderkankerfonds.entity.Afspraak;
import eu.maryns.romeo.kinderkankerfonds.entity.DagboekEntry;
import eu.maryns.romeo.kinderkankerfonds.service.AfsprakenService;

import javax.inject.Inject;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.function.Function;

import static java.time.DayOfWeek.MONDAY;
import static java.time.DayOfWeek.SUNDAY;
import static java.time.LocalDate.from;
import static java.time.LocalDate.now;
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
    protected LookupField<Integer> maandField;
    @Inject
    private CollectionLoader<DagboekEntry> dagboekDl;
    @Inject
    private AfsprakenService afsprakenService;

    @Inject
    private Calendar<LocalDateTime> afsprakenCalendar;

    @Inject
    private DateField<Date> dateFieldDagboek;
    Map<DayOfWeek, String> days = new HashMap<>(7);
    Map<Month, String> months = new HashMap<>(12);
    Map<String, Integer> monthsReverse = new LinkedHashMap<>(12);
    @Inject
    private CollectionContainer<Afspraak> alleAfsprakenDc;
    @Inject
    private CollectionLoader<Afspraak> dagAfsprakenDl;

    @Inject
    private Notifications notifications;

    @Inject
    private GroupTable<Afspraak> afspraaksTable;
    @Inject
    private UiComponents uiComponents;


    @Subscribe
    private void onInit(InitEvent event) {
        Function localDateFormatter = new LocalDateFormatter();
        afspraaksTable.getColumn("plannedStartDate").setFormatter(localDateFormatter);
        afspraaksTable.getColumn("plannedEndDate").setFormatter(localDateFormatter);
        days.put(MONDAY, "Maandag");
        days.put(DayOfWeek.TUESDAY, "Dinsdag");
        days.put(DayOfWeek.WEDNESDAY, "Woensdag");
        days.put(DayOfWeek.THURSDAY, "Donderdag");
        days.put(DayOfWeek.FRIDAY, "Vrijdag");
        days.put(DayOfWeek.SATURDAY, "Zaterdag");
        days.put(SUNDAY, "Zondag");

        months.put(Month.JANUARY, "Januari");
        months.put(Month.FEBRUARY, "Februari");
        months.put(Month.MARCH, "Maart");
        months.put(Month.APRIL, "April");
        months.put(Month.MAY, "Mei");
        months.put(Month.JUNE, "Juni");
        months.put(Month.JULY, "Juli");
        months.put(Month.AUGUST, "Augustus");
        months.put(Month.SEPTEMBER, "September");
        months.put(Month.OCTOBER, "Oktober");
        months.put(Month.NOVEMBER, "November");
        months.put(Month.DECEMBER, "December");

        LocalDateTime dt = LocalDateTime.now();
        LocalDateTime startDag = dt.withSecond(0);
        LocalDateTime endDag = dt.withHour(23).withMinute(59).withSecond(59);
        afsprakenCalendar.setStartDate(startDag);
        afsprakenCalendar.setEndDate(endDag);
        // afsprakenCalendar.setNavigationButtonsVisible(true);
        dateFieldDagboek.setValue(new Date());
        dagboekDl.load();
        dagAfsprakenDl.load();

        monthsReverse.put("Januari", 1);
        monthsReverse.put("Februari", 2);
        monthsReverse.put("Maart", 3);
        monthsReverse.put("April", 4);
        monthsReverse.put("Mei", 5);
        monthsReverse.put("Juni", 6);
        monthsReverse.put("Juli", 7);
        monthsReverse.put("Augustus", 8);
        monthsReverse.put("September", 9);
        monthsReverse.put("Oktober", 10);
        monthsReverse.put("November", 11);
        monthsReverse.put("December", 12);
        maandField.setOptionsMap(monthsReverse);
        setCalendarLocale();
    }


    @Subscribe
    public void onBeforeShow(BeforeShowEvent event) {
        //   System.out.println ( "locales: " + Arrays.toString ( Locale.getAvailableLocales () ) );
        setCalendarLocale();
    }

    private void setCalendarLocale() {
        CubaCalendar vCalendar = afsprakenCalendar.unwrap(CubaCalendar.class);
        //  System.out.println("Locale Before : " + vCalendar.getLocale());
        Locale locale = new Locale("nl", "BE");
        vCalendar.setLocale(locale);
        //   System.out.println("Locale after : " + vCalendar.getLocale().toLanguageTag());
        //   System.out.println("TimeZone Before : " + afsprakenCalendar.getTimeZone().getDisplayName());
        vCalendar.setTimeZone(TimeZone.getTimeZone(ZoneId.of("Europe/Brussels")));
        //  System.out.println("TimeZone After : " + afsprakenCalendar.getTimeZone().getDisplayName());

        //    vCalendar.setMonthNamesShort(months.values().toArray(new String[0]));

        //  System.out.println("dayNames Before : " + vCalendar.getDayNamesShort().length);
        //  Arrays.stream(vCalendar.getDayNamesShort()).sequential().forEach(dayName -> System.out.println("DayName : " + dayName));
        // vCalendar.setDayNamesShort(days.values().toArray(new String[0]));
        //   System.out.println("dayNames after : " + vCalendar.getDayNamesShort().length);
        Arrays.stream(vCalendar.getDayNamesShort()).sequential().forEach(dayName -> System.out.println("DayName : " + dayName));

        afsprakenCalendar.setDayNames(days);
        afsprakenCalendar.getDayNames().values().stream().forEach(dayName -> System.out.println("DayName = " + dayName));
        afsprakenCalendar.setMonthNames(months);
    }


    @Subscribe("afsprakenCalendar")
    public void onAfspraakCalendarEventClick(Calendar.CalendarEventClickEvent<LocalDateTime> event) {
        System.out.println("onAfspraakCalendarEventClick : " + event);
        screenBuilders.editor(Afspraak.class,this)
                .withScreenClass(AfspraakEdit.class)
                .editEntity((Afspraak)event.getEntity())
                .withOpenMode(OpenMode.DIALOG)
                .withAfterCloseListener(afspraakEditAfterScreenCloseEvent -> {
                    CloseAction closeAction = afspraakEditAfterScreenCloseEvent.getCloseAction();
                    if(closeAction.equals(WINDOW_COMMIT_AND_CLOSE_ACTION)) {
                        try {
                            Afspraak editedEntity = afspraakEditAfterScreenCloseEvent.getScreen().getEditedEntity();
                            if (alleAfsprakenDc.containsItem(editedEntity))
                                alleAfsprakenDc.replaceItem(editedEntity);
                            getScreenData().loadAll();
                            notifications.create()
                                    .withCaption("Afspraak gewijzigd")
                                    .withDescription("naar " + editedEntity.getPlannedStartDate().format(DateTimeFormatter.ofPattern("dd-mm-yyyy HH:mm")))
                                    .withType(Notifications.NotificationType.HUMANIZED)
                                    .show();
                        } catch (Exception e) {
                            e.printStackTrace();
                            notifications.create()
                                    .withCaption("<code>Afspraak kon niet gewijzigd worden</code>")
                                    .withDescription("<u>" + e.toString() + "</u>")
                                    .withType(Notifications.NotificationType.ERROR)
                                    .withContentMode(ContentMode.HTML)
                                    .show();
                        }
                    }
                }).show();
    }

    @Subscribe("maandField")
    public void onMaandFieldValueChange(HasValue.ValueChangeEvent event) {
        switchNaarMaand((int) event.getValue());
    }


    @Subscribe("afsprakenCalendar")
    public void onAfspraakCalendarEventMove(Calendar.CalendarEventMoveEvent<LocalDateTime> event) {
        try {
            Afspraak afspraak = afsprakenService.resizeAfspraak((Afspraak) event.getEntity(), event.getNewStart(), event.getNewEnd());
            alleAfsprakenDc.replaceItem(afspraak);
            getScreenData().loadAll();
            notifications.create()
                    .withCaption("Afspraak verzet")
                    .withDescription("naar " + afspraak.getPlannedStartDate().format(DateTimeFormatter.ofPattern("dd-mm-yyyy HH:mm")))
                    .withType(Notifications.NotificationType.HUMANIZED)
                    .show();

        } catch (Exception e) {
            e.printStackTrace();
            notifications.create()
                    .withCaption("<code>Afspraak kon niet verplaatst worden</code>")
                    .withDescription("<u>" + e.toString() + "</u>")
                    .withType(Notifications.NotificationType.ERROR)
                    .withContentMode(ContentMode.HTML)
                    .show();
        }
    }


    @Subscribe("afsprakenCalendar")
    public void onAfspraakCalendarEventResize(Calendar.CalendarEventResizeEvent<LocalDateTime> event) {
        try {
            Afspraak afspraak = afsprakenService.resizeAfspraak((Afspraak) event.getEntity(), event.getNewStart(), event.getNewEnd());
            alleAfsprakenDc.replaceItem(afspraak);
            getScreenData().loadAll();
            notifications.create()
                    .withCaption("Afspraak aangepast")
                    .withType(Notifications.NotificationType.HUMANIZED)
                    .show();
        } catch (Exception e) {
            e.printStackTrace();
            notifications.create()
                    .withCaption("<code>Afspraak kon niet aangepast worden</code>")
                    .withDescription("<u>" + e.toString() + "</u>")
                    .withType(Notifications.NotificationType.ERROR)
                    .withContentMode(ContentMode.HTML)
                    .show();
        }
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
                            afspraak.setPlannedStartDate(event.getDate());
                            afspraak.setPlannedEndDate(event.getDate());
                        }
                )
                .withAfterCloseListener(afspraakEditAfterScreenCloseEvent -> {
                    CloseAction closeAction = afspraakEditAfterScreenCloseEvent.getCloseAction();
                    if(closeAction.equals(WINDOW_COMMIT_AND_CLOSE_ACTION)) {
                        try {
                            Afspraak editedEntity = afspraakEditAfterScreenCloseEvent.getScreen().getEditedEntity();
                            if (alleAfsprakenDc.containsItem(editedEntity))
                                alleAfsprakenDc.replaceItem(editedEntity);
                            else
                                alleAfsprakenDc.getMutableItems().add(editedEntity);
                            getScreenData().loadAll();
                            notifications.create()
                                    .withCaption("Afspraak aangemaakt")
                                    .withType(Notifications.NotificationType.HUMANIZED)
                                    .show();
                        } catch (Exception e) {
                            e.printStackTrace();
                            notifications.create()
                                    .withCaption("<code>Afspraak kon niet aangemaakt worden</code>")
                                    .withDescription("<u>" + e.toString() + "</u>")
                                    .withType(Notifications.NotificationType.ERROR)
                                    .withContentMode(ContentMode.HTML)
                                    .show();
                        }
                    }
                })
                .build()
                .show();
    }


    @Subscribe("afsprakenCalendar")
    public void onAfspraakCalendarRangeClick(Calendar.CalendarRangeSelectEvent<LocalDateTime> event) {

        LocalDateTime start = event.getStart();
        LocalDateTime end = event.getEnd();

        //Afspraak newAfspraak = dataManager.create(Afspraak.class);
   /*     Afspraak newAfspraak = new Afspraak();
        newAfspraak.setPlannedStartDate(start);
        newAfspraak.setPlannedEndDate(end);
        newAfspraak.setStartDate(start);
        newAfspraak.setEndDate(end);*/

        screenBuilders.editor(Afspraak.class, this)
                .withScreenClass(AfspraakEdit.class)
                .withOpenMode(OpenMode.DIALOG)
                .newEntity()
                .withInitializer(afspraak1 -> {
                            afspraak1.setPlannedStartDate(start);
                            afspraak1.setPlannedEndDate(end);
                   /*       afspraak1.setStartDate(start);
                          afspraak1.setEndDate(end);*/
                        }
                )
                .withAfterCloseListener(afspraakEditAfterScreenCloseEvent -> {
                    CloseAction closeAction = afspraakEditAfterScreenCloseEvent.getCloseAction();
                    if (closeAction.equals(WINDOW_COMMIT_AND_CLOSE_ACTION)) {
                        try {
                            Afspraak editedEntity = afspraakEditAfterScreenCloseEvent.getScreen().getEditedEntity();
                            if (alleAfsprakenDc.containsItem(editedEntity))
                                alleAfsprakenDc.replaceItem(editedEntity);
                            else
                                alleAfsprakenDc.getMutableItems().add(editedEntity);
                            getScreenData().loadAll();
                            notifications.create()
                                    .withCaption("Afspraak aangemaakt")
                                    .withType(Notifications.NotificationType.HUMANIZED)
                                    .show();
                        } catch (Exception e) {
                            e.printStackTrace();
                            notifications.create()
                                    .withCaption("<code>Afspraak kon niet aangemaakt worden</code>")
                                    .withDescription("<u>" + e.toString() + "</u>")
                                    .withType(Notifications.NotificationType.ERROR)
                                    .withContentMode(ContentMode.HTML)
                                    .show();
                        }
                    }
                })
                .show();
    }


    @Subscribe("dateFieldDagboek")
    public void onDateFieldDagboekChange(DateField.ValueChangeEvent event)
    {
        dateChanged((Date) event.getValue());
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
        setCalendarLocale();
        //   datesChanged(startDag,endDag);
    }
/*

    public void switchNaarDagDateDatePicker(Date date) {
        LocalDate dt = now();
        if(date != null)
            dt = new java.sql.Date(date.getTime()).toLocalDate();
        LocalDateTime startDag = dt.minusDays(0).atStartOfDay();
        LocalDateTime endDag = dt.plusDays(1).atStartOfDay();
        System.out.println("startDag : " + startDag.toString());
        System.out.println("endDag : " + endDag.toString());
        datesChanged(startDag,endDag);
    }*/



    public void switchNaarDag() {
        LocalDateTime dt = LocalDateTime.now();
        LocalDateTime startDag = dt.withSecond(0);
        LocalDateTime endDag = dt.withHour(23).withMinute(59).withSecond(59);
        afsprakenCalendar.setStartDate(startDag);
        afsprakenCalendar.setEndDate(endDag);
        //  datesChanged(startDag,endDag);
        maandField.setVisible(false);
    }

    public void switchNaarWeek() {
        LocalDate today = now();
        LocalDateTime monday = today.with(previousOrSame(MONDAY)).atStartOfDay();
        LocalDateTime sunday = today.with(nextOrSame(SUNDAY)).atTime(23, 59, 59);
        afsprakenCalendar.setStartDate(monday);
        afsprakenCalendar.setEndDate(sunday);
        // datesChanged(monday,sunday);
        maandField.setVisible(false);
    }

    public void switchNaarMaand() {
        LocalDate today = now();
        LocalDateTime firstDay = today.withDayOfMonth(1).atStartOfDay();
        LocalDateTime lastDay = today.withDayOfMonth(today.lengthOfMonth()).atTime(23, 59, 59);
        afsprakenCalendar.setStartDate(firstDay);
        afsprakenCalendar.setEndDate(lastDay);
        // datesChanged(firstDay,lastDay);
        maandField.setValue(today.getMonthValue());
        maandField.setVisible(true);
    }

    public void switchNaarMaand(int maand) {
        LocalDate today = now().withMonth(maand);
        LocalDateTime firstDay = today.withDayOfMonth(1).atStartOfDay();
        LocalDateTime lastDay = today.withDayOfMonth(today.lengthOfMonth()).atTime(23, 59, 59);
        afsprakenCalendar.setStartDate(firstDay);
        afsprakenCalendar.setEndDate(lastDay);
        // datesChanged(firstDay,lastDay);
        maandField.setValue(maand);
        maandField.setVisible(true);
    }

    private void datesChanged(Date date) {
        LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDateTime begin = localDate.atStartOfDay();
        LocalDateTime end = localDate.plusDays(1).atStartOfDay();
        if (localDate != null) {
            dagAfsprakenDl.setParameter("startDate", date);
        } else {
            dagAfsprakenDl.setParameter("startDate", begin);
        }
        if (localDate != null) {
            dagAfsprakenDl.setParameter("endDate", end);
        } else {
            dagAfsprakenDl.setParameter("endDate", end);
        }
        dagAfsprakenDl.load();
    }

    private void dateChanged(Date date) {
        dagboekDl.setParameter("date", date);
        dagboekDl.load();
        datesChanged(date);
    }


    public void dagErvoor() {
        Date dt = (Date) dagboekDl.getParameter("date");
        //    System.out.println("Date : " + dt);
        LocalDate localDate = from(dt.toInstant().atZone(ZoneId.systemDefault())).minusDays(1l);
        //   System.out.println("localDate : " + localDate);
        dt =Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        dateFieldDagboek.setValue(dt);
        dateChanged(dt);
    }

    public void dagErna() {
        Date dt = (Date) dagboekDl.getParameter("date");
        //   System.out.println("Date : " + dt);
        LocalDate localDate = from(dt.toInstant().atZone(ZoneId.systemDefault())).plusDays(1l);
        //   System.out.println("localDate : " + localDate);
        dt =Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        dateFieldDagboek.setValue(dt);
        dateChanged(dt);
    }

    @Install(to = "dagboekTable", subject = "styleProvider")
    protected String dagboekTableStyleProvider(DagboekEntry dagboekEntry, String property) {
        if (property == null) {
            if (Boolean.TRUE.equals(dagboekEntry.getAttentie())) {
                return "table-style-warning";
            }
        }
        return null;
    }

    @Install(to = "afspraaksTable", subject = "styleProvider")
    protected String afspraakTableStyleProvider(Afspraak afspraak, String property) {
        if (property == null) {
            return "table-style-" + afspraak.getKleur();
        }
        return null;
    }


    public Component renderTekstHtmlDescription(DagboekEntry dagboekEntry) {

        Label label = uiComponents.create(Label.class);
        label.setHtmlEnabled(true);
        try {
            label.setValue(dagboekEntry.getTekst());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return label;
    }

    public Component renderHtmlDescription(Afspraak afspraak) {

        Label label = uiComponents.create(Label.class);
        label.setHtmlEnabled(true);
        try {
            label.setValue(afspraak.getDescription());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return label;
    }
}