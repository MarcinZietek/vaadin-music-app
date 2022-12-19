package pl.mz.vadin.music.app.data.service;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import pl.mz.vadin.music.app.data.entity.Writer;
import pl.mz.vadin.music.app.data.interfaces.WriterService;
import pl.mz.vadin.music.app.data.repository.WriterRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Service
public class WriterServiceImpl {

    private final WriterRepository writerRepository;

    public WriterServiceImpl(WriterRepository writerRepository) {
        this.writerRepository = writerRepository;
    }

    public List<Writer> findAllWriters(String stringFilter) {
        if (stringFilter == null | stringFilter.isEmpty()){
            return writerRepository.findAll();
        }else {
            return writerRepository.search(stringFilter);
        }
    }

}
