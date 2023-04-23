package com.yicj.hello;

import com.yicj.hello.controller.SubscriptionController;
import com.yicj.hello.entity.StockSubscription;
import com.yicj.hello.service.RSubscriptionService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyString;
import static reactor.core.publisher.Mono.when;

/**
 * @author: yicj
 * @date: 2023/4/23 17:10
 */
@WebMvcTest(SubscriptionController.class)
public class SubscriptionControllerMvcTest {

    @MockBean
    private RSubscriptionService subscriptionService ;

    @Autowired
    private MockMvc mockMvc ;


    @Test
    @WithMockUser(username = "test@qq.com", roles = "ADMIN")
    public void shouldReturnViewSubs() throws Exception {
        when(subscriptionService.findByEmail(anyString())).thenReturn(buildSubscriptionList()) ;
        this.mockMvc.perform(MockMvcRequestBuilders.get("/subscriptions"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("subscription"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("email"))
                .andExpect(MockMvcResultMatchers.model().attribute("email", "test@qq.com"))
                .andExpect(MockMvcResultMatchers.model().attribute("subscription", Matchers.containsInAnyOrder(
                        StockSubscription.builder()
                                .email("test@qq.com")
                                .symbol("apple")
                                .build()
                ))) ;

    }

    private List<StockSubscription> buildSubscriptionList() {
        return Arrays.asList(
                StockSubscription.builder()
                        .email("test@qq.com")
                        .symbol("apple")
                        .build()
        ) ;
    }

}
