package pl.mz.vadin.music.app.data.entity;

import javax.persistence.*;
import java.util.*;


@Entity
public class Song extends AbstractEntity{

    private String title;
    private Float duration;

    @ManyToMany
    private List<Producer> producerList = new ArrayList<>();

    @ManyToMany
    private List<Publisher> publisherList = new ArrayList<>();

    @ManyToOne(fetch = FetchType.EAGER)
    private Album album;

    @Override
    public String toString() {
        return title + " - " + duration;
    }

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

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }

}
