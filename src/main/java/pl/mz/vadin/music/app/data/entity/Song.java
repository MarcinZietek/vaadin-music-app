package pl.mz.vadin.music.app.data.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Song extends AbstractEntity{

    private String title;
    private Float duration;

    @ManyToMany
    private List<Producer> producerList = new ArrayList<>();
    @ManyToMany
    private List<Publisher> publisherList = new ArrayList<>();
}
