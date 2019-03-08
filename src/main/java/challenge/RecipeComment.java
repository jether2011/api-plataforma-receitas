package challenge;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

/**
 * Classe para mapear o comentário da receita no MongoDB
 *
 */
public class RecipeComment implements Serializable {

	private static final long serialVersionUID = 1L;

	private String id;
	private String comment;

	public RecipeComment() {
		this.id = UUID.randomUUID().toString().replaceAll("\\-", "");
	}

	private RecipeComment(String comment) {
		this.comment = comment;
	}

	public static RecipeComment of(String comment) {
		return new RecipeComment(comment);
	}

	public String getId() {
		return id;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	@Override
	public int hashCode() {
		return Objects.hash(comment, id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RecipeComment other = (RecipeComment) obj;
		return Objects.equals(comment, other.comment) && Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Comment [id=").append(id).append(", comment=").append(comment).append("]");
		return builder.toString();
	}
}
