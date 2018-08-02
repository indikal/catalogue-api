package lk.inli.catalogue.model;

import java.util.Date;

public class Product {
	final private long movieId;
	final private String title;
	final private String year;
	final private String length;
	final private String type;
	final private String actor;
	final private String actress;
	final private String director;
	final private Date lastUpdated;

	public Product(long movieId, String title, String year, String length, String type, String actor, String actress,
			String director, Date lastUpdated) {
		super();
		this.movieId = movieId;
		this.title = title;
		this.year = year;
		this.length = length;
		this.type = type;
		this.actor = actor;
		this.actress = actress;
		this.director = director;
		this.lastUpdated = lastUpdated;
	}

	public long getMovieId() {
		return movieId;
	}

	public String getTitle() {
		return title;
	}

	public String getYear() {
		return year;
	}

	public String getLength() {
		return length;
	}

	public String getType() {
		return type;
	}

	public String getActor() {
		return actor;
	}

	public String getActress() {
		return actress;
	}

	public String getDirector() {
		return director;
	}

	public Date getLastUpdated() {
		return lastUpdated;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((actor == null) ? 0 : actor.hashCode());
		result = prime * result + ((actress == null) ? 0 : actress.hashCode());
		result = prime * result + ((director == null) ? 0 : director.hashCode());
		result = prime * result + ((lastUpdated == null) ? 0 : lastUpdated.hashCode());
		result = prime * result + ((length == null) ? 0 : length.hashCode());
		result = prime * result + (int) (movieId ^ (movieId >>> 32));
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		result = prime * result + ((year == null) ? 0 : year.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Product other = (Product) obj;
		if (actor == null) {
			if (other.actor != null)
				return false;
		} else if (!actor.equals(other.actor))
			return false;
		if (actress == null) {
			if (other.actress != null)
				return false;
		} else if (!actress.equals(other.actress))
			return false;
		if (director == null) {
			if (other.director != null)
				return false;
		} else if (!director.equals(other.director))
			return false;
		if (lastUpdated == null) {
			if (other.lastUpdated != null)
				return false;
		} else if (!lastUpdated.equals(other.lastUpdated))
			return false;
		if (length == null) {
			if (other.length != null)
				return false;
		} else if (!length.equals(other.length))
			return false;
		if (movieId != other.movieId)
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		if (year == null) {
			if (other.year != null)
				return false;
		} else if (!year.equals(other.year))
			return false;
		return true;
	}
}
