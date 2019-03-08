package challenge;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RecipeRepository extends MongoRepository<Recipe, String> {
	@Query("{'ingredients': ?0}")
	List<Recipe> findAllByIngredients(String ingredient);
	@Query("{ $text: {$search: '?0', $caseSensitive: false}}")
	List<Recipe> findAllByTitleAndDescription(String search);
}
