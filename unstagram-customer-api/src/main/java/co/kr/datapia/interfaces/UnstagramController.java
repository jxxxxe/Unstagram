package co.kr.datapia.interfaces;

import co.kr.datapia.application.UnstagramService;
import co.kr.datapia.domain.Unstagram;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
public class UnstagramController {

    @Autowired
    private UnstagramService unstagramService;
    
    @GetMapping("/unstagram")
    public List<Unstagram> list(){
        List<Unstagram> unstagrams=unstagramService.getUnstagrams();

        return unstagrams;
    }

    @GetMapping("/unstagram/{boardId}")
    public Unstagram detail(
            @PathVariable("boardId") Long boardId
    ){
        Unstagram unstagram=unstagramService.getUnstagram(boardId);

        return unstagram;
    }

    @PostMapping("/unstagram")
    public ResponseEntity<?> create(
            @RequestBody Unstagram resource) throws URISyntaxException {

        String creatorId=resource.getCreatorId();
        String content=resource.getContent();
        String image= resource.getImage();

        Unstagram unstagram=unstagramService.addUnstagram(creatorId,content,image);

        URI url=new URI("/unstagram/"+unstagram.getBoardId());

        return ResponseEntity.created(url).body("{}");
    }

    @PatchMapping("/unstagram/{boardId}")
    public String update(@PathVariable("boardId") Long boardId,
                         @RequestBody Unstagram resource){
        String content=resource.getContent();
        String image= resource.getImage();

        unstagramService.updateUnstagram(boardId, content,image);

        return "";
    }

    @DeleteMapping("/unstagram/{boardId}")
    public String remove(
            @PathVariable("boardId") Long boardId
    ){
        unstagramService.removeUnstagram(boardId);
        return "";
    }

}
