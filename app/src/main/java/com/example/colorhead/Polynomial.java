package com.example.colorhead;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Models the behavior of a polynomial equation of degree n >= 0 from 
 * ColorHead's mathematical model used to relate a color to a quantitative value
 * @author Maxi Attiogbe
 *
 */
public class Polynomial {

	//Data
	ArrayList<Double> coefficients = new ArrayList<Double>();

	//Constructors

	/**
	 * Default constructor that adds a 0 to the coefficients ArrayList
	 * representing a default polynomial 0*x^0 = 0
	 */
	Polynomial () {
		coefficients.add(0.0);
	}


	/**
	 * Constructor that takes in an ArrayList of coefficients (stored as doubles)
	 * and adds each one to the coefficient ArrayList of this Polynomial object
	 * @param coeff an ArrayList of coefficients (stored as doubles)
	 */
	Polynomial (ArrayList<Double> coeff) {
		for(Double d: coeff)
			coefficients.add(d);
	}

	//Methods

	/**
	 * Evaluate a polynomial (of the form a + b*x + c*x^2 + ... where a, b, c, and so on are
	 * the coefficients) given an input variable value
	 * @param varValue an input variable value
	 * @return the value of the polynomial if evaluated after plugging in the input variable value
	 */
	public double evaluate(double varValue) {
		double sum = 0;

		for (int i = 0; i < coefficients.size(); i++ ){
			sum += coefficients.get(i) * Math.pow(varValue, i);
		}

		return sum;
	}


	/**
	 * Adds an inputted polynomial to this one by
	 * adding the corresponding coefficients and returning the sum
	 * @param poly an inputted polynomial
	 * @return the sum of this polynomial and the inputted one
	 */
	public Polynomial add(Polynomial poly) {
		ArrayList<Double> sumCoeff = new ArrayList<Double>();

		// check to determine if the inputted polynomial is of the same degree as this one
		if (this.getCoefficients().size() == poly.getCoefficients().size()) {
			// adding corresponding coefficients
			for(int i = 0; i < this.getCoefficients().size(); i++ ) {
				sumCoeff.add(this.getCoefficients().get(i) + poly.getCoefficients().get(i));
			}
			// check to determine if this polynomial is of higher degree than the inputted one
		} else if (this.getCoefficients().size() >= poly.getCoefficients().size()) {
			for(int i = 0; i < this.getCoefficients().size(); i++ ) {
				//add coefficients of the same degree
				if (i < poly.getCoefficients().size())
					sumCoeff.add(this.getCoefficients().get(i) + poly.getCoefficients().get(i));
					//add coefficients from the longer polynomial (this one) once the degree of the
					// inputted lower degree polynomial has been passed
				else
					sumCoeff.add(this.getCoefficients().get(i));
			}
		} else
			//if this polynomial is not of higher degree than the inputted one
			//then swap the order of addition
			return poly.add(this);

		Polynomial sum = new Polynomial(sumCoeff);
		return sum;
	}

	/**
	 *
	 * @param poly
	 * @return
	 */
	public Polynomial subtract(Polynomial poly) {
		poly.multiplyByConstant(-1.0);
		return this.add(poly);
	}

	/**
	 *
	 */
	public void multiplyByConstant(double c) {
		for(int i = 0; i < coefficients.size(); i++)
			coefficients.set(i, coefficients.get(i) * c);
	}

	/**
	 *
	 * @param poly
	 * @return
	 */
	public Polynomial multiplyByMonomial(Polynomial poly) {
		int termCount = 0;

		//count how many terms the inputted polynomial has
		for(double d: poly.getCoefficients())
			if (d != 0)
				termCount++;

		// if the polynomial truly is a monomial
		if(termCount == 1) {
			//if the polynomial is the constant 0 return the default polynomial 0x^0
			if (poly.getCoefficients().get(poly.getCoefficients().size()-1) == 0)
				return new Polynomial();
			else {
				// multiply this polynomial first by the coefficient of the monomial
				this.multiplyByConstant(poly.getCoefficients().get(poly.getCoefficients().size()-1));

				//ArrayList of coefficients for the product polynomial
				ArrayList<Double> productCoeff = new ArrayList<Double>();

				//Populate the ArrayList with an appropriate number of 0s
				for(int i = 0; i < this.getCoefficients().size(); i++) {
					productCoeff.add(0.0);
				}

				//Simulate multiplying by x^n by making a new ArrayList of coefficients
				// and adding n zeros to increase the degree of each coefficent by n
				Collections.copy(productCoeff, this.getCoefficients());

				for(int i = 0; i < poly.getCoefficients().size()-1; i++) {
					productCoeff.add(0, 0.0);
				}

				Polynomial product = new Polynomial(productCoeff);
				return product;
			}
		} else { // the inputted polynomial is not a monomial
			ArrayList<Double> nullCoeff = new ArrayList<Double>();
			return new Polynomial(nullCoeff); // a null polynomial with no coefficients
		}

	}

