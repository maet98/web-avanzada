package edu.pucmm.views.Calendar;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import edu.pucmm.Service.EventoService;
import edu.pucmm.Service.GerenteService;
import edu.pucmm.config.Utils;
import edu.pucmm.model.Event;
import edu.pucmm.model.Gerente;
import edu.pucmm.views.UpdateGerente;
import edu.pucmm.views.event.EventModal;
import edu.pucmm.views.main.MainView;
import org.atmosphere.interceptor.AtmosphereResourceStateRecovery;
import org.hibernate.sql.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.stefan.fullcalendar.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

@Route(value = "", layout = MainView.class)
@PageTitle("Calendar")
@CssImport("./styles/views/about/about-view.css")
public class CalendarView extends VerticalLayout {

    private EventoService eventoService;

    private GerenteService gerenteService;

    private Utils utils;

    public FullCalendar calendar = FullCalendarBuilder.create().withAutoBrowserTimezone().withScheduler().build();

    private void uploadEvent() {
        Gerente gerente = utils.getCurrentUser();
        if(gerente != null){
            for(Event evento: eventoService.findAllByGerente(gerente.getEmail())) {
                addToCalendar(evento);
            }
        }
    }

    public void add(Event event) {
        event.setGerente(utils.getCurrentUser());
        this.eventoService.create(event);
        addToCalendar(event);
    }

    public void addToCalendar(Event event) {
        Entry entry = new Entry();
        System.out.println(event.getStart());
        System.out.println(this.calendar.getTimezone().convertToUTC(event.getStart()));
        entry.setStart(this.calendar.getTimezone().convertToUTC(event.getStart()));
        entry.setEnd(this.calendar.getTimezone().convertToUTC(event.getEnd()));
        entry.setTitle(event.getTitle());
        calendar.addEntry(entry);
    }

    public CalendarView(@Autowired GerenteService gerenteService, @Autowired Utils utils, @Autowired EventoService eventoService) {
        ((Scheduler) calendar).setSchedulerLicenseKey("CC-Attribution-NonCommercial-NoDerivatives");
        this.gerenteService = gerenteService;
        this.utils = utils;
        this.eventoService = eventoService;
        calendar.setTimezone(Timezone.getSystem());
        Button nextButton = new Button();
        nextButton.setIcon(VaadinIcon.ARROW_RIGHT.create());
        Span intervalLabel = new Span();

        // combo box to select a view for the calendar, like "monthly", "weekly", ...
        ComboBox<org.vaadin.stefan.fullcalendar.CalendarView> viewBox = new ComboBox<>("", CalendarViewImpl.values());
        viewBox.addValueChangeListener(e -> {
            org.vaadin.stefan.fullcalendar.CalendarView value = e.getValue();
            calendar.changeView(value == null ? CalendarViewImpl.DAY_GRID_MONTH : value);
        });

        viewBox.setValue(CalendarViewImpl.DAY_GRID_MONTH);


        calendar.addDatesRenderedListener(event ->{
            LocalDate intervalStart = event.getIntervalStart();
            org.vaadin.stefan.fullcalendar.CalendarView cView = viewBox.getValue();

            String formattedInterval = intervalStart.format(DateTimeFormatter.ofPattern("MMMM YYYY"));

            intervalLabel.setText(formattedInterval);
        });
        nextButton.addClickListener(buttonClickEvent ->{
            calendar.next();
        } );

        Button previous = new Button();
        previous.setIcon(VaadinIcon.ARROW_LEFT.create());
        previous.addClickListener(buttonClickEvent -> {
            calendar.previous();
        });

        EventModal eventModal = new EventModal(this,utils,eventoService);

        Button addButton = new Button();
        addButton.setIcon(VaadinIcon.PLUS.create());
        addButton.addClickListener(event ->{
            eventModal.open();
        });

        HorizontalLayout header = new HorizontalLayout();
        header.setAlignItems(Alignment.CENTER);
        header.add(previous,nextButton, intervalLabel, addButton);
        header.setVerticalComponentAlignment(Alignment.END,addButton);
        addButton.setClassName("addButton");

        UpdateGerente updateGerente = new UpdateGerente(utils.getCurrentUser(),gerenteService);

        Button cambiar = new Button("Cambiar", event -> {
            updateGerente.open();
        });

        add(header,calendar);

        calendar.addTimeslotClickedListener(event -> {
            System.out.println(event.getDateTime());
            eventModal.setDate(event.getDateTime());
            eventModal.open();
        });

        Button check = new Button("check", event ->{
            System.out.println(this.eventoService.notSended());
        });
        add(cambiar);


        this.uploadEvent();
    }

}
