package io.github.girirajvyas.upi.wallet.service;

import io.github.girirajvyas.upi.wallet.model.Otp;

public interface OtpService {

  Otp generateOtp(Long mobileNumber);

  Otp getOtp(Long mobileNumber);

  boolean verifyOtp(Long mobileNumber, Integer otp);

}
