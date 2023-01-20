package pl.mz.vadin.music.app.data.components;

import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;

public class NotificationSuccess extends Dialog {

    public void AddAlbumNotificationSuccess() {
        Notification notification = Notification
                .show("Album został zapisany!");
        notification.addThemeVariants(NotificationVariant.LUMO_SUCCESS);
        notification.setPosition(Notification.Position.BOTTOM_STRETCH);
    }

    public void AddSongNotificationSuccess(){
        Notification notification = Notification
                .show("Piosenka została zapisana!");
        notification.addThemeVariants(NotificationVariant.LUMO_SUCCESS);
        notification.setPosition(Notification.Position.BOTTOM_STRETCH);
    }

    public void DeleteAlbumNotificationSuccess(){
        Notification notification = Notification
                .show("Album został usunięty!");
        notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
        notification.setPosition(Notification.Position.BOTTOM_STRETCH);
    }

    public void DeleteSongNotificationSuccess(){
        Notification notification = Notification
                .show("Piosenka została usnięta!");
        notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
        notification.setPosition(Notification.Position.BOTTOM_STRETCH);
    }

}
