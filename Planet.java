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
	
	public Planet(double xp, double xy, double xv, double yv, double mass, String filename) {
		this.myXPos = xp;
		this.myYPos = xy;
		this.myXVel = xv;
		this.myYVel = yv;
		this.myMass = mass;
		this.myFileName = filename;
	}
	
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
	
	public double calcForceExertedBy(Planet pp) {
		double r = this.calcDistance(pp);
		double G = 6.67e-11;
		double force = G * this.myMass * pp.myMass/(r*r);
		return force;
	}
	
	public double calcForceExertedByX(Planet pp) {
		double force = this.calcForceExertedBy(pp);
		double distanceX = pp.myXPos - this.myXPos;
		double distance = this.calcDistance(pp);
		double forceX = force * distanceX / distance;
		return forceX;
	}
	
	public double calcForceExertedByY(Planet pp) {
		double force = this.calcForceExertedBy(pp);
		double distanceY = pp.myYPos - this.myYPos;
		double distance = this.calcDistance(pp);
		double forceY = force * distanceY / distance;
		return forceY;
	}

	public double calcNetForceExertedByX(Planet[] allPlanets){
		double forceX = 0;
		for (Planet p : allPlanets) {
			if(!p.equals(this)) {
				forceX += this.calcForceExertedByX(p);
				
			}
		}
		return forceX;
	}
	
	public double calcNetForceExertedByY(Planet[] allPlanets) {
		double forceY = 0; 
		for (Planet p : allPlanets) {
			if(!p.equals(this)) {
				forceY += this.calcForceExertedByY(p);
			}
		}
		return forceY;
	}
	
	public void update(double seconds, double xforce, double yforce) {
		double accelX = xforce / this.myMass;
		double accelY = yforce / this.myMass;
		this.myXVel += seconds * accelX;
		this.myYVel += seconds * accelY;
		this.myXPos += seconds * this.myXVel;
		this.myYPos += seconds * this.myYVel;
	}
	
	public static void draw(double myXPos, double myYPos, String myFileName) {
		StdDraw.picture(myXPos, myYPos,"images/"+myFileName);
	}
}