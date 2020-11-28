package edu.pucmm.views;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.data.binder.ValidationResult;
import edu.pucmm.Service.GerenteService;
import edu.pucmm.model.Gerente;


public class UpdateGerente extends Dialog {

    private GerenteService gerenteService;

    private Gerente Actual;

    private Binder<Gerente> gerenteBinder = new Binder<Gerente>();

    public UpdateGerente(Gerente actual, GerenteService gerenteService) {
        this.gerenteService = gerenteService;
        this.Actual = actual;
        System.out.println(actual);
        gerenteBinder.readBean(actual);

        TextField Nombre = new TextField("Name", "Name Here");
        Nombre.setValue(actual.getName());

        EmailField Email = new EmailField("Email","Email Here");
        Email.setValue(actual.getEmail());

        gerenteBinder.forField(Nombre).asRequired().bind(Gerente::getName,Gerente::setName);
        gerenteBinder.forField(Email).asRequired().withValidator((event, valueContext)->{
            Gerente gerente = gerenteService.buscarPorEmail(event);
            if( gerente != null && gerente.getId() != actual.getId()) {
                return ValidationResult.error("Ese email ya se esta usando");
            }
            return ValidationResult.ok();
        }).bind(Gerente::getEmail,Gerente::setEmail);

        VerticalLayout v = new VerticalLayout();

        Button salvar = new Button("Save", event -> {
            System.out.println(gerenteBinder.isValid());
            Gerente gerente = new Gerente();
            try {
                gerenteBinder.writeBean(gerente);
                System.out.println(gerente);
                gerenteService.modificar(actual.getId(),gerente);
                close();
            } catch (ValidationException e) {
                e.printStackTrace();
            }
        });

        v.setAlignItems(FlexComponent.Alignment.CENTER);

        v.add(Nombre,Email,salvar);

        add(v);
    }
}
