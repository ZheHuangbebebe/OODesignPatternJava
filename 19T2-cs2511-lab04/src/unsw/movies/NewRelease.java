package unsw.movies;

public class NewRelease implements Price {

    @Override
    public double getCharge(int daysRented) {
        return daysRented * 3;
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
