package unsw.movies;

public class Regular implements Price {

    @Override
    public double getCharge(int daysRented) {
        double charge = 2;
        if (daysRented > 2)
            charge += (daysRented - 2) * 1.5;
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
		return new Regular();
	}

	@Override
	public Price makeClassic() {
		// TODO Auto-generated method stub
		return new Classic();
	}
}
