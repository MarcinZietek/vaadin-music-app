package pl.mz.vadin.music.app.data.interfaces;

import pl.mz.vadin.music.app.data.entity.Writer;

import java.util.List;
import java.util.UUID;

public interface WriterService {

    List<Writer> findAll();
    void deleteById(Long id);
    Writer findById(Long id);
    Writer update(Writer writer);
    Writer save(Writer writer);
}
