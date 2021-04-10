package com.bignerdranch.service;

import com.bignerdranch.TestUtils;
import com.bignerdranch.assembler.ExpertiseAssembler;
import com.bignerdranch.controller.ExpertiseController;
import com.bignerdranch.model.Expertise;
import com.bignerdranch.respository.ExpertiseRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.Arrays;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(ExpertiseService.class)
@Import({ExpertiseAssembler.class})
public class ExpertiseServiceTest {

    @MockBean
    private ExpertiseRepository repo;

    @Autowired
    private ExpertiseService service;

    @Autowired
    private ObjectMapper mapper;

    private String BASE_URL = "http://localhost/expertises";
    private String SELF_JSON = "$._links.self.href";
    private String ALL_JSON = "$._links.expertises.href";

    @Test
    public void readOneTest() throws Exception {
        Expertise expected = new Expertise(1L, "java");
        given(repo.findById(1L)).willReturn(
                Optional.of(expected)
        );
        EntityModel<Expertise> actual = service.readOne(1L);
        Assert.assertEquals(expected, actual.getContent());

        Optional<Link> link = actual.getLink(IanaLinkRelations.SELF);
        Assert.assertNotNull(link);
        Assert.assertEquals(BASE_URL + 1, link);

        Optional<Link> allLink = actual.getLink("expertises");
        Assert.assertNotNull(allLink);
        Assert.assertEquals(BASE_URL, allLink);
    }

//    @Test
//    public void readAllTest() throws Exception {
//
//        given(repo.findAll()).willReturn(
//                Arrays.asList(new Expertise(1L, "java"))
//        );
//
//        String jsonBase = "$._embedded.expertiseList[0].";
//
//        TestUtils.performGET(mvc, "/expertises")
//                .andExpect(status().isOk())
//                .andExpect(header().string(HttpHeaders.CONTENT_TYPE, MediaTypes.HAL_JSON_VALUE))
//                .andExpect(jsonPath(jsonBase + "name", is("java")))
//                .andExpect(jsonPath(jsonBase + "_links.self.href", is(BASE_URL + "/1")))
//                .andExpect(jsonPath(jsonBase + "_links.expertises.href", is(BASE_URL)))
//                .andExpect(jsonPath(SELF_JSON, is(BASE_URL)));
//    }
//
//    @Test
//    public void restOneTest() throws Exception {
//        given(repo.findById(1L)).willReturn(
//                Optional.of(new Expertise(1L, "java"))
//        );
//
//        TestUtils.performGET(mvc, "/expertises/1")
//                .andExpect(status().isOk())
//                .andExpect(header().string(HttpHeaders.CONTENT_TYPE, MediaTypes.HAL_JSON_VALUE))
//                .andExpect(jsonPath("$.id", is(1)))
//                .andExpect(jsonPath("$.name", is("java")))
//                .andExpect(jsonPath(SELF_JSON, is(BASE_URL + "/1")))
//                .andExpect(jsonPath(ALL_JSON, is(BASE_URL)));
//    }
//
//    @Test
//    public void restOneTestFailure() throws Exception {
//        TestUtils.performGET(mvc, "/expertises/1")
//                .andExpect(status().is(HttpStatus.NOT_FOUND.value()))
//                .andExpect(content().string("Could not find expertise with id 1"));
//    }
//
//    @Test
//    public void createTest() throws Exception {
//        Expertise expected = new Expertise(1L, "java");
//        given(repo.save(expected)).willReturn(expected);
//
//        TestUtils.performPOST(mvc, "/expertises", expected)
//                .andExpect(status().is(HttpStatus.CREATED.value()))
//                .andExpect(jsonPath("$.id", is(1)))
//                .andExpect(jsonPath("$.name", is("java")))
//                .andExpect(jsonPath(SELF_JSON, is(BASE_URL + "/1")))
//                .andExpect(jsonPath(ALL_JSON, is(BASE_URL)));
//    }
//
//    @Test
//    public void updateTest() throws Exception {
//        Expertise original = new Expertise(1L, "java");
//        given(repo.findById(1L)).willReturn(Optional.of(original));
//
//        Expertise expected = new Expertise(1L, "spring boot");
//        given(repo.save(expected)).willReturn(expected);
//
//        TestUtils.performPUT(mvc, "/expertises/1", expected)
//                .andExpect(status().is(HttpStatus.CREATED.value()))
//                .andExpect(jsonPath("$.id", is(1)))
//                .andExpect(jsonPath("$.name", is("spring boot")))
//                .andExpect(jsonPath(SELF_JSON, is(BASE_URL + "/1")))
//                .andExpect(jsonPath(ALL_JSON, is(BASE_URL)));
//    }
//
//    @Test
//    public void updateTestFailure() throws Exception {
//        Expertise test = new Expertise(1L, "java");
//        TestUtils.performPUT(mvc, "/expertises/1", test)
//                .andExpect(status().is(HttpStatus.NOT_FOUND.value()))
//                .andExpect(content().string("Could not find expertise with id 1"));
//    }
//
//    @Test
//    public void deleteTest() throws Exception {
//        given(repo.findById(1L)).willReturn(
//                Optional.of(new Expertise(1L, "java"))
//        );
//        TestUtils.performDelete(mvc, "/expertises/1")
//                .andExpect(status().isAccepted());
//    }
//
//    @Test
//    public void deleteTestFailure() throws Exception {
//        TestUtils.performDelete(mvc, "/expertises/1")
//                .andExpect(status().is(HttpStatus.NOT_FOUND.value()))
//                .andExpect(content().string("Could not find expertise with id 1"));
//    }

}
