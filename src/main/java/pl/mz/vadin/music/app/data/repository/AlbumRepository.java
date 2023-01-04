package pl.mz.vadin.music.app.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.mz.vadin.music.app.data.entity.Album;
import pl.mz.vadin.music.app.data.entity.Song;

import java.util.List;
import java.util.UUID;

@Repository
public interface AlbumRepository extends JpaRepository<Album, UUID> {

    @Query("select a from Album a where lower(a.title) like lower(concat('%', :searchTerm, '%')) ")
    List<Album> search(@Param("searchTerm") String searchTerm);

    Album findByTitle (String name);
}
