package tacos

import org.hamcrest.Matchers.containsString
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*

@RunWith(SpringRunner::class)
class HomeControllerTest {

    @Autowired
    lateinit var mockMvc: MockMvc

    @Test
    fun testHomePage(): Unit {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk)
                .andExpect(view().name("home"))
                .andExpect(content().string(
                        containsString("Welcome to...")
                ))
    }
}