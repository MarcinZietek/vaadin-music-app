package pl.mz.vadin.music.app.views.list;

import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import pl.mz.vadin.music.app.data.entity.Publisher;

import java.util.List;

public class Form extends FormLayout {

    TextField songTitle = new TextField("Song Title");
    TextField albumName = new TextField("Album Title");
    TextField region = new TextField("Album Region");
    ComboBox<Publisher> publisher = new ComboBox<>("Album Publisher");

    Button save = new Button("Save");
    Button delete = new Button("Delete");
    Button close = new Button("Cancel");

    public Form(List<Publisher> publishers) {
        addClassName("song-form");

        publisher.setItems(publishers);
        publisher.setItemLabelGenerator(Publisher::getName);

        add(songTitle,
            albumName,
            region,
            publisher,
            createButtonsLayout());
    }

    private HorizontalLayout createButtonsLayout(){
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        delete.addThemeVariants((ButtonVariant.LUMO_ERROR));
        close.addThemeVariants(ButtonVariant.LUMO_TERTIARY);

        save.addClickShortcut(Key.ENTER);
        close.addClickShortcut(Key.ESCAPE);

        return new HorizontalLayout(save, delete, close);
    }

}
