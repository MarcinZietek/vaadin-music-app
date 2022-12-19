package pl.mz.vadin.music.app.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.mz.vadin.music.app.data.entity.Song;
import pl.mz.vadin.music.app.data.entity.Writer;

import java.util.List;
import java.util.UUID;

@Repository
public interface WriterRepository extends JpaRepository<Writer, Long> {

    @Query("select w from Writer w where lower(w.name) like lower(concat('%', :searchTerm, '%')) ")
    List<Writer> search(@Param("searchTerm") String searchTerm);
    Writer findByName (String name);
}
