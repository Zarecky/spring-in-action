package tacos.data

import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Repository
import tacos.Ingredient
import java.sql.ResultSet

@Repository
class JdbcIngredientRepository(val jdbc: JdbcTemplate) : IngredientRepository {

    override fun findAll(): Iterable<Ingredient> {
        return jdbc.query("select id, name, type from Ingredient",
                this::mapRowToIngredient)
    }

    override fun findOne(id: String): Ingredient? {
        return jdbc.queryForObject("select id, name, type from Ingredient where id=?",
                this::mapRowToIngredient,
                arrayOf(id))
    }

    override fun save(ingredient: Ingredient): Ingredient {
        jdbc.update(
                "insert into Ingredient (id, name, type) values (? ? ?)",
                ingredient.id,
                ingredient.name,
                ingredient.type.toString()
        )
        return ingredient
    }

    private fun mapRowToIngredient(rs: ResultSet, rowNum: Int): Ingredient {
        return Ingredient(
                rs.getString("id"),
                rs.getString("name"),
                Ingredient.Type.valueOf(rs.getString("type")))
    }
}
