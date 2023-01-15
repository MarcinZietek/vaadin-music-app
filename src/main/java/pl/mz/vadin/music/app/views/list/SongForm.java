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
import pl.mz.vadin.music.app.data.entity.Album;
import pl.mz.vadin.music.app.data.entity.Song;

import java.util.List;

public class SongForm extends FormLayout {

    private Song song;


    TextField title = new TextField("Song Title");
    TextField duration = new TextField("Song Duration");
    ComboBox<Album> album = new ComboBox<>("Album Name");
    Binder<Song> songBinder = new BeanValidationBinder<>(Song.class);

    Button save = new Button("Save");
    Button delete = new Button("Delete");
    Button close = new Button("Cancel");

    public SongForm(List<Album> albums) {
        addClassName("song-form");
        songBinder.bindInstanceFields(this);

        album.setItems(albums);
        album.setItemLabelGenerator(Album::getTitle);

        add(title,
            duration,
            album,
            createButtonsLayout());
    }

    private HorizontalLayout createButtonsLayout(){
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        delete.addThemeVariants((ButtonVariant.LUMO_ERROR));
        close.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
        save.addClickShortcut(Key.ENTER);
        close.addClickShortcut(Key.ESCAPE);

        save.addClickListener(event -> validateAndSave());

        delete.addClickListener(event -> fireEvent(new DeleteEventSong(this, song)));
        close.addClickListener(event -> fireEvent(new CloseEventSong(this)));

        songBinder.addStatusChangeListener(e -> save.setEnabled(songBinder.isValid()));

        return new HorizontalLayout(save, delete, close);
    }

    private void validateAndSave(){
        try {
            songBinder.writeBean(song);
            fireEvent(new SaveEventSong(this, song));
        }catch (ValidationException e){
            e.printStackTrace();
        }
    }

    public void setSong(Song song){
        this.song = song;
        songBinder.readBean(song);
    }

    public static abstract class FormEvent extends ComponentEvent<SongForm> {
        private Song song;

        protected FormEvent(SongForm source, Song song){
            super(source, false);
            this.song = song;
        }

        public Song getSong() { return song; }
    }

    public static class SaveEventSong extends FormEvent {
        SaveEventSong(SongForm source, Song song){
            super(source, song);
        }
    }

    public static class DeleteEventSong extends FormEvent {
        DeleteEventSong(SongForm source, Song song){
            super(source, song);
        }
    }

    public static class CloseEventSong extends FormEvent {
        CloseEventSong(SongForm source){
            super(source, null);
        }
    }

    public <T extends ComponentEvent<?>> Registration addListener(Class<T> eventType, ComponentEventListener<T> listener){
        return getEventBus().addListener(eventType, listener);
    }
}
