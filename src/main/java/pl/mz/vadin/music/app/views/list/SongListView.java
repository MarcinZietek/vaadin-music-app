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
import pl.mz.vadin.music.app.data.entity.Album;
import pl.mz.vadin.music.app.data.service.SongListService;

import java.util.Collections;

@Route (value = "")
@PageTitle("Songs")
public class SongListView extends VerticalLayout {

    Grid<Album> grid = new Grid<>(Album.class);
    TextField filterText = new TextField();
    SongListService songListService;

    Form form;

    public SongListView(SongListService songListService) {
        this.songListService = songListService;
        addClassName("song-list-view");
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
        form = new Form(songListService.findAllPublishers());
        form.setWidth("25em");
        form.addListener(Form.SaveEvent.class, this::saveAlbum);
        form.addListener(Form.DeleteEvent.class, this::deleteAlbum);
        form.addListener(Form.CloseEvent.class, e -> closeEditor());
    }

    private void saveAlbum(Form.SaveEvent event){
        songListService.saveAlbum(event.getAlbum());
        updateList();
        closeEditor();
    }

    private void deleteAlbum(Form.DeleteEvent event){
        songListService.deleteAlbum(event.getAlbum());
        updateList();
        closeEditor();
    }

    private void configureGrid(){
        grid.addClassName("song-grid");
        grid.setSizeFull();
        grid.setColumns("title", "releasedDate", "region");
        grid.addColumn(album -> album.getPublisher().getName()).setHeader("Publisher");
        grid.addColumn(album -> album.getAlbumsTracks()).setHeader("Song");
        grid.getColumns().forEach(column -> column.setAutoWidth(true));

        grid.asSingleSelect().addValueChangeListener(event ->
                editAlbum(event.getValue()));
    }

    private HorizontalLayout getToolbar() {
        filterText.setPlaceholder("Filter by title...");
        filterText.setClearButtonVisible(true);
        filterText.setValueChangeMode(ValueChangeMode.LAZY);
        filterText.addValueChangeListener(e -> updateList());

        Button addAlbumButton = new Button("Add song");
        addAlbumButton.addClickListener(click -> addAlbum());

        HorizontalLayout toolbar = new HorizontalLayout(filterText, addAlbumButton);
        toolbar.addClassName("toolbar");
        return toolbar;
    }

    public void editAlbum(Album album){
        if (album == null){
            closeEditor();
        } else {
          form.setAlbum(album);
          form.setVisible(true);
        }
    }

    private void closeEditor(){
        form.setAlbum(null);
        form.setVisible(false);
        removeClassName("editing");
    }

    private void addAlbum(){
        grid.asSingleSelect().clear();
        editAlbum(new Album());
    }

    private void updateList() {
        grid.setItems(songListService.findAllAlbums(filterText.getValue()));
    }
}
