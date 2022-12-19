package pl.mz.vadin.music.app;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.orm.jpa.JpaObjectRetrievalFailureException;
import pl.mz.vadin.music.app.data.entity.Writer;
import pl.mz.vadin.music.app.data.repository.WriterRepository;
import pl.mz.vadin.music.app.data.service.WriterServiceImpl;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

import static org.junit.jupiter.api.Assertions.assertThrows;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class DataIntegrationTest {

    @Autowired
    WriterRepository writerRepository;

    @Test
    void testDeleteWriter(){
        Writer writer = new Writer();
        writer.setName("Moniuszko");

        Writer saved = writerRepository.save(writer);
        writerRepository.deleteById(saved.getId());

        assertThrows(JpaObjectRetrievalFailureException.class, () -> {
            Writer deleted = writerRepository.getById(saved.getId());
        });

    }

    @Test
    void testSaveWriter(){
        Writer writer = new Writer();
        writer.setName("Chopin");
        Writer saved = writerRepository.save(writer);
        assertThat(saved).isNotNull();
    }

    @Test
    void testUpdateWriter(){
        Writer writer = new Writer();
        writer.setName("Moniuszko");
        Writer saved = writerRepository.save(writer);
        saved.setName("Chopin");
        Writer updated = writerRepository.save(saved);
        assertThat(updated.getName()).isEqualTo("Chopin");
    }

    @Test
    void testGetWriter(){
        Writer writer = writerRepository.getById(1L);

        assertThat(writer).isNotNull();
    }

    @Test
    void testGetWriterByName(){
        Writer writer = writerRepository.findByName("Moniuszko");
        assertThat(writer).isNotNull();
    }
}
