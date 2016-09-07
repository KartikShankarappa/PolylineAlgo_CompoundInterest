package com.kartik;
import java.math.BigDecimal;

/**
 * 
 * @author KARTIK
 *
 */
public class Program {

	public static void main(String[] args) {
		String str = null;
		str = isValidArguments(args);
		if (str == "annual") {
			CompoundInterestCalculator objAnnual = new CompoundInterestCalculator();
			double principal = Double.parseDouble(args[2]);
			double rate = Double.parseDouble(args[3]);
			int periods = Integer.parseInt(args[4]);
			int years = Integer.parseInt(args[5]);
			BigDecimal compIntAnn = objAnnual.compoundAnnually(principal, rate, periods, years);
			System.out.println(compIntAnn);
		} else if (str == "continuous") {
			CompoundInterestCalculator objContinuous = new CompoundInterestCalculator();
			double principal = Double.parseDouble(args[2]);
			double rate = Double.parseDouble(args[3]);
			int years = Integer.parseInt(args[4]);
			BigDecimal compIntCont = objContinuous.continuousCompound(principal, rate, years);
			System.out.println(compIntCont);
		} else if (str == "gps") {
			Gps[] gpsLatLong = new Gps[args.length - 1];
			for (int i = 0; i < args.length - 1; i++) {
				String[] point = args[i+1].split(",");
				if (point.length != 2) {
					System.out.println("Invalid Argument");
					System.exit(0);
				} else {
					double latitude = Double.parseDouble(point[0]);
					double longitude = Double.parseDouble(point[1]);
					gpsLatLong[i] = new Gps(latitude,longitude);
				}
			}
			String encodedPolyLine = "";
			PolylineEncoder objPolyLine = new PolylineEncoder();
			encodedPolyLine = objPolyLine.encodePolyline(gpsLatLong);
			System.out.println(encodedPolyLine);
		} else if (str == "invalid") {
			System.out.println("Invalid Argument");
			System.exit(0);
		}	
	}

	/**
	 * Checks if the given command line arguments are valid or in-valid
	 * 
	 * @param arguments set of arguments which were passed through command line
	 * @return returns the validity of the arguments
	 */
	public static String isValidArguments(String[] arguments) {
		if (arguments.length <= 1) {
			return "invalid";
		} else if (!arguments[0].equals("gps") & !arguments[0].equals("interest")) {
			return "invalid";
		}
		if (arguments[0].equals("gps")) {
			return "gps";
		} else if (arguments[0].equals("interest")) {
			if (arguments[1].equals("annual")) {
				if (arguments.length == 6) {
					return "annual";
				}
			} else if(arguments[1].equals("continuous")) {
				if (arguments.length == 5) {
					return "continuous";
				}
			}
		}	
		return "invalid";
	}
}
