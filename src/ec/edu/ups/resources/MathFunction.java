package ec.edu.ups.resources;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

import ec.edu.ups.entities.BillDetail;
import ec.edu.ups.entities.BillHead;

public class MathFunction {
	
	public static double getTrunkDecimal(double n) {
		n = Math.round(n * 100.0)/100.0;
		return n;
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
	
	public static Map<String, Integer> getNavPages(int n, int currentPage, int div){
		Map<String, Integer> map = new HashMap<String, Integer>();
		
		int maxPages;
		var min = currentPage * div;
        var max = min + (div - 1);
        
        maxPages = (int)((n - 1) / div);
        if(max >= n){
            max = n - 1;
        }
        min = min > max ? max : min;
        min = min < 0 ? 0 : min;
        
        int minP = currentPage - 3;
        if(minP < 0) {
        	minP = 0;
        }
        int maxP = currentPage + 3;
        if(maxP >= maxPages) {
        	maxP = maxPages;
        }
        
        map.put("min", min);
        map.put("max", max);
        map.put("minP", minP);
        map.put("maxP", maxP);
        map.put("maxPages", maxPages);
        
		return map;
	}
	
}
