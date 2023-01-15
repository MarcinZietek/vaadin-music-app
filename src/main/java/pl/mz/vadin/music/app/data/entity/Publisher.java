package pl.mz.vadin.music.app.data.entity;

import javax.persistence.Entity;


@Entity
public class Publisher extends AbstractEntity{

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Publisher{" +
                "name='" + name + '\'' +
                '}';
    }
}
