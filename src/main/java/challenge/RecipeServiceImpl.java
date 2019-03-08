package challenge;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

@Service
public class RecipeServiceImpl implements RecipeService {

	private RecipeRepository recipeRepository;
	
	public RecipeServiceImpl(RecipeRepository recipeRepository) {
		this.recipeRepository = recipeRepository;
	}

	@Override
	public Recipe save(Recipe recipe) {
		return this.recipeRepository.save(recipe);
	}

	@Override
	public void update(String id, Recipe recipe) {
		Recipe toUpdate = this.get(id);
		toUpdate.setTitle(recipe.getTitle());
		toUpdate.setDescription(recipe.getDescription());
		toUpdate.cleanIngredients();
		
		recipe.getIngredients().forEach(ingredient -> toUpdate.addIngredient(ingredient));
		
		this.save(toUpdate);
	}

	@Override
	public void delete(String id) {
		Recipe toDelete = this.get(id);
		this.recipeRepository.delete(toDelete);
	}

	@Override
	public Recipe get(String id) {
		return this.recipeRepository.findById(id).orElseThrow(RecipeNotFoundException::new);
	}

	@Override
	public List<Recipe> listByIngredient(String ingredient) {
		return this.recipeRepository.findAllByIngredients(ingredient)
						.stream()
						.sorted(Comparator.comparing(Recipe::getTitle))
						.collect(Collectors.toList());
	}

	@Override
	public List<Recipe> search(String search) {
		return this.recipeRepository.findAllByTitleAndDescription(search)
						.stream()
						.sorted(Comparator.comparing(Recipe::getTitle))
						.collect(Collectors.toList());
	}

	@Override
	public void like(String id, String userId) {
		Recipe toLike = this.get(id);
		toLike.addLike(userId);
		this.save(toLike);
	}

	@Override
	public void unlike(String id, String userId) {
		Recipe toUnlike = this.get(id);
		toUnlike.unlike(userId);
		this.save(toUnlike);
	}

	@Override
	public RecipeComment addComment(String id, RecipeComment comment) {
		Recipe toUpdateComment = this.get(id);
		toUpdateComment.addComment(comment);
		this.save(toUpdateComment);
		return comment;
	}

	@Override
	public void updateComment(String id, String commentId, RecipeComment comment) {
		Recipe toUpdateComment = this.get(id);
		toUpdateComment.updateRecipeComment(commentId, comment);
		this.save(toUpdateComment);
	}

	@Override
	public void deleteComment(String id, String commentId) {
		Recipe toUpdateComment = this.get(id);
		toUpdateComment.removeRecipeComment(commentId);
		this.save(toUpdateComment);
	}

}
