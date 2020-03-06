package unsw.movies;

public class Classic implements Price {

    @Override
    public double getCharge(int daysRented) {
        if(daysRented > 5)
        	return 2 + (daysRented -5);
        else
        	return 2;
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