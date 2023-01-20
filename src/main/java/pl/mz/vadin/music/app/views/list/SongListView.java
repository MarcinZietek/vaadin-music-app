package pl.mz.vadin.music.app.views.list;


import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import pl.mz.vadin.music.app.data.components.NotificationSuccess;
import pl.mz.vadin.music.app.data.entity.Song;
import pl.mz.vadin.music.app.data.service.MusicAppService;

@Route(value = "songs", layout = MainView.class)
@PageTitle("List of Songs")
public class SongListView extends VerticalLayout {

    Grid<Song> grid = new Grid<>(Song.class);
    TextField filterText = new TextField();
    MusicAppService musicAppService;
    SongForm songForm;
    NotificationSuccess dialog = new NotificationSuccess();

    public SongListView(MusicAppService musicAppService) {
        this.musicAppService = musicAppService;
        addClassName("song-list-view");
        setSizeFull();
        configureGrid();
        configureForm();

        add(getToolbar(), getContent());
        updateList();
        closeEditor();
    }

    private Component getContent() {
        HorizontalLayout content = new HorizontalLayout(grid, songForm);
        content.setFlexGrow(2, grid);
        content.setFlexGrow(1, songForm);
        content.addClassNames("song-content");
        content.setSizeFull();
        return content;
    }

    private void configureForm() {
        songForm = new SongForm(musicAppService.findAlbums());
        songForm.setWidth("25em");
        songForm.addListener(SongForm.SaveEventSong.class, this::saveSong);
        songForm.addListener(SongForm.DeleteEventSong.class, this::deleteSong);
        songForm.addListener(SongForm.CloseEventSong.class, e -> closeEditor());
    }

    private void configureGrid(){
        grid.addClassName("songs-grid");
        grid.setSizeFull();
        grid.setColumns("title", "duration");
        grid.addColumn(song -> song.getAlbum().getTitle()).setHeader("Album Name");
        grid.addColumn(song -> song.getAlbum().getRegion()).setHeader("Album Region");
        grid.getColumns().forEach(column -> column.setAutoWidth(true));

        grid.asSingleSelect().addValueChangeListener(event ->
                editSong(event.getValue()));
    }

    private HorizontalLayout getToolbar() {
        filterText.setPlaceholder("Filter song by title...");
        filterText.setClearButtonVisible(true);
        filterText.setValueChangeMode(ValueChangeMode.LAZY);
        filterText.addValueChangeListener(e -> updateList());

        Button addAlbumButton = new Button("Add Song");
        addAlbumButton.addClickListener(click -> addSong());
        HorizontalLayout toolbar = new HorizontalLayout(filterText, addAlbumButton);
        toolbar.addClassName("toolbar");
        return toolbar;
    }

    private void saveSong(SongForm.SaveEventSong event){
        musicAppService.saveSong(event.getSong());
        dialog.AddSongNotificationSuccess();
        updateList();
        closeEditor();
    }

    private void deleteSong(SongForm.DeleteEventSong event){
        musicAppService.deleteSong(event.getSong());
        dialog.DeleteSongNotificationSuccess();
        updateList();
        closeEditor();
    }

    public void editSong(Song song){
        if (song == null){
            closeEditor();
        } else {
            songForm.setSong(song);
            songForm.setVisible(true);
            addClassName("editing");
        }
    }

    private void closeEditor(){
        songForm.setSong(null);
        songForm.setVisible(false);
        removeClassName("editing");
    }

    private void addSong(){
        grid.asSingleSelect().clear();
        editSong(new Song());
    }

    private void updateList() {
        grid.setItems(musicAppService.findAllSongs(filterText.getValue()));
    }

}
