package com.bignerdranch.service;

import com.bignerdranch.exception.NotFoundException;
import com.bignerdranch.model.Expertise;
import com.bignerdranch.model.User;
import com.bignerdranch.object.UserUpdateRequest;
import com.bignerdranch.respository.UserRepository;
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
import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {UserService.class})
public class UserServiceTest {

    @MockBean
    UserRepository repo;

    @MockBean
    ExpertiseService expertiseService;

    @Autowired
    UserService service;

    String ERROR_MSG = "Could not find user with id ";
    Expertise expertise;
    User expected;

    @BeforeEach
    public void init(){
        expertise = new Expertise(1L, "java");
        expected = new User(1L,"test@gmail.com", expertise);
    }

    @Test
    public void readOneTest() {
        when(repo.findById(1L)).thenReturn(Optional.of(expected));

        User actual = service.readOne(1L);
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

        List<User> actual = service.readAll();
        assertThat(actual.size(), is(1));
        assertThat(actual.get(0), is(expected));
    }

    @Test
    public void createTest(){
        when(expertiseService.findOrCreate("java")).thenReturn(expertise);
        when(repo.save(any(User.class))).thenReturn(expected);

        UserUpdateRequest request = new UserUpdateRequest("test@gmail.com", "java");
        User actual = service.create(request);
        assertThat(actual, is(expected));
    }

    @Test
    public void updateTest(){
        User original = expected;
        when(repo.findById(1L)).thenReturn(Optional.of(original));

        Expertise newExpertise = new Expertise(2L,"spring boot");
        expected.setEmail("test2@gmail.com");
        expected.setExpertise(newExpertise);
        when(repo.save(any(User.class))).thenReturn(expected);

        UserUpdateRequest updatedRequest = new UserUpdateRequest("test2@gmail.com", "spring boot");
        User actual = service.update(updatedRequest, 1L);
        assertThat(actual, is(expected));
    }

    @Test
    public void updateTestEmailOnly(){
        User original = expected;
        when(repo.findById(1L)).thenReturn(Optional.of(original));

        expected.setEmail("test2@gmail.com");
        when(repo.save(any(User.class))).thenReturn(expected);

        UserUpdateRequest updatedRequest = new UserUpdateRequest();
        updatedRequest.setEmail("test2@gmail.com");

        User actual = service.update(updatedRequest, 1L);
        assertThat(actual, is(expected));
    }

    @Test
    public void updateTestExpertiseOnly(){
        User original = expected;
        when(repo.findById(1L)).thenReturn(Optional.of(original));

        Expertise newExpertise = new Expertise(2L,"spring boot");
        expected.setExpertise(newExpertise);
        when(repo.save(any(User.class))).thenReturn(expected);

        UserUpdateRequest updatedRequest = new UserUpdateRequest();
        updatedRequest.setExpertise("spring boot");

        User actual = service.update(updatedRequest, 1L);
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
}
