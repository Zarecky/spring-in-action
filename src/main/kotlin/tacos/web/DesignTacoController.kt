package tacos.web

import lombok.extern.slf4j.Slf4j
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.validation.Errors
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import tacos.Ingredient
import tacos.Taco
import tacos.data.IngredientRepository
import java.util.stream.Collectors
import javax.validation.Valid

@Slf4j
@Controller
@RequestMapping("/design")
class DesignTacoController(val ingredientRepository: IngredientRepository) {

    val log: Logger = LoggerFactory.getLogger(DesignTacoController::class.java)

    @GetMapping
    fun showDesignForm(model: Model): String {
       val ingredients: MutableList<Ingredient> = ArrayList()
        ingredientRepository.findAll().forEach { ingredients.add(it) }

        val types: Array<Ingredient.Type> = Ingredient.Type.values()
        for (type in types) {
            model.addAttribute(
                    type.toString().toLowerCase(),
                    filterByType(ingredients, type)
            )
        }

        model["design"] = Taco()

        return "design"
    }

    @PostMapping
    fun processDesign(@Valid design: Taco, errors: Errors): String {
        if (errors.hasErrors()) {
            return "design"
        }

        log.info("Processing design: $design")

        return "redirect:/orders/current"
    }

    fun filterByType(ingredients: List<Ingredient>, type: Ingredient.Type): List<Ingredient> =
            ingredients.stream().filter { it.type == type }.collect(Collectors.toList())
}