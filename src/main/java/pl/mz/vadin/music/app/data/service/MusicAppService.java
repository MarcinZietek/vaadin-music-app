package pl.mz.vadin.music.app.data.service;

import org.springframework.stereotype.Service;
import pl.mz.vadin.music.app.data.entity.Album;
import pl.mz.vadin.music.app.data.entity.Producer;
import pl.mz.vadin.music.app.data.entity.Publisher;
import pl.mz.vadin.music.app.data.entity.Song;
import pl.mz.vadin.music.app.data.repository.AlbumRepository;
import pl.mz.vadin.music.app.data.repository.ProducerRepository;
import pl.mz.vadin.music.app.data.repository.PublisherRepository;
import pl.mz.vadin.music.app.data.repository.SongRepository;

import java.util.List;

@Service
public class MusicAppService {

    private final AlbumRepository albumRepository;

    private final ProducerRepository producerRepository;

    private final PublisherRepository publisherRepository;

    private final SongRepository songRepository;

    public MusicAppService(AlbumRepository albumRepository, ProducerRepository producerRepository, PublisherRepository publisherRepository, SongRepository songRepository) {
        this.albumRepository = albumRepository;
        this.producerRepository = producerRepository;
        this.publisherRepository = publisherRepository;
        this.songRepository = songRepository;
    }

    public List<Song> findAllSongs(String stringFilter) {
        if (stringFilter == null || stringFilter.isEmpty()){
            return songRepository.findAll();
        } else {
            return songRepository.search(stringFilter);
        }
    }

    public List<Album> findAllAlbums(String stringFilter) {
        if (stringFilter == null || stringFilter.isEmpty()) {
            return albumRepository.findAll();
        } else {
            return albumRepository.search(stringFilter);
        }
    }

    public long countAlbums() {
        return albumRepository.count();
    }

    public void deleteAlbum(Album album) {
        albumRepository.delete(album);
    }

    public void deleteSong(Song song) {
        songRepository.delete(song);
    }

    public void saveAlbum(Album album) {
        if (album == null) {
            System.err.println("Album is null");
            return;
        }
        albumRepository.save(album);
    }

    public void saveSong(Song song) {
        if (song == null){
            System.err.println("Song is null");
            return;
        }
        songRepository.save(song);
    }

    public List<Producer> findAllProducers() {
        return producerRepository.findAll();
    }

    public List<Publisher> findAllPublishers() {
        return publisherRepository.findAll();
    }

}
