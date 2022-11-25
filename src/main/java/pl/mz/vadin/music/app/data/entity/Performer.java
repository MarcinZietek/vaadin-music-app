package pl.mz.vadin.music.app.data.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
public class Performer extends AbstractEntity{

    private String firstName;
    private String lastName;
    private String pseudonym;

    @ManyToMany
    private Set<Album> albums = new HashSet<>();
}
