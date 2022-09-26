package io.github.girirajvyas.utils;

import java.util.HashMap;
import java.util.Map;
import org.apache.commons.lang3.ArrayUtils;

public class UpiUtils {

  private UpiUtils() {
    
  }
  
  public static String createMobileUpiString(String pa, String pn, String amount, String tn) {
    return "upi://pay?pa=" + pa + "%26pn=" + pn + "%26am=" + amount + "%26tn=" + tn;
  }
  
  public static String createMobileUpiStringApi(String pa, String pn, String amount, String tn) {
    return "upi://pay?pa=" + pa + "&pn=" + pn + "&am=" + amount + "&tn=" + tn;
  }

  public static Map<String, String> getValuesFromUpiUrl(String upiUrl) {
    Map<String, String> map = new HashMap<>();
    String[] parts = upiUrl.substring(10, upiUrl.length()).split("[&=]");
    if(ArrayUtils.isNotEmpty(parts)) {
      for (int i = 0; i < parts.length; i += 2) {
        map.put(parts[i], parts[i + 1]);
      }
    }
    
    return map;
  }
}
