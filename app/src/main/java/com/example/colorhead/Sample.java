package com.example.colorhead;

/**
 * Models a sample that saves a single unit of data
 * for the Color app
 * @author Maxi Attiogbe
 *
 */
public class Sample {

	//Data

	//red, green, and blue color intensities
	int red;
	int green;
	int blue;
	
	//What type of data is color being linked to
	String valueLabel;
	
	//The numerical value of the quantity linked to color
	double value;
	
	
	//Constructors
	
	/**
	 * Default constructor that sets the red, green, and blue values to 0;
	 * sets valueLabel to an empty string; and sets value equal to 0 
	 */
	public Sample() {
		red = 0;
		green = 0;
		blue = 0;
		valueLabel = "";
		value = 0.0;
	}
	
	/**
	 * Constructs a Sample object to save a single unit of data
	 * @param numValType a String storing the type of data saved
	 * @param r an integer red color intensity (0-255)
	 * @param g an integer green color intensity (0-255)
	 * @param b an integer blue color intensity (0-255)
	 */
	public Sample(String numValType, int r, int g, int b) {
		red = r;
		green = g;
		blue = b;
		valueLabel = numValType;
		value = 0.0;
	}
	
	/**
	 * Constructor with given red, green, and blue values 
	 * as well as the type of numerical value being linked to color
	 * @param numValType a String storing the type of numerical value being linked to color
	 * @param r an integer value (0-255) for the red color intensity
	 * @param g an integer value (0-255) for the green color intensity
	 * @param b an integer value (0-255) for the blue color intensity
	 */
	public Sample(String numValType, double val, int r, int g, int b) {
		red = r;
		green = g;
		blue = b;
		valueLabel = numValType;
		value = val;
	}

	//Methods
	
	/**
	 * Produces a String containing the data values stored in the Sample object
	 * @return a String containing the data values stored in the Sample object
	 */
	public String print() {
		return "Type of Quantitative Data: " + valueLabel + ", Numerical Value: " + value + 
				", Red: " + red + ", Green: " + green + ", Blue: " + blue ;
	}
	
	/**
	 * Get red color intensity value (0-255)
	 * @return Red color intensity value (0-255)
	 */
	public int getRed() {
		return red;
	}

	/**
	 * Set red color intensity value (0-255)
	 * @param red Red color intensity value (0-255)
	 */
	public void setRed(int red) {
		this.red = red;
	}

	/**
	 * Get green color intensity value (0-255)
	 * @return Green color intensity value (0-255)
	 */
	public int getGreen() {
		return green;
	}

	/**
	 * Set green color intensity value (0-255)
	 * @param green Green color intensity value (0-255)
	 */
	public void setGreen(int green) {
		this.green = green;
	}

	/**
	 * Get blue color intensity value (0-255)
	 * @return Blue color intensity value (0-255)
	 */
	public int getBlue() {
		return blue;
	}

	/**
	 * Set blue color intensity value (0-255)
	 * @param blue Blue color intensity value (0-255)
	 */
	public void setBlue(int blue) {
		this.blue = blue;
	}

	/**
	 * Get the type of numerical value being linked to color
	 * @return a String storing the type of numerical value being linked to color
	 */
	public String getValueLabel() {
		return valueLabel;
	}

	/**
	 * Set the type of numerical value being linked to color
	 * @param valueLabel a String storing the type of numerical value being linked to color
	 */
	public void setValueLabel(String valueLabel) {
		this.valueLabel = valueLabel;
	}

	/**
	 * Get the numerical value being linked to color
	 * @return the numerical value being linked to color
	 */
	public double getValue() {
		return value;
	}

	/**
	 * Set the numerical value being linked to color
	 * @param value the numerical value being linked to color
	 */
	public void setValue(double value) {
		this.value = value;
	}

}