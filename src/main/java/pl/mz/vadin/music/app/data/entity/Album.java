package pl.mz.vadin.music.app.data.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Album extends AbstractEntity{

    private String title;

    private LocalDate releasedDate;

    private String region;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Song> tracks = new ArrayList<>();

    @ManyToOne
    private Publisher publisher;

    @ManyToMany
    private List<Performer> performerList = new ArrayList<>();
}
