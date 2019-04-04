package ru.atom.chat.client;

import okhttp3.Response;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import ru.atom.chat.client.ChatClient;
import ru.atom.chat.server.ChatApplication;
import ru.atom.chat.server.ChatController;


import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.core.StringContains.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {ChatApplication.class}, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@AutoConfigureMockMvc
public class ChatClientTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ChatController controller;

    @Test
    public void login() throws Exception {
        this.mockMvc.perform(post("/chat/login")
                .param("name", "MyLogin")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void viewChat() throws Exception {
        ChatClient.login("MyLogin");
        this.mockMvc.perform(get("/chat/chat"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("[MyLogin] logged in")));
        ChatClient.logout("MyLogin");
    }

    @Test
    public void viewOnline() throws Exception {
        ChatClient.login("MyLogin");
        this.mockMvc.perform(get("/chat/online"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("MyLogin")));
        ChatClient.logout("MyLogin");
    }

    @Test
    public void say() throws Exception {
        ChatClient.login("MyLogin");
        this.mockMvc.perform(post("/chat/say")
                .param("name", "MyLogin")
                .param("msg", "hello"))
                .andExpect(status().isOk());
        ChatClient.logout("MyLogin");
    }

    @Test
    public void answer() throws Exception {
        ChatClient.login("MyLogin");
        ChatClient.login("MyLogin1");
        this.mockMvc.perform(post("/chat/answer")
                .param("nameY", "MyLogin")
                .param("nameA", "MyLogin1")
                .param("msg", "Hello"))
                .andDo(print())
                .andExpect(status().isOk());
        ChatClient.logout("MyLogin");
        ChatClient.logout("MyLogin1");
    }

    @Test
    public void clear() throws Exception {
        ChatClient.login("MyLogin");
        this.mockMvc.perform(delete("/chat/clear"))
                .andExpect(status().isOk())
                .andExpect(content().string("Chat is clear"));
        ChatClient.logout("MyLogin");
    }

}

/*@RunWith(SpringRunner.class)
@SpringBootTest(classes = {ChatApplication.class}, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class ChatClientTest {
    private static String MY_NAME_IN_CHAT = "I_AM_STUPID";
    private static String MY_MESSAGE_TO_CHAT = "SOMEONE_KILL_ME";

    @Test
    public void login() throws IOException {
        Response response = ChatClient.login(MY_NAME_IN_CHAT);
        System.out.println("[" + response + "]");
        String body = response.body().string();
        System.out.println();
        Assert.assertTrue(response.code() == 200 || body.equals("Already logged in:("));
    }

    @Test
    public void viewChat() throws IOException {
        Response response = ChatClient.viewChat();
        System.out.println("[" + response + "]");
        System.out.println(response.body().string());
        Assert.assertEquals(200, response.code());
    }


    @Test
    public void viewOnline() throws IOException {
        Response response = ChatClient.viewOnline();
        System.out.println("[" + response + "]");
        System.out.println(response.body().string());
        Assert.assertEquals(200, response.code());
    }

    @Test
    public void say() throws IOException {
        ChatClient.login("MyLogin");
        Response response = ChatClient.say("MyLogin", MY_MESSAGE_TO_CHAT);
        System.out.println("[" + response + "]");
        System.out.println(response.body().string());
        Assert.assertEquals(200, response.code());
    }

    @Test
    public void answer() throws IOException {
        ChatClient.login("MyLogin1");
        ChatClient.login("MyLogin2");
        Response response = ChatClient.answer("MyLogin1", "MyLogin2", MY_MESSAGE_TO_CHAT);
        System.out.println("[" + response + "]");
        System.out.println(response.body().string());
        Assert.assertEquals(200, response.code());
    }

    @Test
    public void clear() throws IOException {
        ChatClient.login("MyLogin1");
        ChatClient.login("MyLogin2");
        ChatClient.answer("MyLogin1", "MyLogin2", MY_MESSAGE_TO_CHAT);
        Response response = ChatClient.clear();
        System.out.println("[" + response + "]");
        System.out.println(response.body().string());
        Assert.assertEquals(200, response.code());
    }
}*/
