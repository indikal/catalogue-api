package lk.inli.catalogue.model;

import java.util.List;

public interface ProductAccessible {

	Product getMovieByIndex(long index);

	Product getMovieById(long id);

	Product getRandomMovie();

	List<Product> getMovieList(long offset);

	List<Product> getSpecifiedMovieList(long... id);

	long getMovieListSize();
}
