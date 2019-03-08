package challenge;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(name = "recipe")
public class RecipeController {

	@Autowired
	private RecipeService service;

	@PostMapping
	public Recipe save(@Valid @RequestBody Recipe recipe) {
		return service.save(recipe);
	}

	@PutMapping("{id}")
	public void update(@PathVariable("id") String id, @Valid @RequestBody Recipe recipe) {
		service.update(id, recipe);
	}

	@DeleteMapping("{id}")
	public void delete(@PathVariable("id") String id) {
		service.delete(id);
	}

	@GetMapping("{id}")
	public Recipe get(@PathVariable("id") String id) {
		return service.get(id);
	}

	@GetMapping("ingredient")
	public List<Recipe> listByIngredient(@RequestParam("ingredient") String ingredient) {
		return service.listByIngredient(ingredient);
	}

	@GetMapping("search")
	public List<Recipe> search(@RequestParam("search") String search) {
		return service.search(search);
	}

	@PostMapping("/recipe/{id}/like/{userId}")
	public void like(@PathVariable("id") String id, @PathVariable("userId") String userId) {
		service.like(id, userId);
	}

	@DeleteMapping("/recipe/{id}/like/{userId}")
	public void unlike(@PathVariable("id") String id, @PathVariable("userId") String userId) {
		service.unlike(id, userId);
	}

	@PostMapping("/recipe/{id}/comment")
	public RecipeComment addComment(@PathVariable("id") String id, @Valid @RequestBody RecipeComment comment) {
		return service.addComment(id, comment);
	}

	@PutMapping("/recipe/{id}/comment/{commentId}")
	public void updateComment(@PathVariable("id") String id, @PathVariable("commentId") String commentId, @Valid @RequestBody RecipeComment comment) {
		service.updateComment(id, commentId, comment);
	}

	@DeleteMapping("/recipe/{id}/comment/{commentId}")
	public void deleteComment(@PathVariable("id") String id, @PathVariable("commentId") String commentId) {
		service.deleteComment(id, commentId);
	}

}
