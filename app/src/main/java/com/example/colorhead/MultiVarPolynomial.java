package com.example.colorhead;

import java.util.ArrayList;
/**
 * Models the behavior of a multivariable polynomial equation of degree n >= 0
 * of the form y = A + B1*b + C1*c + D1*d + ... + B2*b^2 + C2*c^2 + D2*d^2 + ... + Bn*b^n + Cn*c^n + Dn*d^n + ... 
 * from ColorHead's mathematical model used to relate a color to a quantitative value
 * @author Maxi Attiogbe
 *
 */
public class MultiVarPolynomial extends Polynomial {

	//Data 
	ArrayList<String> varList;
	
	//Constructors
	/**
	 * Default constructor
	 */
	public MultiVarPolynomial() {
		super();
		varList = new ArrayList<String>();
	}

	//Methods
	
	public double evaluate(ArrayList<Double> varValues) {
		 double sum = this.getCoefficients().get(0);
	     
		 for (int i = 0; i < varValues.size(); i ++) {
			 for (int j = 1; j <= (this.getCoefficients().size() - 1)/varValues.size(); j++ ){
			        sum += this.getCoefficients().get((i+1) + varValues.size()*(j-1)) * Math.pow(varValues.get(i), j);
			 }
		 }
		    
		    return sum;
	}


	@Override
	public String print() {
		String str = "";
		int coeffNum;
		
		if(this.getCoefficients().size() != 1 && (this.getCoefficients().size()-1) % varList.size() != 0) {
			return str += "ERROR - This polynomial does not have a valid number of terms.";
		}
		
		if(this.getCoefficients().size() == 1) { // if the polynomial is only a constant term
			return str+= this.getCoefficients().get(0);
		}
		//start with the constant term
		str+= this.getCoefficients().get(0) + " + "; 
		
		int exponent = 1;
		// add the remaining terms before the highest degree terms
		for (int i = 1; i < this.getCoefficients().size() - varList.size(); i+= varList.size()) { 
			coeffNum = i;
			for (int j = 0; j < varList.size(); j++) {
				if(exponent == 1)
					str += this.getCoefficients().get(coeffNum) + "*" + varList.get(j) + " + ";
				if(exponent > 1)
					str += this.getCoefficients().get(coeffNum) + "*" + varList.get(j) + "^" + exponent + " + ";
				coeffNum++;
			}
			exponent++;
		}
		
		coeffNum = this.coefficients.size() - varList.size();
		
		//add the highest degree terms except the last one
		for (int i = 0; i < varList.size() - 1; i++) {
			if((this.getCoefficients().size() - 1)/varList.size() == 1)
				str += this.getCoefficients().get(coeffNum) + "*" + varList.get(i) + " + ";
			if((this.getCoefficients().size() - 1)/varList.size() > 1)
				str += this.getCoefficients().get(coeffNum) + "*" + varList.get(i) + "^" + (this.getCoefficients().size() - 1)/varList.size() + " + ";
			coeffNum++;
		}
	
		// add the last term
		if((this.getCoefficients().size() - 1)/varList.size() == 1)
			str += this.getCoefficients().get(this.getCoefficients().size() - 1) + "*" + varList.get(varList.size() - 1);
		if((this.getCoefficients().size() - 1)/varList.size() > 1)
			str += this.getCoefficients().get(this.getCoefficients().size() - 1) + "*" + varList.get(varList.size() - 1) + "^" + (this.getCoefficients().size() - 1)/varList.size();
		
		return str;
	}

	public ArrayList<String> getVarList() {
		return varList;
	}

	public void setVarList(ArrayList<String> varList) {
		this.varList = varList;
	}
	
	
}

