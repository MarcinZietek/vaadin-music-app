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
import pl.mz.vadin.music.app.data.entity.Song;
import pl.mz.vadin.music.app.data.service.SongListService;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.stream.Collectors;

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
        form = new Form(Collections.emptyList());
        form.setWidth("25em");
    }

    private void configureGrid(){
        grid.addClassName("song-grid");
        grid.setSizeFull();
        grid.setColumns("title", "releasedDate", "region");
        grid.addColumn(album -> album.getPublisher().getName()).setHeader("Publisher");
        grid.addColumn(album -> album.getAlbumsTracks()).setHeader("Song");
        grid.getColumns().forEach(column -> column.setAutoWidth(true));
    }

    private HorizontalLayout getToolbar() {
        filterText.setPlaceholder("Filter by title...");
        filterText.setClearButtonVisible(true);
        filterText.setValueChangeMode(ValueChangeMode.LAZY);
        filterText.addValueChangeListener(e -> updateList());

        Button addContactButton = new Button("Add song");

        HorizontalLayout toolbar = new HorizontalLayout(filterText, addContactButton);
        toolbar.addClassName("toolbar");
        return toolbar;
    }

    private void updateList() {
        grid.setItems(songListService.findAllAlbums(filterText.getValue()));
    }
}
