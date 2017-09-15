import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class NBody {
	
	public static double readRadius(String fname) {
		double rad = 0;
		try {
				Scanner scan = new Scanner(new File(fname));
				scan.nextInt();
				rad = scan.nextDouble();
				scan.close();
				return rad;
			} catch (FileNotFoundException e){
				System.out.println("File not found");
				System.exit(0);
			}
			return 0;
	}
	
	public static Planet[] readPlanets(String fname) {
		try {
			Scanner scan = new Scanner(new File(fname));
			int numOfPlanets = scan.nextInt();
			scan.nextDouble();
			Planet[] planets = new Planet[numOfPlanets];
			for(int i = 0; i < planets.length; i++) {
				double XPos = scan.nextDouble();
				double YPos = scan.nextDouble();
				double XVel = scan.nextDouble();
				double YVel = scan.nextDouble();
				double Mass = scan.nextDouble();
				String FileName = scan.next();
				planets[i] = new Planet(XPos, YPos, XVel, YVel, Mass, FileName);
			}
			scan.close();
			return planets;
		} catch (FileNotFoundException e) {
			System.out.println("File not found");
			System.exit(0);
			return null;
		}
	}
	
	public static void main(String[] args){
		double totalTime = 157788000.0;
		double dt = 25000.0;
		String pfile = "data/planets.txt";
		if (args.length > 2) {
			totalTime = Double.parseDouble(args[0]);
			dt = Double.parseDouble(args[1]);
			pfile = args[2];
		}
		
		String fname= "./data/planets.txt";
		Planet[] planets = readPlanets(fname);
		double radius = readRadius(fname);
		
		StdDraw.setScale(-radius, radius);
		StdDraw.picture(0,0,"images/starfield.jpg");
		for(Planet p : planets) {
			Planet.draw(p.myXPos, p.myYPos, p.myFileName);
		}
		for(double t = 0.0; t < totalTime; t += dt) {
			double[] xForces = new double[planets.length];
			double[] yForces = new double[planets.length];
			
			for(int i = 0; i < planets.length; i++) {
				xForces[i] = planets[i].calcNetForceExertedByX(planets);
				yForces[i] = planets[i].calcNetForceExertedByY(planets);
			}
			
			for(int i = 0; i < planets.length; i++) {
				planets[i].update(dt, xForces[i], yForces[i]);
			}
			StdDraw.picture(0,0,"images/starfield.jpg");
			for(Planet p : planets) {
				Planet.draw(p.myXPos, p.myYPos, p.myFileName);
			}
			StdDraw.show(10);
		}
		System.out.printf("%d\n", planets.length);
		System.out.printf("%.2e\n", radius);
		for (int i = 0; i < planets.length; i++) {
		    System.out.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
		   		              planets[i].myXPos, planets[i].myYPos, 
		                      planets[i].myXVel, planets[i].myYVel, 
		                      planets[i].myMass, planets[i].myFileName);	
		}
	}
}
