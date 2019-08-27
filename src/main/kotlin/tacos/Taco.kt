package tacos

import java.util.*
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size
import kotlin.collections.ArrayList

data class Taco(
        @NotNull
        @Size(min = 5, message = "Name must be at least 5 characters long")
        var name: String = "",

        @Size(min = 1, message = "You must choose at least 1 ingredient")
        var ingredients: List<String> = ArrayList<String>() as List<String>,

        var id: Long?,
        var createdAt: Date?
)