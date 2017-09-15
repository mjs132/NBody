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
		double distanceX = this.myXPos - pp.myXPos;
		double distance = this.calcDistance(pp);
		double forceX = force * distanceX / distance;
		/*if(forceX < 0 && pp.myXPos > this.myXPos) {
			forceX += forceX * -2;
		}*/
		return forceX;
	}
	
	public double calcForceExertedByY(Planet pp) {
		double force = this.calcForceExertedBy(pp);
		double distanceY = this.myYPos - pp.myYPos;
		double distance = this.calcDistance(pp);
		double forceY = force * distanceY / distance;
		/*if(forceY < 0 && pp.myYPos > this.myYPos) {
			forceY += forceY * -2;
		}*/
		return forceY;
	}

	public double calcNetForceExertedByX(Planet[] allPlanets){
		double forceX = 0;
		for (Planet p : allPlanets) {
			if(!p.equals(this)) {
				if(p.myXPos > this.myXPos) {
					forceX += this.calcForceExertedByX(p);
				}
				else {
					forceX -= this.calcForceExertedByX(p);
				}
			}
		}
		return forceX;
	}
	
	public double calcNetForceExertedByY(Planet[] allPlanets) {
		double forceY = 0; 
		for (Planet p : allPlanets) {
			if(!p.equals(this)) {
				if(p.myYPos > this.myYPos) {
					forceY += this.calcForceExertedByY(p);
				}
				else {
					forceY -= this.calcForceExertedByY(p);
				}
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