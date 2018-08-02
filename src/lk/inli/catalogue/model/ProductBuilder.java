package lk.inli.catalogue.model;

import java.util.Date;
import java.util.HashSet;

public class ProductBuilder {
	private long movieId;
	private String title;
	private String year;
	private String length;
	private String type;
	private String actor;
	private String actress;
	private String director;
	private Date lastUpdated;

	public Product build() {
		if ((movieId < 0) || ("".equals(this.title) || this.title.trim().isEmpty())
				|| ("".equals(this.year) || this.year.trim().isEmpty())
				|| ("".equals(this.length) || this.length.trim().isEmpty())
				|| ("".equals(this.type) || this.type.trim().isEmpty())
				|| ("".equals(this.actor) || this.actor.trim().isEmpty())
				|| ("".equals(this.actress) || this.actress.trim().isEmpty())
				|| ("".equals(this.director) || this.director.trim().isEmpty())
				|| (this.lastUpdated == null)) {
			throw new IllegalStateException("ProductBuilder not in buildable state.");
		}

		return new Product(this.movieId, this.title, this.year, this.length, this.type,
				this.actor, this.actress, this.director, this.lastUpdated);
	}

	public ProductBuilder movieId(long movieId) {
		if (movieId < 0) {
			throw new IllegalArgumentException("Movie Id can't be less than 0");
		}
		this.movieId = movieId;
		return this;
	}

	public ProductBuilder title(String title) {
		if (title == null || title.trim().isEmpty()) {
			throw new IllegalArgumentException("Movie title cannot be null or empty.");
		}
		this.title = title;
		return this;
	}

	public ProductBuilder year(String year) {
		if (year == null || year.trim().isEmpty()) {
			throw new IllegalArgumentException("Year cannot be null or empty.");
		}
		this.year = year;
		return this;
	}

	public ProductBuilder length(String length) {
		if (length == null || length.trim().isEmpty()) {
			throw new IllegalArgumentException("Length cannot be null or empty.");
		}
		this.length = length;
		return this;
	}

	public ProductBuilder type(String type) {
		if (type == null || type.trim().isEmpty()) {
			throw new IllegalArgumentException("Type cannot be null or empty.");
		}
		this.type = type;
		return this;
	}

	public ProductBuilder actor(String actor) {
		if (actor == null || actor.trim().isEmpty()) {
			throw new IllegalArgumentException("Actor cannot be null or empty.");
		}
		this.actor = actor;
		return this;
	}

	public ProductBuilder actress(String actress) {
		if (actress == null || actress.trim().isEmpty()) {
			throw new IllegalArgumentException("Actress cannot be null or empty.");
		}
		this.actress = actress;
		return this;
	}

	public ProductBuilder director(String director) {
		if (director == null || director.trim().isEmpty()) {
			throw new IllegalArgumentException("Director cannot be null or empty.");
		}
		this.director = director;
		return this;
	}

	public ProductBuilder lastUpdated(Date lastUpdated) {
		if (lastUpdated == null) {
			throw new IllegalArgumentException("Date cannot be null.");
		}
		this.lastUpdated = lastUpdated;
		return this;
	}
}
