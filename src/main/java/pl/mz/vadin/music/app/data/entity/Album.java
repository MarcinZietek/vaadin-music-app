package pl.mz.vadin.music.app.data.entity;

import pl.mz.vadin.music.app.data.domain.MusicGenre;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Entity
public class Album extends AbstractEntity {

    @NotEmpty
    private String title;

    private LocalDate releasedDate;

    private String region;
    @Enumerated(EnumType.STRING)
    private MusicGenre musicGenre;

    @ManyToOne
    private Publisher publisher;

    @ManyToMany(fetch = FetchType.EAGER)
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

    public MusicGenre getMusicGenre() {
        return musicGenre;
    }

    public void setMusicGenre(MusicGenre musicGenre) {
        this.musicGenre = musicGenre;
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

    @Override
    public String toString() {
        return title;
    }
}
