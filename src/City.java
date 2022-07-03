public class City {
	private double cityId;
    private double xCoor;
    private double yCoor;
    private boolean isVisited;
 
    public City(double cityId, double xCoor, double yCoor){ //Constructor method of the city class.
    	this.cityId = cityId; 
    	this.xCoor = xCoor; 
    	this.yCoor = yCoor; 
    	this.isVisited = false;
    }
    
    public double getXCoor() { 
		return this.xCoor;
	}
	public void setXCoor(double xCoor) {
		this.xCoor = xCoor;
	}
	
	public double getYCoor() {
		return this.yCoor;
	}
	public void setYCoor(double yCoor) {
		this.yCoor = yCoor;
	}

	public double getcityId() {
		return cityId;
	}

	public void setcityId(double cityId) {
		this.cityId = cityId;
	}

	public boolean isVisited() {
		return isVisited;
	}

	public void setVisited(boolean isVisited) {
		this.isVisited = isVisited;
	}
	
    public double distanceBetween(City city) { // Computes the distance between 2 cities.
        double a = Math.abs(this.getXCoor() - city.getXCoor());
        double b = Math.abs(this.getYCoor() - city.getYCoor());
        double result = (double) (Math.sqrt(Math.pow(a, 2) + Math.pow(b, 2))); //Takes the sqare root.
        return result;
    }


	
}