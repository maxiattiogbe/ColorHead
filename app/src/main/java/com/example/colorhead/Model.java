package com.example.colorhead;

import com.example.colorhead.Jama.Matrix;

import java.util.ArrayList;


/**
 * Models the behavior of Color's mathematical model
 * used to relate a color to a value
 * @author Maxi Attiogbe
 *
 */

public class Model {

	//Data
	
	//Specific description giving more information about this model such as the purpose of this model 
	//as well as what type of data was collected and how
	String description;
	
	//Separate polynomial curves that will be used to link a given type of quantitative data 
	//to RGB values
	Polynomial red;
	Polynomial green;
	Polynomial blue;
	
	//Type of quantitative data that this Model will link color to
	String indVar;
	
	ArrayList<Sample> data = new ArrayList<Sample>();
	
	//Residual sum of squares (a measure of error)
	double rss; 
	
	//Constructors
	
	/**
	 * Default constructor that constructs an empty Model object
	 * with an empty String storing no description;
	 * default red, green, and blue polynomials all equal to 0x^0;
	 * an empty String storing no independent variable; an empty ArrayList
	 * storing no data Samples; and an RSS of 0
	 */
	public Model() {
		description = "";
		red = new Polynomial();
		green = new Polynomial();
		blue = new Polynomial();
		indVar = "";
		data.add(new Sample());
		rss = 0.0;
	}
	
	/**
	 * Constructor that constructs a Model object
	 * with an empty String storing no description;
	 * default red, green, and blue polynomials all equal to 0x^0;
	 * an empty String storing no independent variable; an ArrayList
	 * storing data Samples; and an RSS of 0
	 * @param data an ArrayList storing data Samples
	 */
	public Model(ArrayList<Sample> data) {
		description = "";
		red = new Polynomial();
		green = new Polynomial();
		blue = new Polynomial();
		
		int i = 1;
		
		/*
		 * Continue adding Samples to the data list if starting with the second Sample, each Sample represents
		 * the same type of data as the previous Sample, otherwise stop	
		 */
		while (i < data.size() && data.get(i-1).getValueLabel().equalsIgnoreCase(data.get(i).getValueLabel())) { 
			this.data.add(data.get(i-1));
			i++;
		}
		
		/*
		 * Exiting the while loop with only one more Sample to add means all Samples represent the same data.
		 * In contrast, exiting the while loop with more than one Sample to add means not all Samples represent the same data
		 * and therefore the data is invalid.
		 */
		if (this.data.size() == data.size() - 1) {
			this.data.add(data.get(data.size() - 1));
			indVar = this.data.get(0).getValueLabel(); // all Samples represent the same data so the type of data can be obtained from the first Sample
		} else
			indVar = "Invalid Data"; // not all Samples represent the same data
	}
	
	/**
	 * Constructor that constructs a Model object
	 * with an empty String storing no description;
	 * default red, green, and blue polynomials all equal to 0x^0;
	 * a String storing the independent variable; an ArrayList
	 * storing data Samples; and an RSS of 0
	 * @param var a String storing the independent variable
	 * @param data an ArrayList storing data Samples
	 */
	public Model(String var, ArrayList<Sample> data) {
		description = "";
		red = new Polynomial();
		green = new Polynomial();
		blue = new Polynomial();
		
		int i = 0;
		
		/*
		 * Continue adding Samples to the data list if the data type of each Sample is that same as the one
		 * stored in the String var, otherwise stop	
		 */
		while (i < data.size() && data.get(i).getValueLabel().equalsIgnoreCase(var)) { 
			this.data.add(data.get(i));
			i++;
		}
		
		/*
		 * Exiting the while loop with no more Samples to add means all Samples represent the same data.
		 * In contrast, exiting the while loop with at least one Sample to add means not all Samples represent the same data
		 * and therefore the data is invalid.
		 */
		if (this.data.size() == data.size()) {
			indVar = this.data.get(0).getValueLabel(); // all Samples represent the same data so the type of data can be obtained from the first Sample
		} else
			indVar = "Invalid Data"; // not all Samples represent the same data	
		
		rss = 0.0;
	}
	
	/**
	 * Constructor that constructs a Model object
	 * with a String storing a description;
	 * default red, green, and blue polynomials all equal to 0x^0;
	 * a String storing the independent variable; an ArrayList
	 * storing data Samples; and an RSS of 0
	 * @param description  a String storing a description of this mathematical model
	 * @param var a String storing the independent variable
	 * @param data an ArrayList storing data Samples
	 */
	public Model(String description, String var, ArrayList<Sample> data) {
		this.description = description;
		red = new Polynomial();
		green = new Polynomial();
		blue = new Polynomial();
		
		int i = 0;
		
		/*
		 * Continue adding Samples to the data list if the data type of each Sample is that same as the one
		 * stored in the String var, otherwise stop	
		 */
		while (i < data.size() && data.get(i).getValueLabel().equalsIgnoreCase(var)) { 
			this.data.add(data.get(i));
			i++;
		}
		
		/*
		 * Exiting the while loop with no more Samples to add means all Samples represent the same data.
		 * In contrast, exiting the while loop with at least one Sample to add means not all Samples represent the same data
		 * and therefore the data is invalid.
		 */
		if (this.data.size() == data.size()) {
			indVar = this.data.get(0).getValueLabel(); // all Samples represent the same data so the type of data can be obtained from the first Sample
		} else
			indVar = "Invalid Data"; // not all Samples represent the same data	
		
		rss = 0.0;
	}
	
