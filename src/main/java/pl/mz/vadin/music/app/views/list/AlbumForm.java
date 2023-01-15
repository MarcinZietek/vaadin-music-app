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

public class AlbumForm extends FormLayout {

    private Album album;

    TextField title = new TextField("Album Title");
    TextField region = new TextField("Album Region");
    ComboBox<Publisher> publisher = new ComboBox<>("Album Publisher");
    ComboBox<MusicGenre> musicGenre = new ComboBox<>("Music Genre");
    Binder<Album> albumBinder = new BeanValidationBinder<>(Album.class);

    Button save = new Button("Save");
    Button delete = new Button("Delete");
    Button close = new Button("Cancel");

    public AlbumForm(List<Publisher> publishers, List<MusicGenre> musicGenres) {
        addClassName("song-form");
        albumBinder.bindInstanceFields(this);

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
        delete.addClickListener(event -> fireEvent(new DeleteEventAlbum(this, album)));
        close.addClickListener(event -> fireEvent(new CloseEventAlbum(this)));

        albumBinder.addStatusChangeListener(e -> save.setEnabled(albumBinder.isValid()));

        return new HorizontalLayout(save, delete, close);
    }

    private void validateAndSave(){
        try{
            albumBinder.writeBean(album);
            fireEvent(new SaveEventAlbum(this, album));
        }catch (ValidationException e){
            e.printStackTrace();
        }
    }

    public void setAlbum(Album album){
        this.album = album;
        albumBinder.readBean(album);
    }

    public static abstract class FormEvent extends ComponentEvent<AlbumForm>{
        private Album album;

        protected FormEvent(AlbumForm source, Album album){
            super(source, false);
            this.album = album;
        }

        public Album getAlbum() {
            return album;
        }

    }

    public static class SaveEventAlbum extends FormEvent{
        SaveEventAlbum(AlbumForm source, Album album){
            super(source, album);
        }
    }

    public static class DeleteEventAlbum extends FormEvent{
        DeleteEventAlbum(AlbumForm source, Album album){
            super(source, album);
        }
    }

    public static class CloseEventAlbum extends FormEvent{
        CloseEventAlbum(AlbumForm source){
            super(source, null);
        }
    }

    public <T extends ComponentEvent<?>>Registration addListener(Class<T> eventType, ComponentEventListener<T> listener){
        return getEventBus().addListener(eventType, listener);
    }

}
