package pl.mz.vadin.music.app.data.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;


@Entity
public class Song extends AbstractEntity{

    private String title;
    private Float duration;

    @ManyToMany
    private List<Producer> producerList = new ArrayList<>();
    @ManyToMany
    private List<Publisher> publisherList = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "albums_tracks",
    joinColumns = @JoinColumn (name= "song_id"),
    inverseJoinColumns = @JoinColumn(name = "album_id"))
    private Set<Album> albums;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Float getDuration() {
        return duration;
    }

    public void setDuration(Float duration) {
        this.duration = duration;
    }

    public List<Producer> getProducerList() {
        return producerList;
    }

    public void setProducerList(List<Producer> producerList) {
        this.producerList = producerList;
    }

    public List<Publisher> getPublisherList() {
        return publisherList;
    }

    public void setPublisherList(List<Publisher> publisherList) {
        this.publisherList = publisherList;
    }

    public Set<Album> getAlbums() {
        return albums;
    }

    public void setAlbums(Set<Album> albums) {
        this.albums = albums;
    }
}
