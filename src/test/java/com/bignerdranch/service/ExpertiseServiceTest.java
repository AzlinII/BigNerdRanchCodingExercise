package com.bignerdranch.service;

import com.bignerdranch.exception.NotFoundException;
import com.bignerdranch.model.Expertise;
import com.bignerdranch.respository.ExpertiseRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {ExpertiseService.class})
public class ExpertiseServiceTest {

    @MockBean
    ExpertiseRepository repo;

    @Autowired
    ExpertiseService service;

    String ERROR_MSG = "Could not find expertise with id ";
    Expertise expected;

    @BeforeEach
    public void init(){
        expected = new Expertise(1L, "java");
    }

    @Test
    public void readOneTest() {
        when(repo.findById(1L)).thenReturn(Optional.of(expected));
        Expertise actual = service.readOne(1L);
        assertThat(expected, is(actual));
    }

    @Test
    public void readOneTestFailure(){
        Exception e = assertThrows(NotFoundException.class, () -> service.readOne(1L));
        assertThat(e.getMessage(), is(ERROR_MSG + "1"));
    }

    @Test
    public void readAllTest() {
        when(repo.findAll()).thenReturn(Arrays.asList(expected));
        List<Expertise> actual = service.readAll();
        assertThat(actual.size(), is(1));
        assertThat(actual.get(0), is(expected));
    }

    @Test
    public void createTest(){
        when(repo.save(expected)).thenReturn(expected);

        Expertise actual = service.create(expected);
        assertThat(actual, is(expected));
    }

    @Test
    public void updateTest(){
        Expertise original = expected;
        when(repo.findById(1L)).thenReturn(Optional.of(original));

        Expertise expected = new Expertise(1L, "spring boot");
        when(repo.save(expected)).thenReturn(expected);

        Expertise actual = service.update(expected, 1L);
        assertThat(actual, is(expected));
    }

    @Test
    public void updateTestFailure(){
        Exception e = assertThrows(NotFoundException.class, () -> service.update(null, 1L));
        assertThat(e.getMessage(), is(ERROR_MSG + "1"));
    }

    @Test
    public void deleteTest(){
        when(repo.findById(1L)).thenReturn(Optional.of(expected));
        service.delete(1L);
        verify(repo, times(1)).deleteById(1L);
    }

    @Test
    public void deleteTestFailure(){
        Exception e = assertThrows(NotFoundException.class, () -> service.delete( 1L));
        assertThat(e.getMessage(), is(ERROR_MSG + "1"));
    }

    @Test
    public void findOrCreateTest(){
        when(repo.findByName("java")).thenReturn(Optional.of(expected));
        Expertise actual = service.findOrCreate("java");
        assertThat(actual, is(expected));
    }

    @Test
    public void findOrCreateTestNull(){
        Expertise actual = service.findOrCreate(null);
        assertThat(actual, is(nullValue()));
    }

    @Test
    public void findOrCreateTestNew(){
        Expertise expected = new Expertise(1L, "spring boot");
        when(repo.findByName("spring boot")).thenReturn(Optional.empty());
        when(repo.save(any(Expertise.class))).thenReturn(expected);

        Expertise actual = service.findOrCreate("spring boot");
        assertThat(actual, is(expected));
    }

}
