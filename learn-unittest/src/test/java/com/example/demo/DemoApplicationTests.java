package com.example.demo;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.awt.print.Book;


@SpringBootTest
class DemoApplicationTests {

  @Autowired
  private HelloService helloService;
  @Autowired
  WebApplicationContext wac;
  MockMvc mockMvc;

  @Test
  void contextLoads() {
    String jj = helloService.helloService("jj");
    System.out.println(jj);
//    Assert.assertThat(jj, Matchers.is("hello jj"));
  }
  @BeforeEach
  public void before(){
    mockMvc= MockMvcBuilders.webAppContextSetup(wac).build();
  }
  @Test
  public void test1() throws Exception {
    MvcResult mvcResult=mockMvc.perform(
            MockMvcRequestBuilders.get("/hello")
            .contentType(MediaType.APPLICATION_FORM_URLENCODED)
            .param("name","java"))
     .andExpect(MockMvcResultMatchers.status().isOk())
     .andDo(MockMvcResultHandlers.print())
     .andReturn();
    System.out.println(mvcResult.getResponse().getContentAsString());
  }
  @Test
  public void test2() throws Exception {
    User user=new User();
    user.setId(1);
    user.setName("男大苏打");
    String userStr=new ObjectMapper().writeValueAsString(user);
    MvcResult mvcResult=mockMvc.perform(
            MockMvcRequestBuilders.post("/addUser")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(userStr))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andReturn();
    System.out.println(mvcResult.getResponse().getContentAsString());
  }

  @AfterEach
  public void after(){
    System.out.println("测试后");
  }
}
