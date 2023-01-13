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
import pl.mz.vadin.music.app.data.domain.MusicGenre;
import pl.mz.vadin.music.app.data.entity.Song;
import pl.mz.vadin.music.app.data.service.MusicAppService;

import java.util.Arrays;
import java.util.stream.Collectors;

@Route(value = "songs", layout = MainView.class)
@PageTitle("List of Songs")
public class SongListView extends VerticalLayout {

    Grid<Song> grid = new Grid<>(Song.class);

    TextField filterText = new TextField();
    private final MusicAppService musicAppService;

    Form form;

    public SongListView(MusicAppService musicAppService) {
        this.musicAppService = musicAppService;
        addClassName("songs-view");
        setSizeFull();
        configureGrid();
        configureForm();

        add(getToolbar(), getContent());
        updateList();
        closeEditor();
    }

    private Component getContent() {
        HorizontalLayout content = new HorizontalLayout(grid, form);
        content.setFlexGrow(2, grid);
        content.setFlexGrow(1, form);
        content.addClassNames("content");
        content.setSizeFull();
        return content;
    }

    private void configureForm() {
        form = new Form(musicAppService.findAllPublishers(), Arrays.stream(MusicGenre.values()).collect(Collectors.toList()));
        form.setWidth("25em");
        form.addListener(Form.SaveEventSong.class, this::saveSong);
        form.addListener(Form.DeleteEventSong.class, this::deleteSong);
        form.addListener(Form.CloseEventSong.class, e -> closeEditor());
    }

    private void configureGrid(){
        grid.addClassName("songs-grid");
        grid.setSizeFull();
        grid.setColumns("title", "duration");
        grid.addColumn(song -> song.getAlbums().toString()).setHeader("Album");
//        grid.addColumn(song -> song.getMusicGenre()).setHeader("Music Genre");
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

    private void saveSong(Form.SaveEventSong event){
        musicAppService.saveSong(event.getSong());
        updateList();
        closeEditor();
    }

    private void deleteSong(Form.DeleteEventSong event){
        musicAppService.deleteSong(event.getSong());
        updateList();
        closeEditor();
    }

    public void editSong(Song song){
        if (song == null){
            closeEditor();
        } else {
            form.setSong(song);
            form.setVisible(true);
        }
    }

    private void closeEditor(){
        form.setSong(null);
        form.setVisible(false);
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
