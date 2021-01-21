package co.kr.datapia.application;

import co.kr.datapia.domain.Unstagram;
import co.kr.datapia.domain.UnstagramRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willReturn;
import static org.mockito.Mockito.verify;

public class UnstagramServiceTests {

    private UnstagramService unstagramService;

    @Mock
    private UnstagramRepository unstagramRepository;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        unstagramService=new UnstagramService(unstagramRepository);
    }

    @Test
    public void getUnstagrams(){
        List<Unstagram> mockUnstagrams=new ArrayList<>();
        String image= "image1";

        mockUnstagrams.add(Unstagram.builder()
                .creatorId("An")
                .content("This is the content")
                .image(image)
                .build());

        given(unstagramRepository.findAll()).willReturn(mockUnstagrams);

        List<Unstagram> unstagrams=unstagramService.getUnstagrams();
        Unstagram unstagram=unstagrams.get(0);

        assertThat(unstagram.getCreatorId(),is("An"));
        assertThat(unstagram.getContent(),is("This is the content"));
        assertThat(unstagram.getImage(),is(image));
    }

    @Test
    public void getUnstagram(){
        String image= "image 1";

        Unstagram mockUnstagram=Unstagram.builder()
                .creatorId("An")
                .content("This is the content")
                .image(image)
                .build();

        given(unstagramRepository.findByBoardId(1004L)).willReturn(Optional.of(mockUnstagram));

        Unstagram unstagram=unstagramService.getUnstagram(1004L);

        assertThat(unstagram.getCreatorId(),is("An"));
        assertThat(unstagram.getContent(),is("This is the content"));
        assertThat(unstagram.getImage(),is(image));
    }

    @Test
    public void addUnstagram(){
        String creatorId="An";
        String content="This is content";
        String image= "image 1";

        Unstagram unstagram=unstagramService.addUnstagram(creatorId,content,image);

        verify(unstagramRepository).save(any());

        assertThat(unstagram.getCreatorId(),is(creatorId));
        assertThat(unstagram.getContent(),is(content));
        assertThat(unstagram.getImage(),is(image));
    }

    @Test
    public void updateUnstagram(){
        String creatorId="An";
        String content="The content is updated";
        String image= "image 1";

        Unstagram mockUnstagram=Unstagram.builder().creatorId(creatorId).content("This is the content").image("image 1").build();

        given(unstagramRepository.findByBoardId(1004L)).willReturn(Optional.of(mockUnstagram));

        Unstagram unstagram=unstagramService.updateUnstagram(1004L, content,image);

        assertThat(unstagram.getContent(),is(content));
        assertThat(unstagram.getImage(),is(image));
    }

    @Test
    public void removeUnstagram(){
      unstagramService.removeUnstagram(1004L);

      verify(unstagramRepository).deleteByBoardId(1004L);
    }
}