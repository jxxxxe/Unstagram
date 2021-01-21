package co.kr.datapia.interfaces;

import co.kr.datapia.application.UnstagramService;
import co.kr.datapia.domain.Unstagram;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.core.Is.is;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(UnstagramController.class)
public class UnstagramControllerTests {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private UnstagramService unstagramService;

    @Before
    public void setUp() throws Exception {
        createMockUnstagram();
    }

    private void createMockUnstagram() {
        String creatorId="An";
        String content="This is the content";
        String image= "image 1";

        List<Unstagram> unstagrams=new ArrayList<>();

        Unstagram unstagram=Unstagram.builder()
                .creatorId(creatorId)
                .content(content)
                .image(image)
                .build();

        unstagrams.add(unstagram);


        given(unstagramService.getUnstagrams()).willReturn(unstagrams);
        given(unstagramService.getUnstagram(1004L)).willReturn(unstagram);
        given(unstagramService.addUnstagram(creatorId,content,image)).willReturn(unstagram);
    }

    @Test
    public void list() throws Exception {

        mvc.perform(get("/unstagram"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("This is the content")));
    }

    @Test
    public void detail() throws Exception {

        mvc.perform(get("/unstagram/1004"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("This is the content")));
    }

    @Test
    public void create() throws Exception {

        mvc.perform(post("/unstagram")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"creatorId\":\"An\", \"content\":\"This is the content\", \"image\":\"image 1\"}"))
                .andExpect(status().isCreated())
                .andExpect(content().string("{}"));

        verify(unstagramService).addUnstagram("An", "This is the content", "image 1");
    }

    @Test
    public void update() throws Exception {
        String creatorId="JJong123";
        String content="The content is updated";
        String image= "image 2";

        mvc.perform(patch("/unstagram/1004")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"creatorId\":\"JJong123\", \"content\":\"The content is updated\", \"image\":\"image 2\"}"))
                .andExpect(status().isOk());

        verify(unstagramService).updateUnstagram(1004L, content,image);
    }

    @Test
    public void remove() throws Exception {

        mvc.perform(delete("/unstagram/1004"))
                .andExpect(status().isOk());

        verify(unstagramService).removeUnstagram(1004L);
    }
}