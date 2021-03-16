package com.example.colorhead;

import android.graphics.Color;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;


/**
 * Models the Color app
 * @author Maxi Attiogbe
 *
 */

public class ColorApp {


	public static void main(String[] args) {
		
		int r = 0;
		int g = 0;
		int b = 0;
		
		int rSum = 0;
		int gSum = 0;
		int bSum = 0;
		
		int rCount = 0;
		int gCount = 0;
		int bCount = 0;
		/*
		Picture image = new Picture();
		image.pick();
	
		int height = image.getHeight();
		int width = image.getWidth();
		
		for (int j = 0; j < height; j ++) {
			for (int i = 0; i < width; i++) {
				Color original = image.getColorAt(i, j);
				r = 255 - original.getRed();
				g = 255 - original.getGreen();
				b = 255 - original.getBlue();
				Color negative = new Color(r, g, b);
				image.setColorAt(i, j, negative);
				
				rSum += original.getRed();
				gSum += original.getGreen();
				bSum += original.getBlue();
				
				rCount++;
				gCount++;
				bCount++;
			}
		}				
	
		*/
		Sample newSample = new Sample("pH", (int) rSum/rCount, (int) gSum/gCount, (int) bSum/bCount);
		System.out.println(newSample.print());
		System.out.println();
		
		
		System.out.println("*    *    *");
		System.out.println(" *   *   *        Color");
		System.out.println("  *  *  *   App Demonstration");
		System.out.println("   * * *");
		System.out.println();
		System.out.println("Welcome!");
		
		System.out.println();
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~ EQUATIONS ~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println();
		
		System.out.println("The system of polynomial equations that will be used"
				+ " to estimate pH based on RGB (red, green, and blue) values (0-255) is as follows ");
		System.out.println();
		
		Model pHModel = new Model();
		
		ArrayList<Double> coeff1 = new ArrayList<Double> ();
		ArrayList<Double> coeff2 = new ArrayList<Double> ();
		ArrayList<Double> coeff3 = new ArrayList<Double> ();
	
		
		// 01_01_2021_Copy_Experimental Data with Solutions.xlsx Data
		
		ArrayList<Sample> data = new ArrayList<Sample>();
		
		// Creating Samples from spreadsheet data
		Sample s1_1_1= new Sample("pH", 2.47, 208, 120, 66);
		Sample s1_1_2= new Sample("pH", 2.47, 209, 122, 68);
		Sample s1_1_3= new Sample("pH", 2.47, 211, 123, 68);
		Sample s1_2_1= new Sample("pH", 2.47, 219, 133, 74);
		Sample s1_2_2= new Sample("pH", 2.47, 221, 132, 73);
		Sample s1_2_3= new Sample("pH", 2.47, 219, 135, 79);
		Sample s1_3_1= new Sample("pH", 2.47, 211, 122, 70);
		Sample s1_3_2= new Sample("pH", 2.47, 213, 127, 73);
		Sample s1_3_3= new Sample("pH", 2.47, 209, 127, 74);
		Sample s2_1_1= new Sample("pH", 2.78, 205, 121, 65);		
		Sample s2_1_2= new Sample("pH", 2.78, 206, 128, 73);
		Sample s2_1_3= new Sample("pH", 2.78, 208, 127, 70);
		Sample s2_2_1= new Sample("pH", 2.74, 204, 116, 62);
		Sample s2_2_2= new Sample("pH", 2.74, 204, 117, 64);
		Sample s2_2_3= new Sample("pH", 2.74, 200, 118, 68);
		Sample s2_3_1= new Sample("pH", 2.74, 203, 118, 63);
		Sample s2_3_2= new Sample("pH", 2.74, 202, 121, 67);
		Sample s2_3_3= new Sample("pH", 2.74, 203, 124, 68);
		Sample s3_1_1= new Sample("pH", 3.33, 203, 139, 70);	
		Sample s3_1_2= new Sample("pH", 3.33, 203, 137, 68);
		Sample s3_1_3= new Sample("pH", 3.33, 205, 140, 69);
		Sample s3_2_1= new Sample("pH", 3.36, 203, 123, 58);
		Sample s3_2_2= new Sample("pH", 3.36, 200, 124, 60);
		Sample s3_2_3= new Sample("pH", 3.36, 201, 129, 67);
		Sample s3_3_1= new Sample("pH", 3.33, 199, 123, 55);
		Sample s3_3_2= new Sample("pH", 3.33, 195, 123, 58);
		Sample s3_3_3= new Sample("pH", 3.33, 199, 127, 60);
		Sample s4_1_1= new Sample("pH", 4.32, 198, 156, 59);
		Sample s4_1_2= new Sample("pH", 4.32, 202, 162, 61);
		Sample s4_1_3= new Sample("pH", 4.32, 201, 160, 63);
		Sample s4_2_1= new Sample("pH", 4.35, 197, 153, 55);
		Sample s4_2_2= new Sample("pH", 4.35, 195, 153, 58);
		Sample s4_2_3= new Sample("pH", 4.35, 193, 153, 61);
		Sample s4_3_1= new Sample("pH", 4.32, 200, 173, 71);
		Sample s4_3_2= new Sample("pH", 4.32, 198, 167, 70);
		Sample s4_3_3= new Sample("pH", 4.32, 199, 164, 68);
		Sample s5_1_1= new Sample("pH", 6.51, 181, 156, 56);
		Sample s5_1_2= new Sample("pH", 6.51, 182, 157, 54);
		Sample s5_1_3= new Sample("pH", 6.51, 182, 157, 55);
		Sample s5_2_1= new Sample("pH", 6.54, 191, 156, 51);
		Sample s5_2_2= new Sample("pH", 6.54, 191, 158, 52);
		Sample s5_2_3= new Sample("pH", 6.54, 189, 161, 58);
		Sample s5_3_1= new Sample("pH", 6.57, 190, 158, 53);
		Sample s5_3_2= new Sample("pH", 6.57, 186, 153, 51);
		Sample s5_3_3= new Sample("pH", 6.57, 192, 158, 54);
		Sample s6_1_1= new Sample("pH", 7.24, 172, 155, 50);
		Sample s6_1_2= new Sample("pH", 7.24, 175, 151, 38);
		Sample s6_1_3= new Sample("pH", 7.24, 185, 156, 45);
		Sample s6_2_1= new Sample("pH", 7.31, 194, 161, 31);
		Sample s6_2_2= new Sample("pH", 7.31, 189, 159, 31);
		Sample s6_2_3= new Sample("pH", 7.31, 188, 157, 30);
		Sample s6_3_1= new Sample("pH", 7.28, 187, 162, 37);
		Sample s6_3_2= new Sample("pH", 7.28, 186, 162, 35);
		Sample s6_3_3= new Sample("pH", 7.28, 186, 162, 37);
		Sample s7_1_1= new Sample("pH", 7.35, 190, 162, 48);
		Sample s7_1_2= new Sample("pH", 7.35, 184, 157, 49);
		Sample s7_1_3= new Sample("pH", 7.35, 181, 160, 51);
		Sample s7_2_1= new Sample("pH", 7.45, 198, 170, 59);
		Sample s7_2_2= new Sample("pH", 7.45, 200, 167, 56);
		Sample s7_2_3= new Sample("pH", 7.45, 200, 169, 60);
		Sample s7_3_1= new Sample("pH", 7.24, 170, 151, 45);
		Sample s7_3_2= new Sample("pH", 7.24, 170, 151, 49);
		Sample s7_3_3= new Sample("pH", 7.24, 173, 157, 53);
		Sample s8_1_1= new Sample("pH", 7.38, 184, 174, 66);
		Sample s8_1_2= new Sample("pH", 7.38, 185, 171, 69);
		Sample s8_1_3= new Sample("pH", 7.38, 182, 172, 73);
		Sample s8_2_1= new Sample("pH", 7.38, 187, 162, 69);
		Sample s8_2_2= new Sample("pH", 7.38, 185, 165, 67);
		Sample s8_2_3= new Sample("pH", 7.38, 186, 166, 67);
		Sample s8_3_1= new Sample("pH", 7.35, 159, 154, 53);
		Sample s8_3_2= new Sample("pH", 7.35, 163, 155, 55);
		Sample s8_3_3= new Sample("pH", 7.35, 169, 157, 55);
		Sample s9_1_1= new Sample("pH", 8.04, 81, 120, 63);
		Sample s9_1_2= new Sample("pH", 8.04, 78, 114, 68);
		Sample s9_1_3= new Sample("pH", 8.04, 78, 115, 70);
		Sample s9_2_1= new Sample("pH", 7.97, 89, 126, 71);
		Sample s9_2_2= new Sample("pH", 7.97, 81, 122, 76);
		Sample s9_2_3= new Sample("pH", 7.97, 78, 119, 78);
		Sample s9_3_1= new Sample("pH", 8, 90, 129, 71);
		Sample s9_3_2= new Sample("pH", 8, 80, 123, 73);
		Sample s9_3_3= new Sample("pH", 8, 77, 120, 74);
		Sample s10_1_1= new Sample("pH", 8.52, 66, 117, 74);
		Sample s10_1_2= new Sample("pH", 8.52, 67, 114, 77);
		Sample s10_1_3= new Sample("pH", 8.52, 68, 115, 77);
		Sample s10_2_1= new Sample("pH", 8.49, 75, 123, 71);
		Sample s10_2_2= new Sample("pH", 8.49, 77, 124, 76);
		Sample s10_2_3= new Sample("pH", 8.49, 73, 121, 75);
		Sample s10_3_1= new Sample("pH", 8.49, 69, 119, 79);
		Sample s10_3_2= new Sample("pH", 8.49, 55, 121, 81);
		Sample s10_3_3= new Sample("pH", 8.49, 71, 122, 83);
		Sample s11_1_1= new Sample("pH", 8.77, 58, 106, 74);
		Sample s11_1_2= new Sample("pH", 8.77, 60, 108, 76);
		Sample s11_1_3= new Sample("pH", 8.77, 48, 102, 63);
		Sample s11_2_1= new Sample("pH", 8.73, 53, 103, 69);
		Sample s11_2_2= new Sample("pH", 8.73, 56, 105, 73);
		Sample s11_2_3= new Sample("pH", 8.73, 55, 104, 73);
		Sample s11_3_1= new Sample("pH", 8.73, 56, 104, 70);
		Sample s11_3_2= new Sample("pH", 8.73, 56, 105, 71);
		Sample s11_3_3= new Sample("pH", 8.73, 58, 108, 73);
		
		
		//Adding Samples to the data ArrayList
		
		//Solution 1
		 	// Trial 1
		data.add(s1_1_1);
		data.add(s1_1_2);
		data.add(s1_1_3);
		
		 	// Trial 2
		data.add(s1_2_1);
		data.add(s1_2_2);
		data.add(s1_2_3);
		
		 	// Trial 3
		data.add(s1_3_1);
		data.add(s1_3_2);
		data.add(s1_3_3);
		
		
		//Solution 2
		 	// Trial 1
		data.add(s2_1_1);
		data.add(s2_1_2);
		data.add(s2_1_3);
		
		 	// Trial 2
		data.add(s2_2_1);
		data.add(s2_2_2);
		data.add(s2_2_3);
		
		 	// Trial 3
		data.add(s2_3_1);
		data.add(s2_3_2);
		data.add(s2_3_3);
		
		
		//Solution 3
		 	// Trial 1
		data.add(s3_1_1);
		data.add(s3_1_2);
		data.add(s3_1_3);
		
		 	// Trial 2
		data.add(s3_2_1);
		data.add(s3_2_2);
		data.add(s3_2_3);
		
		 	// Trial 3
		data.add(s3_3_1);
		data.add(s3_3_2);
		data.add(s3_3_3);
		
		
		//Solution 4
		 	// Trial 1
		data.add(s4_1_1);
		data.add(s4_1_2);
		data.add(s4_1_3);
		
		 	// Trial 2
		data.add(s4_2_1);
		data.add(s4_2_2);
		data.add(s4_2_3);
		
		 	// Trial 3
		data.add(s4_3_1);
		data.add(s4_3_2);
		data.add(s4_3_3);
		
		
		//Solution 5
		 	// Trial 1
		data.add(s5_1_1);
		data.add(s5_1_2);
		data.add(s5_1_3);
		
		 	// Trial 2
		data.add(s5_2_1);
		data.add(s5_2_2);
		data.add(s5_2_3);
		
		 	// Trial 3
		data.add(s5_3_1);
		data.add(s5_3_2);
		data.add(s5_3_3);
		
		
		//Solution 6
		 	// Trial 1
		data.add(s6_1_1);
		data.add(s6_1_2);
		data.add(s6_1_3);
		
		 	// Trial 2
		data.add(s6_2_1);
		data.add(s6_2_2);
		data.add(s6_2_3);
		
		 	// Trial 3
		data.add(s6_3_1);
		data.add(s6_3_2);
		data.add(s6_3_3);
		
		
		//Solution 7
		 	// Trial 1
		data.add(s7_1_1);
		data.add(s7_1_2);
		data.add(s7_1_3);
		
		 	// Trial 2
		data.add(s7_2_1);
		data.add(s7_2_2);
		data.add(s7_2_3);
		
		 	// Trial 3
		data.add(s7_3_1);
		data.add(s7_3_2);
		data.add(s7_3_3);
		
		//Solution 8
		 	// Trial 1
		data.add(s8_1_1);
		data.add(s8_1_2);
		data.add(s8_1_3);
		
		 	// Trial 2
		data.add(s8_2_1);
		data.add(s8_2_2);
		data.add(s8_2_3);
		
		 	// Trial 3
		data.add(s8_3_1);
		data.add(s8_3_2);
		data.add(s8_3_3);
		
		//Solution 9
		 	// Trial 1
		data.add(s9_1_1);
		data.add(s9_1_2);
		data.add(s9_1_3);
		
		 	// Trial 2
		data.add(s9_2_1);
		data.add(s9_2_2);
		data.add(s9_2_3);
		
		 	// Trial 3
		data.add(s9_3_1);
		data.add(s9_3_2);
		data.add(s9_3_3);
		
		
		//Solution 10
		 	// Trial 1
		data.add(s10_1_1);
		data.add(s10_1_2);
		data.add(s10_1_3);
		
		 	// Trial 2
		data.add(s10_2_1);
		data.add(s10_2_2);
		data.add(s10_2_3);
		
		 	// Trial 3
		data.add(s10_3_1);
		data.add(s10_3_2);
		data.add(s10_3_3);
		
		//Solution 11
		 	// Trial 1
		data.add(s11_1_1);
		data.add(s11_1_2);
		data.add(s11_1_3);
		
		 	// Trial 2
		data.add(s11_2_1);
		data.add(s11_2_2);
		data.add(s11_2_3);
		
		 	// Trial 3
		data.add(s11_3_1);
		data.add(s11_3_2);
		data.add(s11_3_3);
		
		
		//Putting the data ArrayList into the model
		pHModel.setData(data);
		
		//Fit cubic polynomials (one each for red, green, and blue color intensities) to the data
		pHModel.fitData(3,0,14);
		System.out.println();
		System.out.println("where x = pH and R(x), G(x), and B(x) are the corresponding red, green, and blue "
				+ "color intensities respectively.");
		
		System.out.println();
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~ TRAINING DATA/TESTING DATA 1 ~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println();
		
		System.out.println("The above equations model the following data.");
		
		for(Sample s: pHModel.getData()) {
			System.out.println();
			System.out.println(s.print());
		}
		
		System.out.println();
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~ ESTIMATION AND STATISTICS ~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println();
		
		ArrayList<Double> percentError = new ArrayList<Double>();
		double sum = 0.0;
		
		for(int count = 0; count < pHModel.getData().size(); count++) {
			System.out.println("Please input three numbers (each 0-255 and separated by spaces) to represent pH paper "
					+ "red, green, and blue color intensities.");
			
			r= pHModel.getData().get(count).getRed();
			g= pHModel.getData().get(count).getGreen();
			b= pHModel.getData().get(count).getBlue();
			
			System.out.println();
			System.out.println("Your input is " + "Red: " + r + ", Green: " + g +  ", and Blue: " + b);
			System.out.println();
			
			ArrayList<Double> redCoeff = new ArrayList<Double>();
			ArrayList<Double> greenCoeff = new ArrayList<Double>();
			ArrayList<Double> blueCoeff = new ArrayList<Double>();
			
			redCoeff.add((double)r);
			greenCoeff.add((double)g);
			blueCoeff.add((double)b);
			
			Polynomial redConstPoly = new Polynomial(redCoeff);
			Polynomial greenConstPoly = new Polynomial(greenCoeff);
			Polynomial blueConstPoly = new Polynomial(blueCoeff);
			
			Polynomial errorPoly = redConstPoly.subtract(pHModel.getRed()).square().
					add(greenConstPoly.subtract(pHModel.getGreen()).square()).
					add(blueConstPoly.subtract(pHModel.getBlue()).square());
			
		
			System.out.println("Actual (measured) pH: " + pHModel.getData().get(count).getValue());
			System.out.println();
			System.out.println("The following pH estimate was found. ");
			
			Polynomial errorPolyCopy = redConstPoly.subtract(pHModel.getRed()).square().
					add(greenConstPoly.subtract(pHModel.getGreen()).square()).
					add(blueConstPoly.subtract(pHModel.getBlue()).square());
			
		
				
		
			double pH = pHModel.estimate(r,g,b,0,14);
			System.out.println("pH: " + pH);
			System.out.println();
			System.out.println("Percent error: " +
			(Math.abs(pH - pHModel.getData().get(count).getValue())/pHModel.getData().get(count).getValue() * 100) + "%");
			percentError.add((Math.abs(pH - pHModel.getData().get(count).getValue())/pHModel.getData().get(count).getValue() * 100));
			System.out.println();
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			System.out.println();	
		}
		
		Collections.sort(percentError);
		for (double d: percentError)
			sum += d;	
		
		System.out.println("Total average percent error: " + Math.round(sum/percentError.size()*100.0)/100.0 + "%");
		System.out.println("Total median percent error: " + Math.round(percentError.get((int) percentError.size()/2) * 100.0)/100.0 + "%");
		
		System.out.println();
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~ TESTING DATA 2 ~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println();	

		//Scanner scanner = new Scanner(System.in);
		
		//r= scanner.nextInt();
		//g= scanner.nextInt();
		//b= scanner.nextInt();
		
		//scanner.close();
		
	}
}