package edu.pucmm.views.event;


import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.datetimepicker.DateTimePicker;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.data.binder.ValidationResult;
import edu.pucmm.Service.EventoService;
import edu.pucmm.config.Utils;
import edu.pucmm.model.Event;
import edu.pucmm.views.Calendar.CalendarView;

import java.time.LocalDateTime;


public class EventModal extends Dialog {

    private EventoService eventoService;

    private Utils utils;

    private CalendarView calendarView;

    DateTimePicker start = new DateTimePicker();
    DateTimePicker end = new DateTimePicker();
    private Binder<Event> binder = new Binder<>(Event.class);


    public EventModal(CalendarView calendarView, Utils utils, EventoService eventoService) {
        this.calendarView = calendarView;
        this.utils = utils;
        this.eventoService = eventoService;
       Label insert = new Label("Event");
       VerticalLayout vl = new VerticalLayout();
       Label StartLabel = new Label("Start Date:");
       this.start.setId("start");
       StartLabel.setFor(start);
       Label errorStart = new Label("Select Date");


        Label EndLabel = new Label("End Date:");
        this.end.setId("end");
        EndLabel.setFor(end);

       TextField input = new TextField("Title:");
       input.setPlaceholder("title");
       binder.readBean(new Event("",LocalDateTime.now(),LocalDateTime.now()));

       this.binder.forField(input).asRequired().bind(Event::getTitle,Event::setTitle);

       this.binder.forField(this.start).asRequired().withValidator((event, valueContext) -> {
           if(this.end.getValue() != null && event != null){
               if(event.isAfter(this.end.getValue())){
                   return ValidationResult.error("Start Date should be before End Date");
               } else {
                   return ValidationResult.ok();
               }
           }
           return ValidationResult.ok();
       }).bind(Event::getStart,Event::setStart);

       this.binder.forField(this.end).asRequired().withValidator((event, valueContext ) ->{
            if(this.start.getValue() != null && event != null){
                if(event.isBefore(this.start.getValue())){
                    return ValidationResult.error("Start Date should be before End Date");
                } else {
                    return ValidationResult.ok();
                }
            }
            return ValidationResult.ok();
       }).bind(Event::getEnd,Event::setEnd);

       HorizontalLayout hl = new HorizontalLayout();
       Button save = new Button("Save");
       save.addClickListener(ev -> {
           Event event = new Event();
           try {
               binder.writeBean(event);
               this.calendarView.add(event);
           } catch (ValidationException e) {
               e.printStackTrace();
           }
           this.clean();
           close();
       });
       Button quit = new Button("Exit");
       quit.addClickListener(buttonClickEvent -> {
           this.clean();
           close();
       });
       hl.add(save,quit);

       vl.add(insert, input,StartLabel,start,errorStart,EndLabel, end, hl);
       vl.setAlignItems(FlexComponent.Alignment.CENTER);
       add(vl);
   }

   public void setDate(LocalDateTime date) {
        Event event = new Event("",date,date);
        binder.readBean(event);
   }

   private void clean() {
        Event c = new Event();
        binder.readBean(c);
   }

}
