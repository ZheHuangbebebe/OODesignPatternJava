package unsw.movies;

public class Movie {

    private String title;
    private Price price;

    public Movie(String title) {
        this.title = title;
        this.price = new NewRelease();
    }

    public void setPrice(Price arg) {
        price = arg;
    }

    public String getTitle() {
        return title;
    }

    public double getCharge(int daysRented) {
        return price.getCharge(daysRented);
    }
    
    public void makeChildrens() {
    	price = price.makeChildrens();
    }
    public void makeRegular() {
    	price = price.makeRegular();
    }
    public void makeClassic() {
    	price = price.makeClassic();
    }

  
}