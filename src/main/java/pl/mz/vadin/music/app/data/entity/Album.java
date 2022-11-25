package pl.mz.vadin.music.app.data.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Entity
public class Album extends AbstractEntity{

    private String title;

    private LocalDate releasedDate;

    private String region;

    @ManyToMany
    @JoinTable(name = "albums_tracks", joinColumns = @JoinColumn(name = "album_id"),
            inverseJoinColumns = @JoinColumn(name = "tracks_id") )
    private List<Song> albumsTracks = new ArrayList<>();

    @ManyToOne
    private Publisher publisher;

    @ManyToMany
    private List<Performer> performerList = new ArrayList<>();

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDate getReleasedDate() {
        return releasedDate;
    }

    public void setReleasedDate(LocalDate releasedDate) {
        this.releasedDate = releasedDate;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public List<Song> getAlbumsTracks() {
        return albumsTracks;
    }

    public void setAlbumsTracks(List<Song> albumsTracks) {
        this.albumsTracks = albumsTracks;
    }

    public Publisher getPublisher() {
        return publisher;
    }

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }

    public List<Performer> getPerformerList() {
        return performerList;
    }

    public void setPerformerList(List<Performer> performerList) {
        this.performerList = performerList;
    }
}
