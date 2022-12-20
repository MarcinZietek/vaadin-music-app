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
public class SongListService {

    private final AlbumRepository albumRepository;

    private final ProducerRepository producerRepository;

    private final PublisherRepository publisherRepository;

    private final SongRepository songRepository;

    public SongListService(AlbumRepository albumRepository, ProducerRepository producerRepository, PublisherRepository publisherRepository, SongRepository songRepository) {
        this.albumRepository = albumRepository;
        this.producerRepository = producerRepository;
        this.publisherRepository = publisherRepository;
        this.songRepository = songRepository;
    }

    public List<Song> findAllSongs(String stringFilter){
        if(stringFilter == null || stringFilter.isEmpty()){
            return songRepository.findAll();
        }else {
            return songRepository.search(stringFilter);
        }
    }

    public List<Album> findAllAlbums(){
        return albumRepository.findAll();
    }

    public List<Producer> findAllProducers(){
        return producerRepository.findAll();
    }

    public List<Publisher> findAllPublishers(){
        return publisherRepository.findAll();
    }
}