package io.github.girirajvyas.upi.wallet.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import io.github.girirajvyas.upi.wallet.exception.PayValidationException;

public class ValidatorUtils {

  private ValidatorUtils() {

  }

  public static void isValidMobileNumber(Long mobileNumber) {
    if (mobileNumber != null) {
      String mobileNumberString = String.valueOf(mobileNumber);
      Pattern p = Pattern.compile("^\\d{10}$");
      Matcher m = p.matcher(mobileNumberString);
      
      if(!m.matches()) {
        throw new PayValidationException("Invalid Mobile Number! It can be 10 digit number only");
      }
    } else {
      throw new PayValidationException("Mobile number cannot be null!");
    }
  }


}
