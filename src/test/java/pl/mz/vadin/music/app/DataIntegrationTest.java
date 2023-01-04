package pl.mz.vadin.music.app;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.orm.jpa.JpaObjectRetrievalFailureException;
import pl.mz.vadin.music.app.data.entity.Album;
import pl.mz.vadin.music.app.data.entity.Writer;
import pl.mz.vadin.music.app.data.repository.AlbumRepository;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

import static org.junit.jupiter.api.Assertions.assertThrows;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class DataIntegrationTest {

    @Autowired
    AlbumRepository albumRepository;

    @Test
    void testDeleteWriter(){
        Album album = new Album();
        album.setTitle("Moniuszko");

        Album saved = albumRepository.save(album);
        albumRepository.deleteById(saved.getId());

        assertThrows(JpaObjectRetrievalFailureException.class, () -> {
            Album deleted = albumRepository.getById(saved.getId());
        });

    }

    @Test
    void testSaveAlbum(){
        Album album = new Album();
        album.setTitle("Chopin");
        Album saved = albumRepository.save(album);
        assertThat(saved).isNotNull();
    }

    @Test
    void testUpdateWriter(){
        Album album = new Album();
        album.setTitle("Moniuszko");
        Album saved = albumRepository.save(album);
        saved.setTitle("Chopin");
        Album updated = albumRepository.save(saved);
        assertThat(updated.getTitle()).isEqualTo("Chopin");
    }

    @Test
    void testGetWriter(){
        Album album = albumRepository.getById(UUID.randomUUID());

        assertThat(album).isNotNull();
    }

    @Test
    void testGetWriterByNameNotFound(){
        Album album = new Album();
        album.setTitle("Moniuszko");
        albumRepository.save(album);
        album = albumRepository.findByTitle("Moniuszko");
        assertThat(album).isNotNull();
    }
}
