package challenge;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.TextIndexed;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * Classe para mapear a receita no MongoDB
 *
 */
@Document(collection = "recipe")
public class Recipe implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private String id;
	@TextIndexed
	private String title;
	@TextIndexed
	private String description;
	
	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	private List<String> likes = new ArrayList<String>();
	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	private List<String> ingredients = new ArrayList<String>();
	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	private List<RecipeComment> comments = new ArrayList<RecipeComment>();

	public void addLike(String like) {
		this.likes.add(like);
	}

	public void addIngredient(String ingredient) {
		this.ingredients.add(ingredient);
	}

	public String getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<String> getLikes() {
		return Collections.unmodifiableList(likes);
	}
	
	public void unlike(String userId) {
		this.likes.remove(userId);
	}

	public List<String> getIngredients() {
		return ingredients;
	}

	public void addComment(RecipeComment comment) {
		this.comments.add(comment);
	}
	
	public void updateRecipeComment(String commentId, RecipeComment comment) {
		RecipeComment update = this.comments.stream().filter(c -> c.getId().equals(commentId)).findFirst().get();
		update.setComment(comment.getComment());
	}
	
	public void removeRecipeComment(String commentId) {
		this.comments.removeIf(c -> c.getId().equals(commentId));
	}
	
	public List<RecipeComment> getComments() {
		return Collections.unmodifiableList(comments);
	}

	public void cleanIngredients() {
		this.ingredients.clear();
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, title);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Recipe other = (Recipe) obj;
		return Objects.equals(id, other.id) && Objects.equals(title, other.title);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Recipe [id=").append(id).append(", title=").append(title).append(", description=")
				.append(description).append(", likes=").append(likes).append(", ingredients=").append(ingredients)
				.append(", comments=").append(comments).append("]");
		return builder.toString();
	}
}
