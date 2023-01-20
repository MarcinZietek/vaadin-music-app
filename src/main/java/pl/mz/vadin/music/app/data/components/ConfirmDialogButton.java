package pl.mz.vadin.music.app.data.components;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.confirmdialog.ConfirmDialog;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;

public class ConfirmDialogButton extends Dialog {

    private Span status;

    public ConfirmDialogButton() {
        HorizontalLayout layout = new HorizontalLayout();
        layout.setAlignItems(FlexComponent.Alignment.CENTER);
        layout.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);

        status = new Span();
        status.setVisible(false);

        ConfirmDialog dialog = new ConfirmDialog();
        dialog.setHeader("Usuwasz \"Album\"?");
        dialog.setText(
                "Czy jesteś pewnien że chcesz usunąć wybrany Album razem z piosenkami?");

        dialog.setCancelable(true);
        dialog.addCancelListener(event -> setStatus("Canceled"));

        dialog.setConfirmText("Delete");
        dialog.setConfirmButtonTheme("error primary");
        dialog.addConfirmListener(event -> setStatus("Deleted"));

        Button button = new Button("Open confirm dialog");
        button.addClickListener(event -> {
            dialog.open();
            status.setVisible(false);
        });

        layout.add(button, status);
        add(layout);
    }

    private void setStatus(String value) {
        status.setText("Status: " + value);
        status.setVisible(true);
    }
}
