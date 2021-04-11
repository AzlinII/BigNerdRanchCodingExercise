package com.bignerdranch.service;

import com.bignerdranch.exception.NotFoundException;
import com.bignerdranch.model.Expertise;
import com.bignerdranch.model.Post;
import com.bignerdranch.model.User;
import com.bignerdranch.object.PostUpdateRequest;
import com.bignerdranch.respository.PostRepository;
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
@SpringBootTest(classes = {PostService.class})
public class PostServiceTest {

    @MockBean
    PostRepository repo;

    @MockBean
    UserService userService;

    @Autowired
    PostService service;

    String ERROR_MSG = "Could not find post with id ";
    Expertise expertise;
    User user;
    Post expected;

    @BeforeEach
    public void init(){
        expertise = new Expertise(1L, "java");
        user = new User(1L,"test@gmail.com", expertise);
        expected = new Post(1L, "title", "body", user);
    }

    @Test
    public void readOneTest() {
        when(repo.findById(anyLong())).thenReturn(Optional.of(expected));

        Post actual = service.readOne(1L);
        assertThat(expected, is(actual));
    }

    @Test
    public void readOneTestFailure(){
        Exception e = assertThrows(NotFoundException.class, () -> service.readOne(1L));
        assertThat(e.getMessage(), is(ERROR_MSG + "1"));
    }

    @Test
    public void readMultipleTest() {
        when(repo.findAll()).thenReturn(Arrays.asList(expected));

        List<Post> actual = service.readMultiple(null);
        assertThat(actual.size(), is(1));
        assertThat(actual.get(0), is(expected));
    }

    @Test
    public void readMultipleFilterTest() {
        when(repo.findPostsByUserId(anyLong())).thenReturn(Arrays.asList(expected));

        List<Post> actual = service.readMultiple(1L);
        assertThat(actual.size(), is(1));
        assertThat(actual.get(0), is(expected));
    }

    @Test
    public void createTest(){
        when(userService.readOne(1L)).thenReturn(user);
        when(repo.save(any(Post.class))).thenReturn(expected);

        PostUpdateRequest request = new PostUpdateRequest("new title", "new body", 1L);
        Post actual = service.create(request);
        assertThat(actual, is(expected));
    }

    @Test
    public void updateTest(){
        Post original = expected;
        when(repo.findById(1L)).thenReturn(Optional.of(original));

        expected.setTitle("New title");
        expected.setBody("New body");
        when(repo.save(any(Post.class))).thenReturn(expected);

        PostUpdateRequest updatedRequest = new PostUpdateRequest("New title", "New body", user.getId());
        Post actual = service.update(updatedRequest, 1L);
        assertThat(actual, is(expected));
    }

    @Test
    public void updateTestTitleOnly(){
        Post original = expected;
        when(repo.findById(1L)).thenReturn(Optional.of(original));

        expected.setTitle("New title");
        when(repo.save(any(Post.class))).thenReturn(expected);

        PostUpdateRequest updatedRequest = new PostUpdateRequest(user.getId());
        updatedRequest.setTitle("New title");

        Post actual = service.update(updatedRequest, 1L);
        assertThat(actual, is(expected));
    }

    @Test
    public void updateTestBodyOnly(){
        Post original = expected;
        when(repo.findById(1L)).thenReturn(Optional.of(original));

        expected.setBody("New body");
        when(repo.save(any(Post.class))).thenReturn(expected);

        PostUpdateRequest updatedRequest = new PostUpdateRequest(user.getId());
        updatedRequest.setBody("New body");

        Post actual = service.update(updatedRequest, 1L);
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
