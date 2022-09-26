package io.github.girirajvyas.service;

import io.github.girirajvyas.model.Otp;

public interface OtpService {

  Otp generateOtp(Long mobileNumber);

  Otp getOtp(Long mobileNumber);

  boolean verifyOtp(Long mobileNumber, Integer otp);

}
