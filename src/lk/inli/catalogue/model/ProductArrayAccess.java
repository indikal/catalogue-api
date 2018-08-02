package lk.inli.catalogue.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class ProductArrayAccess implements ProductAccessible {
	private List<Product> productList;
	static private final int MAX_NUMBER_OF_PRODUCTS_PER_PAGE = 10;

	public ProductArrayAccess() {
		this.loadProductArray();
	}

	@Override
	public Product getMovieByIndex(long index) {
		return ((int) index < productList.size()) ? productList.get((int) index) : null;
	}

	@Override
	public Product getMovieById(long id) {
		for (Product question : productList) {
			if (question.getMovieId() == id) {
				return question;
			}
		}
		return null;
	}

	@Override
	public Product getRandomMovie() {
		int index = (new Random()).nextInt(productList.size());
		return productList.get(index);
	}

	@Override
	public List<Product> getMovieList(long offset) {
		long start = offset;
		if (start < 0) {
			start = 0;
		}
		if (start >= productList.size()) {
			start = productList.size();
		}
		long end = start + MAX_NUMBER_OF_PRODUCTS_PER_PAGE;
		if (end >= productList.size()) {
			end = productList.size();
		}

		return productList.subList((int) start, (int) end);
	}

	@Override
	public List<Product> getSpecifiedMovieList(long... id) {

		final List<Long> ids = Arrays.stream(id).boxed().collect(Collectors.toList());

		return this.productList.stream().filter(p -> ids.contains(p.getMovieId())).collect(Collectors.toList());

	}

	@Override
	public long getMovieListSize() {
		return productList.size();
	}

	private void loadProductArray() {
		productList = new ArrayList<>();
		productList.add((new ProductBuilder()).movieId(1).year("1989").length("88").title("Final Notice")
				.type("Mystery").actor("Gerard, Gil").actress("Anderson, Melody").director("Stern, Steven Hilliard")
				.lastUpdated(new Date()).build());
		productList.add((new ProductBuilder()).movieId(2).year("1982").length("128").title("Ballad of Narayama")
				.type("Drama").actor("Ogata, Ken").actress("Sakamoto, Sumiko").director("Imamura, Shohei")
				.lastUpdated(new Date()).build());
		productList.add((new ProductBuilder()).movieId(3).year("1990").length("105").title("Guilty by Suspicion")
				.type("Drama").actor("De Niro, Robert").actress("Bening, Annette").director("Winkler, Irwin")
				.lastUpdated(new Date()).build());
		productList.add((new ProductBuilder()).movieId(4).year("1935").length("90").title("Swedenhielms")
				.type("Drama").actor("Westergren, Hokan").actress("Bergman, Ingrid").director("Molander, Gustaf")
				.lastUpdated(new Date()).build());
		productList.add((new ProductBuilder()).movieId(5).year("1991").length("91").title("Raw Nerve").type("Mystery")
				.actor("Ford, Glenn").actress("Bergman, Sandahl").director("Prior, David A.").lastUpdated(new Date())
				.build());
		productList.add((new ProductBuilder()).movieId(6).year("1989").length("86").title("Let It Ride")
				.type("Comedy").actor("Dreyfuss, Richard").actress("Garr, Teri").director("Pytka, Joe")
				.lastUpdated(new Date()).build());
		productList.add((new ProductBuilder()).movieId(7).year("1989").length("90").title("New Year's Day")
				.type("Comedy").actor("Jaglom, Henry").actress("Jakobsen, Maggie").director("Jaglom, Henry")
				.lastUpdated(new Date()).build());
		productList.add((new ProductBuilder()).movieId(8).year("1970").length("130").title("Fellini Satyricon")
				.type("Drama").actor("Potter, Martin").actress("Keller, Hiram").director("Fellini, Federico")
				.lastUpdated(new Date()).build());
		productList.add((new ProductBuilder()).movieId(9).year("1960").length("103").title("Time Machine")
				.type("Science Fiction").actor("Taylor, Rod").actress("Mimieux, Yvette").director("Pal, George")
				.lastUpdated(new Date()).build());
		productList.add((new ProductBuilder()).movieId(10).year("1990").length("95").title("Dangerous Pursuit")
				.type("Mystery").actor("Harrison, Gregory").actress("Powers, Alexandra").director("Stern, Sandor")
				.lastUpdated(new Date()).build());
		productList.add((new ProductBuilder()).movieId(11).year("1986").length("104").title("Best of Times")
				.type("Comedy").actor("Williams, Robin").actress("Reed, Pamela").director("Spottiswoode, Roger")
				.lastUpdated(new Date()).build());
	}
}
