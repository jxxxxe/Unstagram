package co.kr.datapia.application;

import co.kr.datapia.domain.Unstagram;
import co.kr.datapia.domain.UnstagramRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class UnstagramService {

    private UnstagramRepository unstagramRepository;

    @Autowired
    public UnstagramService(UnstagramRepository unstagramRepository) {
        this.unstagramRepository = unstagramRepository;
    }

    public List<Unstagram> getUnstagrams() {
        List<Unstagram> unstagrams=unstagramRepository.findAll();

        return unstagrams;
    }

    public Unstagram getUnstagram(Long boardId) {
        Unstagram unstagram=unstagramRepository.findByBoardId(boardId).orElse(null);

        return unstagram;
    }


    public Unstagram addUnstagram(String creatorId, String content, String image) {
        Unstagram unstagram=Unstagram.builder().creatorId(creatorId).content(content).image(image).build();

        unstagramRepository.save(unstagram);

        return unstagram;
    }

    public Unstagram updateUnstagram(Long boarderId, String content, String image) {
        Unstagram unstagram=unstagramRepository.findByBoardId(boarderId).orElse(null);

        unstagram.setContent(content);
        unstagram.setImage(image);

        unstagramRepository.save(unstagram);

        return unstagram;
    }

    public void removeUnstagram(Long boardId) {
        unstagramRepository.deleteByBoardId(boardId);
    }
}
