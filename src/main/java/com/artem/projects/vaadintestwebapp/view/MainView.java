package com.artem.projects.vaadintestwebapp.view;

import com.artem.projects.vaadintestwebapp.data.Number;
import com.artem.projects.vaadintestwebapp.data.NumberService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.router.Route;
import lombok.extern.slf4j.Slf4j;


@Route
@Slf4j
public class MainView extends VerticalLayout {
    private final TextField textField;
    private final Button button;
    private Binder<Number> binder;
    private final NumberService numberService;

    public MainView(NumberService numberService) {
        this.numberService = numberService;
        this.textField = new TextField();
        this.binder = new Binder<>();
        this.button = new Button("Increment", VaadinIcon.PLUS.create());
        Number number = new Number(1, 0);

        binder.setBean(number);
        binder.forField(textField)
                .asRequired()
                .withConverter(
                        Long::valueOf,
                        String::valueOf,
                        "Enter a number")
                .bind(Number::getNumber, Number::setNumber);

        button.addClickListener(e -> {
            if (binder.validate().isOk()) {
                long incNumber = Long.parseLong(textField.getValue()) + 1;
                textField.setValue(String.valueOf(incNumber));
            }
        });

        textField.addValueChangeListener(e -> {
            log.info(number.toString());
            numberService.saveNumber(number);
        });
        add(textField, button);
    }
}
