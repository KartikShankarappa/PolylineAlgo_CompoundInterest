package com.kartik;

import java.math.BigDecimal;

/**
 * @author KARTIK
 * Calculates interest annually and continuously.
 */
public class CompoundInterestCalculator {

    /**
     * @param principal principal amount
     * @param rate nominal annual interest rate (not reflecting the compounding) (not in percentage; i.e., 3.1% would be passed in as 0.031)
     * @param periods number of times the interest is compounded per year
     * @param years the number of years the {@code principal} is borrowed
     * @return the amount of interest calculated (excludes the {@code principal})
     * @see {@literal http://en.wikipedia.org/wiki/Compound_interest#Compound_Interest}
     */
    public BigDecimal compoundAnnually(double principal, double rate, int periods, int years) {
    	BigDecimal principalBD = BigDecimal.valueOf(principal);
    	BigDecimal rateBD = BigDecimal.valueOf(rate);
    	BigDecimal periodsBD = BigDecimal.valueOf(periods);
    	BigDecimal one = BigDecimal.valueOf(1);
    	BigDecimal temp1;
    	BigDecimal temp2;
    	BigDecimal compIntAnnual;
    	temp1 =(((one.add((rateBD.divide(periodsBD))))).pow(periods * years));
    	temp2 = temp1.multiply(principalBD);
    	compIntAnnual = temp2.subtract(principalBD);
    	return compIntAnnual;
    }

    /**
     * @param principal principal amount
     * @param rate annual interest rate (not in percentage; i.e., 3.1% would be passed in as 0.031)
     * @param years the number of years to calculate
     * @return the amount of interest calculated after {@code years} (excludes the {@code principal})
     * @see {@literal http://en.wikipedia.org/wiki/Compound_interest#Continuous_compounding}
     */
    public BigDecimal continuousCompound(double principal, double rate, int years) {
    	BigDecimal temp1;
    	BigDecimal temp2;
    	BigDecimal compIntContinuous;
    	BigDecimal principalBD = BigDecimal.valueOf(principal);
    	temp1 = BigDecimal.valueOf(Math.exp(rate * years));
    	temp2 = principalBD.multiply(temp1);
    	compIntContinuous = temp2.subtract(principalBD);
    	return compIntContinuous;
    }
}
