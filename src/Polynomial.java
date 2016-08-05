import java.util.*;
import java.util.Map.Entry;

class Polynomial {
	HashMap<Integer, Double> polynomial;

	public Polynomial() {
		polynomial = new HashMap<>();
	}

	public Polynomial(int exponents[], double[] coefficients) {
		this.polynomial = new HashMap<>();
		for (int i = 0; i < exponents.length; i++) {
			if (polynomial.containsKey(exponents[i]))
				polynomial.put(exponents[i], polynomial.get(exponents[i]) + coefficients[i]);
			else
				polynomial.put(exponents[i], coefficients[i]);
		}
	}

	public Polynomial clone() {
		Polynomial clone = new Polynomial((HashMap<Integer, Double>) this.polynomial.clone());
		return clone;
	}

	private void putCoefficient(int exponent, double coefficient) {
		this.polynomial.put(exponent, coefficient);
	}

	private double getCoefficient(int exponent) {
		if (this.polynomial.containsKey(exponent))
			return this.polynomial.get(exponent);
		else
			return 0;
	}

	public Polynomial(HashMap<Integer, Double> inputMap) {
		polynomial = inputMap;
	}

	public Polynomial add(Polynomial p1) {
		Polynomial temp = this.clone();
		for (Integer exponent : p1.polynomial.keySet()) {
			temp.putCoefficient(exponent, temp.getCoefficient(exponent) + p1.getCoefficient(exponent));
		}
		return temp;
	}

	public Polynomial subtract(Polynomial p1) {
		Polynomial temp = this.clone();
		for (Integer exponent : p1.polynomial.keySet()) {
			temp.putCoefficient(exponent, temp.getCoefficient(exponent) - p1.getCoefficient(exponent));
		}
		return temp;
	}

	public Polynomial multiply(Polynomial p2) {
		Polynomial product = new Polynomial();
		for (Integer exponent1 : this.polynomial.keySet()) {
			for (Integer exponent2 : p2.polynomial.keySet())
				product.putCoefficient(exponent1 + exponent2, product.getCoefficient(exponent1 + exponent2)
						+ (this.getCoefficient(exponent1) * p2.getCoefficient(exponent2)));
		}
		return product;
	}

	public String toOutputFormat(String preExponent, String postExponent){
		String out = "";
		for (Integer exponent : polynomial.keySet()){
			if (getCoefficient(exponent) > 0)
				out += "+";
			out += getCoefficient(exponent) + "X" + preExponent + exponent + postExponent +" ";
				
		}
		if(out.startsWith("+"))
			return out.substring(1);
		return out;
	}
	
	public String toString() {
		return toOutputFormat("^", "");
		/*
		String out = "";
		for (Integer exponent : polynomial.keySet())
			if ((int) getCoefficient(exponent) != 0)
				out += getCoefficient(exponent) + "X^" + exponent + "\t";
		return out;
		*/
	}

	public String toLatex() {
		String out = "\\(" + toString() + "\\)";
		return out;
	}

	public String toHTML() {
		return "<font size=\"20\">" + toOutputFormat("<sup>","</sup>") + "</font>";
	}

	public static void main(String[] args) {
		final int[] exponents = { 0, 3 };
		final double[] coefficients = { 1, 5 };
		final double[] coefficients2 = { 1, 5 };
		Polynomial p1 = new Polynomial(exponents, coefficients2);
		Polynomial p2 = new Polynomial(exponents, coefficients);
		System.out.println("Multiplication : "+p1.multiply(p2));
		System.out.println("Addition : "+p1.add(p2));
		System.out.println("Subtraction : "+p1.subtract(p2));
		
	}
}