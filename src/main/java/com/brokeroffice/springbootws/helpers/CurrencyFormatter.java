package com.brokeroffice.springbootws.helpers;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;
import java.math.BigDecimal;
import java.math.RoundingMode;
public class CurrencyFormatter {
    DecimalFormat formatter = new DecimalFormat("#,###");
    public  static String  formatter(String value){
      //return formatter.format(value);
        // Create a NumberFormat instance with the desired locale
        NumberFormat numberFormat = NumberFormat.getNumberInstance(Locale.US);
        DecimalFormat decimalFormat = (DecimalFormat) numberFormat;
        decimalFormat.applyPattern("#,##0.00");
        double number = Double.parseDouble(value);

        // Format the number with thousand separators
        String formattedString = decimalFormat.format(number);
        return formattedString;
    }

    public  static double  formatter2(double value){
        //return formatter.format(value);
        // Create a NumberFormat instance with the desired locale
        NumberFormat numberFormat = NumberFormat.getNumberInstance(Locale.US);
        DecimalFormat decimalFormat = (DecimalFormat) numberFormat;
        decimalFormat.applyPattern("#,##0.00");
        double number = Double.parseDouble(String.valueOf(value));

        // Format the number with thousand separators
        double formattedString = Double.parseDouble(decimalFormat.format(number));
        return formattedString;
    }

    public static double roundToDecimalPlaces(double a) {
        double roundOff = Math.round(a * 100.0) / 100.0;
        return roundOff; // Return the rounded value as a double
    }
}
