package pl.mz.vadin.music.app;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.orm.jpa.JpaObjectRetrievalFailureException;
import pl.mz.vadin.music.app.data.domain.MusicGenre;
import pl.mz.vadin.music.app.data.entity.Album;
import pl.mz.vadin.music.app.data.entity.Song;
import pl.mz.vadin.music.app.data.repository.AlbumRepository;
import pl.mz.vadin.music.app.data.repository.SongRepository;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class DataIntegrationTest {

    @Autowired
    AlbumRepository albumRepository;
    @Autowired
    private SongRepository songRepository;

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
        album.setMusicGenre(MusicGenre.CLASSICAL);
        Album saved = albumRepository.save(album);
        assertThat(saved).isNotNull();
    }

    @Test
    void testSaveSong(){
        Song song = new Song();
        song.setTitle("Nowa Piosenka");
        song.setDuration(4.25F);
        Song saved = songRepository.save(song);
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
