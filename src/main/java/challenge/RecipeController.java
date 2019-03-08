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
@RequestMapping("recipe")
public class RecipeController {

	@Autowired
	private RecipeService service;

	@PostMapping
	public Recipe save(@Valid @RequestBody Recipe recipe) {
		return service.save(recipe);
	}

	@PutMapping("{id}")
	public void update(@PathVariable String id, @Valid @RequestBody Recipe recipe) {
		service.update(id, recipe);
	}

	@DeleteMapping("{id}")
	public void delete(@PathVariable String id) {
		service.delete(id);
	}

	@GetMapping("{id}")
	public Recipe get(@PathVariable String id) {
		return service.get(id);
	}

	@GetMapping("/ingredient")
	public List<Recipe> listByIngredient(@RequestParam(name = "ingredient") String ingredient) {
		return service.listByIngredient(ingredient);
	}

	@GetMapping("/search")
	public List<Recipe> search(@RequestParam(name = "search") String search) {
		return service.search(search);
	}

	@PostMapping("/{id}/like/{userId}")
	public void like(@PathVariable String id, @PathVariable String userId) {
		service.like(id, userId);
	}

	@DeleteMapping("/{id}/like/{userId}")
	public void unlike(@PathVariable String id, @PathVariable String userId) {
		service.unlike(id, userId);
	}

	@PostMapping("/{id}/comment")
	public RecipeComment addComment(@PathVariable String id, @Valid @RequestBody RecipeComment comment) {
		return service.addComment(id, comment);
	}

	@PutMapping("/{id}/comment/{commentId}")
	public void updateComment(@PathVariable String id, @PathVariable String commentId, @Valid @RequestBody RecipeComment comment) {
		service.updateComment(id, commentId, comment);
	}

	@DeleteMapping("/{id}/comment/{commentId}")
	public void deleteComment(@PathVariable String id, @PathVariable String commentId) {
		service.deleteComment(id, commentId);
	}

}
