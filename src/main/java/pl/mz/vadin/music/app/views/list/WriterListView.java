package pl.mz.vadin.music.app.views.list;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import pl.mz.vadin.music.app.data.entity.Writer;
import pl.mz.vadin.music.app.data.service.WriterServiceImpl;

@Route(value = "/writers")
@PageTitle("Writers")
public class WriterListView extends VerticalLayout {

    Grid<Writer> grid = new Grid<>(Writer.class);
    TextField filterText = new TextField();
    WriterServiceImpl writerService;

    public WriterListView(WriterServiceImpl writerService) {
        this.writerService = writerService;
        addClassName("writer-list-view");
        setSizeFull();
        configureGrid();

        add(getToolbar(), grid);
        updateList();
    }

    private void configureGrid(){
        grid.addClassName("writer-grid");
        grid.setSizeFull();
        grid.setColumns("id", "name");
        grid.addColumn(writer -> writer.getId()).setHeader("Id");
        grid.addColumn(writer -> writer.getName()).setHeader("Name");
        grid.getColumns().forEach(column -> column.setAutoWidth(true));
    }

    private HorizontalLayout getToolbar() {
        filterText.setPlaceholder("Filter by title...");
        filterText.setClearButtonVisible(true);
        filterText.setValueChangeMode(ValueChangeMode.LAZY);
        filterText.addValueChangeListener(e -> updateList());

        Button addContactButton = new Button("Add writer");

        HorizontalLayout toolbar = new HorizontalLayout(filterText, addContactButton);
        toolbar.addClassName("toolbar");
        return toolbar;
    }

    private void updateList(){
        grid.setItems(writerService.findAllWriters(filterText.getValue()));
    }
}
