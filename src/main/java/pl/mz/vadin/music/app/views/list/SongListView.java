package pl.mz.vadin.music.app.views.list;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import pl.mz.vadin.music.app.data.entity.Song;
import pl.mz.vadin.music.app.data.service.SongListService;

@Route (value = "/songs")
@PageTitle("Songs")
public class SongListView extends VerticalLayout {

    Grid<Song> grid = new Grid<>(Song.class);
    TextField filterText = new TextField();
    SongListService songListService;

    public SongListView(SongListService songListService) {
        this.songListService = songListService;
        addClassName("song-list-view");
        setSizeFull();
        configureGrid();

        add(getToolbar(), grid);
        updateList();
    }


    private void configureGrid(){
        grid.addClassName("song-grid");
        grid.setSizeFull();
        grid.setColumns("title", "duration");
//        grid.addColumn(song -> song.getTitle()).setHeader("Title");
//        grid.addColumn(song -> song.getAlbum()).setHeader("Album Name");
//        grid.addColumn(song -> song.getProducerList()).setHeader("Producer");
//        grid.addColumn(publisher -> publisher.getPublisherList()).setHeader("Publisher");
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
        grid.setItems(songListService.findAllSongs(filterText.getValue()));
    }
}
