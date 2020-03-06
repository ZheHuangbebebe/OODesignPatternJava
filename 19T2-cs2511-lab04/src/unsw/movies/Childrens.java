package unsw.movies;

public class Childrens implements Price {

    @Override
    public double getCharge(int daysRented) {
        double charge = 1.5;
        if (daysRented > 3)
            charge += (daysRented - 3) * 1.5;
        return charge;
    }
    
	@Override
	public Price makeChildrens() {
		// TODO Auto-generated method stub
		return new Childrens();
	}

	@Override
	public Price makeRegular() {
		// TODO Auto-generated method stub
		return this;
	}

	@Override
	public Price makeClassic() {
		// TODO Auto-generated method stub
		return new Classic();
	}

}