	/**
	 * Multiply this polynomial by an inputted polynomial by multiplying this polynomial
	 * by each term (a monomial) of the inputted polynomial and returning the sum of
	 * those products
	 * @param poly the inputted polynomial
	 * @return the product of this polynomial and the inputted one
	 */
	public Polynomial multiplyByPolynomial(Polynomial poly) {
		Polynomial product = new Polynomial();
		ArrayList<Double> coeff = new ArrayList<Double>();


		for(int i = 0; i < poly.getCoefficients().size(); i++) {
			coeff.add(poly.getCoefficients().get(i));

			for(int k = 0; k < coeff.size()-1; k++) {
				coeff.set(k, 0.0);
			}

			Polynomial p0 = new Polynomial(this.getCoefficients());
			Polynomial p = new Polynomial(coeff);

			product = product.add(p0.multiplyByMonomial(p));
		}
		return product;
	}


	/**
	 * Square this polynomial by multiplying it by itself
	 * @return the square of this polynomial
	 */
	public Polynomial square() {
		return this.multiplyByPolynomial(this);
	}

	/**
	 * Models taking the derivative of a polynomial of the form
	 *
	 * After running this method, the Polynomial object now
	 * has coefficients corresponding to those of the derivative
	 * of the original polynomial the Polynomial object originally
	 * represented
	 */
	public void derivative() {
		ArrayList<Double> newCoeff = new ArrayList<Double>();
		if (coefficients.size() == 0){
			return;
		} else if (coefficients.size() == 1){
			coefficients.remove(0);
			coefficients.add(0.0);
		} else {
			for (int i = 1; i < coefficients.size(); i++) {
				newCoeff.add(i * coefficients.get(i)); // implementation of the derivative power rule
			}
			//the derivative of a constant is 0, so it is removed from coefficients list
			coefficients.remove(0);

			Collections.copy(coefficients, newCoeff);
		}
	}

	/**
	 * Uses Newton's Method to estimate the roots of this polynomial
	 * within a given range
	 * @param lowerLimit the integer lower limit of the given range
	 * @param upperLimit the integer upper limit of the given range
	 * @return an ArrayList of doubles storing estimations for the roots of this polynomial
	 * within the given range
	 */
	public ArrayList<Double> findRoots (int lowerLimit, int upperLimit) {

		double tolerance = Math.pow(10, -9); //9 digits of accuracy
		double epsilon = Math.pow(10, -14); // Do not divide by a number smaller than this

		Polynomial p00 = new Polynomial(this.getCoefficients());
		Polynomial f = p00;

		Polynomial p01 = new Polynomial(this.getCoefficients());
		p01.derivative();
		Polynomial fPrime = p01;

		ArrayList<Double> solutions = new ArrayList<Double>();

		//Newton's Method
		for(int i = lowerLimit; i <= upperLimit; i ++) {
			double x0 = i; //initial guess

			//iterations
			for(int j = 0; j <= 50; j++) {

				if(Math.abs(fPrime.evaluate(x0)) < epsilon)
					break;

				double x1 = x0 - f.evaluate(x0)/fPrime.evaluate(x0);

				if (Math.abs(x1 - x0) <= tolerance) {
					int matches = 0;

					for(double d: solutions) {
						if (Math.abs(x1 - d) <= 0.1)
							matches++;
					}

					if (matches == 0)
						solutions.add(x1);
					break;
				}
				x0 = x1;
			}
		}
		return solutions;
	}

	/**
	 * Produce, in the form of a String, the polynomial that the Polynomial object represents
	 * @return a String storing the polynomial that the Polynomial object represents
	 */
	public String print() {
		String str = "";

		if (coefficients.get(coefficients.size()-1) != 0)
			str += coefficients.get(coefficients.size()-1) + "x^" + (coefficients.size()-1) + " ";
		else
			return str += "0.0";

		for(int i = coefficients.size()-2; i > 0; i--) {
			if(coefficients.get(i) != 0)
				if (coefficients.get(i) < 0)
					if (i != 1)
						str += "- " + Math.abs(coefficients.get(i)) + "x^" + (i) + " ";
					else
						str += "- " + Math.abs(coefficients.get(i)) + "x ";
				else
				if (i != 1)
					str += "+ " + coefficients.get(i) + "x^" + (i) + " ";
				else
					str += "+ " + coefficients.get(i) + "x ";
		}

		if (coefficients.size() > 1 && coefficients.get(0) < 0) {
			str += "- " + Math.abs(coefficients.get(0));
			return str;
		} else if (coefficients.size() > 1 &&  coefficients.get(0) > 0) {
			str += "+ " + coefficients.get(0);
			return str;
		} else
			return str;
	}


	/**
	 * Get the ArrayList of polynomial coefficients
	 * @return the ArrayList of polynomial coefficients
	 */
	public ArrayList<Double> getCoefficients() {
		return coefficients;
	}


	/**
	 * Set the ArrayList of polynomial coefficients
	 * @param coefficients the ArrayList of polynomial coefficients
	 */
	public void setCoefficients(ArrayList<Double> coefficients) {
		this.coefficients = coefficients;
	}
}