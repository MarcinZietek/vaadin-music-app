package pl.mz.vadin.music.app.data.entity;

import lombok.Data;

import javax.persistence.Entity;

@Data
@Entity
public class Producer extends AbstractEntity{

    private String name;
}