	//Methods
	
	/**
	 * Use polynomial regression via matrices (see individual fit methods) to fit the data 
	 * of this mathematical model to three polynomials of a given degree and make them the 
	 * red, green, and blue polynomial equations and output the RSS
	 * @param degree the given degree 
	 * @param low an integer lower independent data variable limit
	 * @param high an integer higher independent data variable limit
	 */
	public void fitData(int degree, int low, int high){
	   fitRed(degree);
	   //System.out.println("R(x) = " + red.print());
	   fitGreen(degree);
	  // System.out.println("B(x) = " + green.print());
	   fitBlue(degree);
	   //System.out.println("G(x) = " + blue.print());
	   
	   for(Sample s: this.data)
		   rss += Math.pow(s.getValue()-this.estimate(s.getRed(),s.getGreen(),s.getBlue(),low, high),2);
	  
	  // System.out.println();
	   //System.out.println("RSS = " + rss);
	}
	
	/**
	 * Use polynomial regression via matrices (see individual fit methods) to fit the data 
	 * of this mathematical model to a polynomial of a given degree for red color intensity
	 * @param degree the given degree
	 */
	public void fitRed(int degree) {
		int n = this.getData().size();
		double [][] yVals = new double [n][1]; // the Y matrix
		double [][] xValsVand = new double [n][degree + 1]; // the X matrix (Vandermonde matrix)
		ArrayList<Double> redCoeff = new ArrayList<Double>();
		rss = 0.0;
		
		// Get y-values
		for(int r = 0; r < n; r++) 
			yVals[r][0] = this.getData().get(r).getRed();
		
		//Put y-values into the Y matrix
		Matrix y = new Matrix(yVals);
		
		for(int r = 0; r < n; r++) {
			for (int c = 0; c <= degree; c++) {
				xValsVand[r][c] = Math.pow(this.getData().get(r).getValue(), c);
			}
		}
		
		Matrix x = new Matrix(xValsVand);

		Matrix b = x.transpose().times(x).inverse().times(x.transpose()).times(y);

		for(int i = 0; i <= degree; i ++)
			redCoeff.add(b.get(i, 0));
		
		red.setCoefficients(redCoeff);		
	}
	
	/**
	 * Use polynomial regression via matrices (see individual fit methods) to fit the data 
	 * of this mathematical model to a polynomial of a given degree for green color intensity
	 * @param degree the given degree
	 */
	public void fitGreen(int degree) {
		int n = this.getData().size();
		double [][] yVals = new double [n][1]; // the Y matrix
		double [][] xValsVand = new double [n][degree + 1]; // the X matrix (Vandermonde matrix)
		ArrayList<Double> greenCoeff = new ArrayList<Double>();
		rss = 0.0;
		
		// Get y-values
		for(int r = 0; r < n; r++) 
			yVals[r][0] = this.getData().get(r).getGreen();
		
		//Put y-values into the Y matrix
		Matrix y = new Matrix(yVals);
		
		for(int r = 0; r < n; r++) {
			for (int c = 0; c <= degree; c++) {
				xValsVand[r][c] = Math.pow(this.getData().get(r).getValue(), c);
			}
		}
		
		Matrix x = new Matrix(xValsVand);
		
		Matrix b = x.transpose().times(x).inverse().times(x.transpose()).times(y);

		for(int i = 0; i <= degree; i ++)
			greenCoeff.add(b.get(i, 0));
		
		green.setCoefficients(greenCoeff);
			
	}
	
	/**
	 * Use polynomial regression via matrices (see individual fit methods) to fit the data 
	 * of this mathematical model to a polynomial of a given degree for blue color intensity
	 * @param degree the given degree
	 */
	public void fitBlue(int degree) {
		int n = this.getData().size();
		double [][] yVals = new double [n][1]; // the Y matrix
		double [][] xValsVand = new double [n][degree + 1]; // the X matrix (Vandermonde matrix)
		ArrayList<Double> blueCoeff = new ArrayList<Double>();
		rss = 0.0;
		
		// Get y-values
		for(int r = 0; r < n; r++) 
			yVals[r][0] = this.getData().get(r).getBlue();
		
		//Put y-values into the Y matrix
		Matrix y = new Matrix(yVals);
		
		for(int r = 0; r < n; r++) {
			for (int c = 0; c <= degree; c++) {
				xValsVand[r][c] = Math.pow(this.getData().get(r).getValue(), c);
			}
		}
		
		Matrix x = new Matrix(xValsVand);
		
		Matrix b = x.transpose().times(x).inverse().times(x.transpose()).times(y);

		for(int i = 0; i <= degree; i ++)
			blueCoeff.add(b.get(i, 0));
		
		blue.setCoefficients(blueCoeff);	
	}
	
