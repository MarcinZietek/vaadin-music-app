package pl.mz.vadin.music.app.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.mz.vadin.music.app.data.entity.Song;

import java.util.List;
import java.util.UUID;

@Repository
public interface SongRepository extends JpaRepository<Song, UUID> {

    @Query("select s from Song s where lower(s.title) like lower(concat('%', :searchTerm, '%')) ")
    List<Song> search(@Param("searchTerm") String searchTerm);
    }
