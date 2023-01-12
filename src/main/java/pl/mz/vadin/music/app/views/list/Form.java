package pl.mz.vadin.music.app.views.list;

import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.shared.Registration;
import pl.mz.vadin.music.app.data.domain.MusicGenre;
import pl.mz.vadin.music.app.data.entity.Album;
import pl.mz.vadin.music.app.data.entity.Publisher;

import java.util.List;

public class Form extends FormLayout {

    private Album album;
    TextField title = new TextField("Album Title");
    TextField region = new TextField("Album Region");
    ComboBox<Publisher> publisher = new ComboBox<>("Album Publisher");
    ComboBox<MusicGenre> musicGenre = new ComboBox<>("Music Genre");

    Binder<Album> binder = new BeanValidationBinder<>(Album.class);

    Button save = new Button("Save");
    Button delete = new Button("Delete");
    Button close = new Button("Cancel");

    public Form(List<Publisher> publishers, List<MusicGenre> musicGenres) {
        addClassName("song-form");
        binder.bindInstanceFields(this);

        publisher.setItems(publishers);
        publisher.setItemLabelGenerator(Publisher::getName);

        musicGenre.setItems(musicGenres);
        musicGenre.setItemLabelGenerator(MusicGenre::getName);

        add(title,
            region,
            publisher,
            musicGenre,
            createButtonsLayout());
    }

    private HorizontalLayout createButtonsLayout(){
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        delete.addThemeVariants((ButtonVariant.LUMO_ERROR));
        close.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
        save.addClickShortcut(Key.ENTER);
        close.addClickShortcut(Key.ESCAPE);

        save.addClickListener(event -> validateAndSave());
        delete.addClickListener(event -> fireEvent(new DeleteEvent(this, album)));
        close.addClickListener(event -> fireEvent(new CloseEvent(this)));

        binder.addStatusChangeListener(e -> save.setEnabled(binder.isValid()));

        return new HorizontalLayout(save, delete, close);
    }

    private void validateAndSave(){
        try{
            binder.writeBean(album);
            fireEvent(new SaveEvent(this, album));
        }catch (ValidationException e){
            e.printStackTrace();
        }
    }

    public void setAlbum(Album album){
        this.album = album;
        binder.readBean(album);
    }



    public static abstract class FormEvent extends ComponentEvent<Form>{
        private Album album;

        protected FormEvent(Form source, Album album){
            super(source, false);
            this.album = album;
        }

        public Album getAlbum() {
            return album;
        }
    }

    public static class SaveEvent extends FormEvent{
        SaveEvent(Form source, Album album){
            super(source, album);
        }
    }

    public static class DeleteEvent extends FormEvent{
        DeleteEvent(Form source, Album album){
            super(source, album);
        }
    }

    public static class CloseEvent extends FormEvent{
        CloseEvent(Form source){
            super(source, null);
        }
    }

    public <T extends ComponentEvent<?>>Registration addListener(Class<T> eventType, ComponentEventListener<T> listener){
        return getEventBus().addListener(eventType, listener);
    }

}
