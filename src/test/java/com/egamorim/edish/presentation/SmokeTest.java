package com.egamorim.edish.presentation;

import com.egamorim.edish.application.repository.MenuItemRepository;
import com.egamorim.edish.application.service.OrderService;
import com.egamorim.edish.presentation.dto.OrderDTO;
import com.egamorim.edish.presentation.dto.OrderItemDTO;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(OrderController.class)
@AutoConfigureMockMvc
public class SmokeTest {

    @MockBean
    private MenuItemRepository menuItemRepository;
    @MockBean
    private OrderService orderService;
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void greetingShouldReturnDefaultMessage() throws Exception {

        OrderDTO request = new OrderDTO();
        request.setMerchantId(UUID.randomUUID().toString());
        request.setItems(Collections.singletonList(new OrderItemDTO()));

        this.mockMvc.perform(
                post("/edish/order")
                    .content(asJsonString(request))
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());

    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
