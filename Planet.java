public class Planet {
	double myXPos;
	double myYPos;
	double myXVel;
	double myYVel;
	double myMass;
	String myFileName;
	
public Planet(Planet p) {
	myXPos = p.myXPos;
	myYPos = p.myYPos;
	myXVel = p.myXVel;
	myYVel = p.myYVel;
	myMass = p.myMass;
	myFileName = p.myFileName;
}
	
	/*
	 * Return Planet
	 * @param xp, xy, xv, yv, mass, and file name
	 */
	public Planet(double xp, double xy, double xv, double yv, double mass, String filename) {
		this.myXPos = xp;
		this.myYPos = xy;
		this.myXVel = xv;
		this.myYVel = yv;
		this.myMass = mass;
		this.myFileName = filename;
	}
	
	/*
	 * Return distance between points
	 * @ param pp is the planet you are trying to calculate distance from
	 * @return distance between points
	 */
	public double calcDistance(Planet pp) {
		double x1 = this.myXPos;
		double y1 = this.myYPos;
		double x2 = pp.myXPos;
		double y2 = pp.myYPos;
		double distance = Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
		if(distance < 0) {
			distance += distance * -2;
		}
		return distance;
	}
	
	/*
	 * Return the force from planet pp
	 * @param pp is the planet which is exerting force
	 * @return magnitude and direction(+ or -) of force 
	 */
	public double calcForceExertedBy(Planet pp) {
		double r = this.calcDistance(pp);
		double G = 6.67e-11;
		double force = G * this.myMass * pp.myMass/(r*r);
		return force;
	}
	
	/*
	 * Return the force exerted by x
	 * @param pp is the planet which is exerting force
	 * @return the x component of force
	 */
	public double calcForceExertedByX(Planet pp) {
		double force = this.calcForceExertedBy(pp);
		double distanceX = pp.myXPos - this.myXPos;
		double distance = this.calcDistance(pp);
		double forceX = force * distanceX / distance;
		return forceX;
	}
	
	/*
	 * Return the force exerted by y
	 * @param pp is the planet which is exerting force
	 * @return the y component of force
	 */
	public double calcForceExertedByY(Planet pp) {
		double force = this.calcForceExertedBy(pp);
		double distanceY = pp.myYPos - this.myYPos;
		double distance = this.calcDistance(pp);
		double forceY = force * distanceY / distance;
		return forceY;
	}

	/*
	 * Return the net force exerted by x by array
	 * @param all planets is an array of the planet which are exerting force
	 * @return the net force in x direction
	 */
	public double calcNetForceExertedByX(Planet[] allPlanets){
		double forceX = 0;
		for (Planet p : allPlanets) {
			if(!p.equals(this)) {
				forceX += this.calcForceExertedByX(p);
			}
		}
		return forceX;
	}
	
	/*
	 * Return the net force exerted by y by array
	 * @param all planets is an array of the planet which are exerting force
	 * @return the net force in y direction
	 */
	public double calcNetForceExertedByY(Planet[] allPlanets) {
		double forceY = 0; 
		for (Planet p : allPlanets) {
			if(!p.equals(this)) {
				forceY += this.calcForceExertedByY(p);
			}
		}
		return forceY;
	}
	
	/*
	 * Updates the variables in Planet class for variable
	 * @param seconds is the time
	 * @param xforce is the net forces in x direction
	 * @param yforce is the net forces in y direction
	 */
	public void update(double seconds, double xforce, double yforce) {
		double accelX = xforce / this.myMass;
		double accelY = yforce / this.myMass;
		this.myXVel += seconds * accelX;
		this.myYVel += seconds * accelY;
		this.myXPos += seconds * this.myXVel;
		this.myYPos += seconds * this.myYVel;
	}
	
	/*
	 * Draws new planet
	 * @param myXPos is the new x position of planet
	 * @param myYPos is the new y position of planet
	 * @param myFileName is the file name for the planet
	 */
	public static void draw(double myXPos, double myYPos, String myFileName) {
		StdDraw.picture(myXPos, myYPos,"images/"+myFileName);
	}
}