	/**
	 * Estimate an independent variable value within a given limit and given its corresponding integer 
	 * red, green, and blue color intensities and use differential calculus (see derivative method)
	 * and Newton's method (see findRoots method) to find an estimate that minimizes error
	 * @param r red color intensity
	 * @param g green color intensity
	 * @param b blue color intensity
	 * @return a double storing an estimate for the independent variable value for the input
	 */
	public double estimate(double r, double g, double b, int lowerLimit, int upperLimit) {
		double output = 0.0;
		
		ArrayList<Double> redCoeff = new ArrayList<Double>();
		ArrayList<Double> greenCoeff = new ArrayList<Double>();
		ArrayList<Double> blueCoeff = new ArrayList<Double>();
		
		redCoeff.add(r);
		greenCoeff.add(g);
		blueCoeff.add(b);
		
		Polynomial redConstPoly = new Polynomial(redCoeff);
		Polynomial greenConstPoly = new Polynomial(greenCoeff);
		Polynomial blueConstPoly = new Polynomial(blueCoeff);
		
		Polynomial errorPoly = redConstPoly.subtract(this.getRed()).square().
				add(greenConstPoly.subtract(this.getGreen()).square()).
				add(blueConstPoly.subtract(this.getBlue()).square());
		
		Polynomial errorPolyCopy = redConstPoly.subtract(this.getRed()).square().
				add(greenConstPoly.subtract(this.getGreen()).square()).
				add(blueConstPoly.subtract(this.getBlue()).square());
		
		errorPoly.derivative();
		output = errorPoly.findRoots(lowerLimit,upperLimit).get(0);
		
		for(double d: errorPoly.findRoots(lowerLimit,upperLimit)) {
			
			if(errorPolyCopy.evaluate(output) > errorPolyCopy.evaluate(d))
				output = d;
		}
		
		return output;
	}

	/**
	 * Produce a String storing the data list, the type of data in the list, 
	 * the three polynomial equations used to model them based on RGB values (one polynomial each for red, green, and blue),
	 * and the residual sum of squares (a measure of error) for the model
	 * @return a String storing the data list, the type of data in the list, 
	 * the three polynomial equations used to model them based on RGB values (one polynomial each for red, green, and blue),
	 * and the residual sum of squares (a measure of error) for the model
	 */
	public String print() {
		String str = "";
		return str;
	}
	
	/**
	 * Print a matrix (2D array) with one line per row
	 * @param mat a matrix (2D array)
	 * @return a String representing a matrix (2D array) with one line per row
	 */
	public String printMatrix(double mat[][]) {
		String str = "";
		
        // Loop through all rows 
        for (int r = 0; r < mat.length; r++) {
        	
        	// Loop through all elements of current row 
            for (int c = 0; c < mat[r].length; c++) 
                str += mat[r][c] + " "; 
            
            str += "\n"; // start a new line 
        }
        return str;    
    } 
	
	/**
	 * Get the red color intensity Polynomial
	 * @return the red color intensity Polynomial
	 */
	public Polynomial getRed() {
		return red;
	}

	/**
	 * Set the red color intensity Polynomial
	 * @param red a red color intensity Polynomial
	 */
	public void setRed(Polynomial red) {
		this.red = red;
	}

	/**
	 * Get the green color intensity Polynomial
	 * @return the green color intensity Polynomial
	 */
	public Polynomial getGreen() {
		return green;
	}

	/**
	 * Set the green color intensity Polynomial
	 * @param green a green color intensity Polynomial
	 */
	public void setGreen(Polynomial green) {
		this.green = green;
	}

	/**
	 * Get the blue color intensity Polynomial
	 * @return the blue color intensity Polynomial
	 */
	public Polynomial getBlue() {
		return blue;
	}

	/**
	 * Set the blue color intensity Polynomial
	 * @param blue a blue color intensity Polynomial
	 */
	public void setBlue(Polynomial blue) {
		this.blue = blue;
	}

	/**
	 * Get a String storing the type of quantitative data that this Model will link color to
	 * @return a String storing the type of quantitative data that this Model will link color to
	 */
	public String getIndVar() {
		return indVar;
	}

	/**
	 * Set the type of quantitative data that this Model will link color to
	 * @param indVar a String storing the type of quantitative data that this Model will link color to
	 */
	public void setIndVar(String indVar) {
		this.indVar = indVar;
	}

	/**
	 * Get an ArrayList of the data Samples
	 * @return an ArrayList of the data Samples
	 */
	public ArrayList<Sample> getData() {
		return data;
	}

	/**
	 * Input an ArrayList of data Samples
	 * @param data an ArrayList of data Samples
	 */
	public void setData(ArrayList<Sample> data) {
		this.data = data;
	}

	/**
	 * Get the RSS (a measure of error called residual sum of square) of this mathematical model
	 * @return the RSS of this mathematical model
	 */
	public double getrss() {
		return rss;
	}

	/**
	 * Set the RSS (a measure of error called residual sum of square) of this mathematical model
	 * @param rss the RSS of this mathematical model
	 */
	public void setrss(double rss) {
		this.rss = rss;
	}

}