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

import java.time.LocalDate;
import java.util.Optional;
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
    void testSaveSong(){
        Song song = new Song();
        Album album = new Album();
        album.setTitle("Astronomia Poety. Baczyński");
        album.setRegion("Poland");
        album.setReleasedDate(LocalDate.of(2020, 7, 24));
        albumRepository.save(album);

        song.setTitle("Nowa Piosenka");
        song.setDuration(4.25F);
        song.setAlbum(album);
        Song saved = songRepository.save(song);
        assertThat(saved).isNotNull();
    }

    @Test
    void testDeleteSongById(){
        Song song = new Song();
        song.setTitle("Nowa piosenka");
        song.setDuration(4.25F);
        Song saved = songRepository.save(song);
        songRepository.deleteById(saved.getId());
        Optional<Song> deleted = songRepository.findById(saved.getId());
        assertThat(deleted).isEmpty();

    }

    @Test
    void testUpdateSong(){
        Song song = new Song();
        song.setTitle("Nowa piosenka");
        song.setDuration(4.25F);
        Song saved = songRepository.save(song);

        Song updated = new Song();
        updated.setTitle("Nowsza piosenka");
        updated = songRepository.save(saved);
        assertThat(updated.getTitle()).isNotEqualTo("Nowsza piosenka");
    }

    @Test
    void testGetSongById(){
        Optional<Song> song = songRepository.findById(UUID.randomUUID());
        assertThat(song).isNotNull();
    }

    @Test
    void testFindAllAlbums(){
        Iterable<Album> albums = albumRepository.findAll();
        assertThat(albums).hasSize(4);       // expected 4

    }

    @Test
    void testSaveAlbum(){
        Album album = new Album();
        album.setTitle("Astronomia Poety. Baczyński");
        album.setRegion("Poland");
        album.setReleasedDate(LocalDate.of(2020, 7, 24));
        album.setMusicGenre(MusicGenre.CLASSICAL);
        Album saved = albumRepository.save(album);
        assertThat(saved).isNotNull();
    }

    @Test
    void testDeleteAlbum(){
        Album album = new Album();
        album.setTitle("Astronomia Poety. Baczyński");
        album.setRegion("Poland");
        album.setReleasedDate(LocalDate.of(2020, 7, 24));
        album.setMusicGenre(MusicGenre.CLASSICAL);
        Album saved = albumRepository.save(album);
        albumRepository.deleteById(saved.getId());

        assertThrows(JpaObjectRetrievalFailureException.class, () -> {
            Album deleted = albumRepository.getById(saved.getId());
        });

    }

    @Test
    void testDeleteAllAlbums(){
        albumRepository.deleteAll();
        assertThat(albumRepository.findAll()).isEmpty();
    }

    @Test
    void testUpdateAlbum(){
        Album album = new Album();
        album.setTitle("Astronomia Poety. Baczyński");
        album.setRegion("Poland");
        album.setReleasedDate(LocalDate.of(2020, 7, 24));
        album.setMusicGenre(MusicGenre.CLASSICAL);
        Album saved = albumRepository.save(album);

        Album updated = new Album();
        updated.setTitle("Chopin's sonata");
        updated = albumRepository.save(saved);
        assertThat(updated.getTitle()).isEqualTo("Astronomia Poety. Baczyński");
    }

    @Test
    void testGetAlbumById(){
        Optional<Album> album = albumRepository.findById(UUID.randomUUID());
        assertThat(album).isNotNull();
    }

    @Test
    void testGetAlbumByNameNotFound(){
        Album album = new Album();
        album.setTitle("Moniuszko");
        albumRepository.save(album);
        album = albumRepository.findByTitle("Moniuszko");
        assertThat(album).isNotNull();
    }
}
