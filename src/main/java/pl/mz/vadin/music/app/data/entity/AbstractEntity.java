package pl.mz.vadin.music.app.data.entity;

import lombok.Data;
import org.hibernate.annotations.Type;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.util.UUID;

@MappedSuperclass
@Data
public abstract class AbstractEntity {

    @Id
    @GeneratedValue
    @Type(type = "uuid-char")
    private UUID id;

}
