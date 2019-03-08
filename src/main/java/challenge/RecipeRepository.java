package challenge;

import java.util.List;

import org.springframework.data.mongodb.core.query.TextCriteria;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RecipeRepository extends MongoRepository<Recipe, String> {
	@Query("{'ingredients': ?0}")
	List<Recipe> findByIngredients(String ingredient);
	List<Recipe> findByTitleAndDescription(TextCriteria search);
}
