package pl.mz.vadin.music.app.data.entity;

import javax.persistence.Entity;


@Entity
public class Writer extends AbstractEntity{

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
