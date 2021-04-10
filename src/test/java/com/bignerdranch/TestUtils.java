package com.bignerdranch;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

public class TestUtils {

//    private static ObjectMapper mapper = new ObjectMapper();
//
//    public static ResultActions performGET(MockMvc mvc, String endpoint) throws Exception {
//        return mvc.perform(get(endpoint).accept(MediaTypes.HAL_JSON_VALUE));
//    }
//
//    public static ResultActions performPOST(MockMvc mvc, String endpoint, Object body) throws Exception {
//        String content = mapper.writeValueAsString(body);
//        return mvc.perform(post(endpoint).content(content).contentType(MediaType.APPLICATION_JSON));
//    }
//
//    public static ResultActions performPUT(MockMvc mvc, String endpoint, Object body) throws Exception {
//        String content = mapper.writeValueAsString(body);
//        return mvc.perform(put(endpoint).content(content).contentType(MediaType.APPLICATION_JSON));
//    }
//
//    public static ResultActions performDelete(MockMvc mvc, String endpoint) throws Exception {
//        return mvc.perform(delete(endpoint));
//    }




}
