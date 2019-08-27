package tacos.web

import lombok.extern.slf4j.Slf4j
import org.slf4j.Logger
import org.slf4j.LoggerFactory.getLogger
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.validation.Errors
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import tacos.Order
import javax.validation.Valid

@Slf4j
@Controller
@RequestMapping("/orders")
class OrderController {

    val log: Logger = getLogger(OrderController::class.java)

    @GetMapping("/current")
    fun orderForm(model: Model): String {
        return "orderForm"
    }

    @PostMapping
    fun proccessOrder(@Valid order: Order, errors: Errors): String {
        if (errors.hasErrors()) {
            return "orderForm"
        }

        log.info("Order submitted: ${order}")
        return "redirect:/"
    }
}