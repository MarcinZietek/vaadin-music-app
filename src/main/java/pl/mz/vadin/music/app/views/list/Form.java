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
import pl.mz.vadin.music.app.data.entity.Song;

import java.util.List;

public class Form extends FormLayout {

    private Album album;

    private Song song;
    TextField title = new TextField("Album Title");
    TextField region = new TextField("Album Region");
    ComboBox<Publisher> publisher = new ComboBox<>("Album Publisher");
    ComboBox<MusicGenre> musicGenre = new ComboBox<>("Music Genre");

    Binder<Album> albumBinder = new BeanValidationBinder<>(Album.class);
    Binder<Song> songBinder = new BeanValidationBinder<>(Song.class);

    Button save = new Button("Save");
    Button delete = new Button("Delete");
    Button close = new Button("Cancel");

    public Form(List<Publisher> publishers, List<MusicGenre> musicGenres) {
        addClassName("song-form");
        albumBinder.bindInstanceFields(this);
        songBinder.bindInstanceFields(this);

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

        delete.addClickListener(event -> fireEvent(new DeleteEventSong(this, song)));
        close.addClickListener(event -> fireEvent(new CloseEventSong(this)));

        albumBinder.addStatusChangeListener(e -> save.setEnabled(albumBinder.isValid()));
        songBinder.addStatusChangeListener(e -> save.setEnabled(songBinder.isValid()));

        return new HorizontalLayout(save, delete, close);
    }

    private void validateAndSave(){
        try{
            albumBinder.writeBean(album);
            fireEvent(new SaveEventAlbum(this, album));
        }catch (ValidationException e){
            e.printStackTrace();
        }
        try {
            songBinder.writeBean(song);
            fireEvent(new SaveEventSong(this, song));
        }catch (ValidationException ex){
            ex.printStackTrace();
        }
    }

    public void setAlbum(Album album){
        this.album = album;
        albumBinder.readBean(album);
    }

    public void setSong(Song song){
        this.song = song;
        songBinder.readBean(song);
    }

    public static abstract class FormEvent extends ComponentEvent<Form>{
        private Album album;
        private Song song;

        protected FormEvent(Form source, Album album){
            super(source, false);
            this.album = album;
        }
        protected FormEvent(Form source, Song song){
            super(source, false);
            this.song = song;
        }

        public Album getAlbum() {
            return album;
        }

        public Song getSong() { return song; }
    }

    public static class SaveEventAlbum extends FormEvent{
        SaveEventAlbum(Form source, Album album){
            super(source, album);
        }
    }
    public static class SaveEventSong extends FormEvent{
        SaveEventSong(Form source, Song song){
            super(source, song);
        }
    }

    public static class DeleteEventAlbum extends FormEvent{
        DeleteEventAlbum(Form source, Album album){
            super(source, album);
        }
    }

    public static class DeleteEventSong extends FormEvent{
        DeleteEventSong(Form source, Song song){
            super(source, song);
        }
    }

    public static class CloseEventAlbum extends FormEvent{
        CloseEventAlbum(Form source){
            super(source, (Album) null);
        }
    }

    public static class CloseEventSong extends FormEvent{
        CloseEventSong(Form source){
            super(source, (Song) null);
        }
    }

    public <T extends ComponentEvent<?>>Registration addListener(Class<T> eventType, ComponentEventListener<T> listener){
        return getEventBus().addListener(eventType, listener);
    }



}
