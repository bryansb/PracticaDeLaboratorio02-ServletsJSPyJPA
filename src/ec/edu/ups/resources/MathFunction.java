package ec.edu.ups.resources;

import java.math.BigInteger;
import java.math.RoundingMode;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

import ec.edu.ups.entities.BillDetail;
import ec.edu.ups.entities.BillHead;

public class MathFunction {

	public static double getTrunkDecimal(double n) {
		NumberFormat nf = NumberFormat.getNumberInstance(Locale.US);
		DecimalFormat df = (DecimalFormat)nf;		
		df.setRoundingMode(RoundingMode.DOWN);
		return Double.parseDouble(df.format(n));
	}
	
	public static void setBillHeadTotal(BillHead bh) {
		double heaSubtotal = 0.0;
		double heaVat = 0.0;
		double heaTotal = 0.0;
		for(BillDetail bd : bh.getHeaBillDetails()) {
			if(!bd.isDetDeleted()) {
				heaSubtotal += bd.getDetTotal();
			}
		}
		bh.setHeaSubtotal(heaSubtotal);
		heaVat = heaSubtotal * Constants.IVA;
		bh.setHeaVat(heaVat);
		heaTotal = heaSubtotal + heaVat;
		bh.setHeaTotal(heaTotal);
	}
	
	public static void setBillDetailTotal(BillDetail bd) {
		double detTotal = bd.getDetAmount() * bd.getDetUnitPrice();
		bd.setDetTotal(detTotal);
	}
	
	public static String getMd5(String input) { 
        try { 
  
            // Static getInstance method is called with hashing MD5 
            MessageDigest md = MessageDigest.getInstance("MD5"); 
  
            // digest() method is called to calculate message digest 
            //  of an input digest() return array of byte 
            byte[] messageDigest = md.digest(input.getBytes()); 
  
            // Convert byte array into signum representation 
            BigInteger no = new BigInteger(1, messageDigest); 
  
            // Convert message digest into hex value 
            String hashtext = no.toString(16); 
            while (hashtext.length() < 32) { 
                hashtext = "0" + hashtext; 
            } 
            return hashtext; 
        }  catch (NoSuchAlgorithmException e) { 
            throw new RuntimeException(e); 
        } 
    } 
	
}