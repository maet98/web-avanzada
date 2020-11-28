package edu.pucmm.views.gerente;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.data.provider.DataProvider;
import com.vaadin.flow.data.renderer.NativeButtonRenderer;
import com.vaadin.flow.data.validator.EmailValidator;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import edu.pucmm.Service.EmailSender;
import edu.pucmm.Service.GerenteService;
import com.vaadin.flow.data.binder.Binder;
import edu.pucmm.model.Gerente;
import edu.pucmm.views.Calendar.CalendarView;
import edu.pucmm.views.main.MainView;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.PageRequest;

import java.io.IOException;

@Route(value = "Gerente", layout = MainView.class)
@PageTitle("Gerente")
public class GerenteView extends VerticalLayout {
    @Autowired
    GerenteService gerenteService;

    @Autowired
    private EmailSender emailSender;

    Grid<Gerente> tablaGerente;
    Button btnAgregar, btnEliminar;
    TextField Email;
    TextField Name;
    PasswordField Password;
    Binder<Gerente> binder;
    DataProvider<Gerente,Void> dataProvider;
    Gerente gerenteSelected;


    public GerenteView() {

        //Instanciando el dato provider.
        dataProvider = DataProvider.fromCallbacks(
                //indicando la consulta que retorna la información
                query -> {
                    // Indicando el primer elemento cargado.
                    int offset = query.getOffset();
                    System.out.println("El offset: "+offset);
                    // La cantidad maxima a cargar
                    int limit = query.getLimit();
                    System.out.println("El limit: "+limit);
                    //Enviando el flujo
                    return gerenteService.listaGerente(PageRequest.of(offset, limit));
                },
                query -> {
                    //Indicando la cantidad maxima de elementos.
                    return Math.toIntExact(gerenteService.cantidadGerente());
                }
        );
        //instanciando el binder.
        binder = new Binder<>();


        //La tabla
        tablaGerente = new Grid<>();
        tablaGerente.setDataProvider(dataProvider);
        tablaGerente.addColumn(Gerente::getEmail).setHeader("Email");
        tablaGerente.addColumn(Gerente::getName).setHeader("Nombre");
        tablaGerente.addColumn(new NativeButtonRenderer<Gerente>("Elminiar", e->{
            Notification.show("Eliminando el registro: "+e.getName());
            gerenteService.borrarGerente(e);
            dataProvider.refreshAll();
        })).setHeader("Acciones");

        //evento de la tabla
        tablaGerente.addSelectionListener(s->{
            if(s.getFirstSelectedItem().isPresent()){
                gerenteSelected = s.getFirstSelectedItem().get();
                binder.readBean(gerenteSelected);
                btnEliminar.setEnabled(true);
            }else{
                Name.clear();
                Email.clear();
                Password.clear();
                btnEliminar.setEnabled(false);
            }
        });

        //
        tablaGerente.setWidth("70%");

        //los campos.
        Email=new TextField("Email");
        Name=new TextField("Nombre");
        Password = new PasswordField("Password");
        btnAgregar = new Button("Agregar", e->{
            try {
                Gerente tempGerente = new Gerente();
                binder.writeBean(tempGerente);
                tempGerente.setRol("ROLE_GERENTE");
                gerenteService.crearGerentes(tempGerente);
                dataProvider.refreshAll();
            }catch (ValidationException ex){
                Notification.show("Error...: "+ex.getMessage());
            }
            Gerente gerente = new Gerente();
            binder.readBean(gerente);
        });

        btnEliminar = new Button("Eliminar", e->{
            gerenteService.borrarGerente(gerenteSelected);
            dataProvider.refreshAll();
        });
        btnEliminar.setEnabled(false);

        //referencia al binder.
        binder.forField(Email)
                .asRequired("Debe indicar el email")
                .withValidator( new EmailValidator("Este email no esta bien."))
                .bind(Gerente::getEmail,Gerente::setEmail);

        binder.forField(Name).asRequired("Debe indicar un nombre")
                .bind(Gerente::getName, Gerente::setName);

        binder.forField(Password)
                .asRequired("Debe indicar la contrasena")
                .bind(Gerente::getPassword,Gerente::setPassword);

        //layout para formularios.
        FormLayout fl = new FormLayout(Name, Email, Password);
        HorizontalLayout accionesForm = new HorizontalLayout(btnAgregar, btnEliminar);
        VerticalLayout vfl = new VerticalLayout(fl, accionesForm);

        //agregando el diseño.
        HorizontalLayout hz = new HorizontalLayout(tablaGerente, vfl);
        hz.setSizeFull();

        Button send = new Button("Send", event -> {
            try {
                emailSender.sendEmail("no-reply@em9523.miguelestevez.xyz ","maletaveras@gmail.com","Hola","Prueba");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        hz.add(send);

        add(hz);
        setSizeFull();
        //refrescando la tabla.
        dataProvider.refreshAll();
    }

}
