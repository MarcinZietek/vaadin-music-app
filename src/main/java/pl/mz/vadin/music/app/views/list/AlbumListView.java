package pl.mz.vadin.music.app.views.list;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import pl.mz.vadin.music.app.data.domain.MusicGenre;
import pl.mz.vadin.music.app.data.entity.Album;
import pl.mz.vadin.music.app.data.service.MusicAppService;

import java.util.Arrays;
import java.util.stream.Collectors;

@Route (value = "", layout = MainView.class)
@PageTitle("Albums")
@CssImport("./themes/styles.css")
public class AlbumListView extends VerticalLayout {

    Grid<Album> grid = new Grid<>(Album.class);
    TextField filterText = new TextField();
    MusicAppService musicAppService;

    AlbumForm albumForm;

    public AlbumListView(MusicAppService musicAppService) {
        this.musicAppService = musicAppService;
        addClassName("album-list-view");
        setSizeFull();
        configureGrid();
        configureForm();

        add(getToolbar(), getContent());
        updateList();
        closeEditor();
    }

    private Component getContent() {
        HorizontalLayout content = new HorizontalLayout(grid, albumForm);
        content.setFlexGrow(2, grid);
        content.setFlexGrow(1, albumForm);
        content.addClassNames("album-content");
        content.setSizeFull();
        return content;
    }

    private void configureForm() {
        albumForm = new AlbumForm(musicAppService.findAllPublishers(), Arrays.stream(MusicGenre.values()).collect(Collectors.toList()));
        albumForm.setWidth("25em");
        albumForm.addListener(AlbumForm.SaveEventAlbum.class, this::saveAlbum);
        albumForm.addListener(AlbumForm.DeleteEventAlbum.class, this::deleteAlbum);
        albumForm.addListener(AlbumForm.CloseEventAlbum.class, e -> closeEditor());
    }

    private void saveAlbum(AlbumForm.SaveEventAlbum event){
        musicAppService.saveAlbum(event.getAlbum());
        updateList();
        closeEditor();
    }

    private void deleteAlbum(AlbumForm.DeleteEventAlbum event){
        musicAppService.deleteAlbum(event.getAlbum());
        updateList();
        closeEditor();
    }

    private void configureGrid(){
        grid.addClassName("albums-grid");
        grid.setSizeFull();
        grid.setColumns("title", "releasedDate", "region");
        grid.addColumn(album -> album.getPublisher().getName()).setHeader("Publisher");
        grid.addColumn(album -> album.getMusicGenre().getName()).setHeader("Music genre");
        grid.getColumns().forEach(column -> column.setAutoWidth(true));

        grid.asSingleSelect().addValueChangeListener(event ->
                editAlbum(event.getValue()));
    }

    private HorizontalLayout getToolbar() {
        filterText.setPlaceholder("Filter by title...");
        filterText.setClearButtonVisible(true);
        filterText.setValueChangeMode(ValueChangeMode.LAZY);
        filterText.addValueChangeListener(e -> updateList());

        Button addAlbumButton = new Button("Add Album");
        addAlbumButton.addClickListener(click -> addAlbum());

        HorizontalLayout toolbar = new HorizontalLayout(filterText, addAlbumButton);
        toolbar.addClassName("toolbar");
        return toolbar;
    }

    public void editAlbum(Album album){
        if (album == null){
            closeEditor();
        } else {
          albumForm.setAlbum(album);
          albumForm.setVisible(true);
          addClassName("editing");
        }
    }

    private void closeEditor(){
        albumForm.setAlbum(null);
        albumForm.setVisible(false);
        removeClassName("editing");
    }

    private void addAlbum(){
        grid.asSingleSelect().clear();
        editAlbum(new Album());
    }

    private void updateList() {
        grid.setItems(musicAppService.findAllAlbums(filterText.getValue()));
    }
}
