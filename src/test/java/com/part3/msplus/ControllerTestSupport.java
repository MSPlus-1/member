package com.part3.msplus;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.part3.msplus.oauth2.application.IdTokenFacade;
import com.part3.msplus.oauth2.application.Oauth2Service;
import com.part3.msplus.oauth2.controller.Oauth2Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(controllers = {
        Oauth2Controller.class,
})
public abstract class ControllerTestSupport {

    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    protected ObjectMapper objectMapper;

    @MockBean
    protected Oauth2Service oauth2Service;

    @MockBean
    protected IdTokenFacade idTokenFacade;
}
