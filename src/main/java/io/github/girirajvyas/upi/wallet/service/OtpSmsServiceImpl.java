package io.github.girirajvyas.upi.wallet.service;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.sql.Timestamp;
import java.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import io.github.girirajvyas.upi.wallet.entity.OtpEntity;
import io.github.girirajvyas.upi.wallet.mapper.OtpEntityOtpMapper;
import io.github.girirajvyas.upi.wallet.model.Otp;
import io.github.girirajvyas.upi.wallet.repository.OtpRepository;

@Service
public class OtpSmsServiceImpl implements OtpService {

  @Autowired
  private OtpRepository otpRepository;

  @Autowired
  private OtpEntityOtpMapper otpEntityOtpMapper;
  
  @Override
  public Otp generateOtp(Long mobileNumber) {
    OtpEntity otpEntity = OtpEntity.builder().otp(getRandomNumber()).mobileNumber(mobileNumber)
        .timestamp(new Timestamp(0)).build();
    OtpEntity savedOtpEntity = otpRepository.save(otpEntity);
    
    return otpEntityOtpMapper.otpEntityToOtp(savedOtpEntity);
  }

  @Override
  public Otp getOtp(Long mobileNumber) {
    OtpEntity otpEntity = otpRepository.findOtpByMobileNumber(mobileNumber);
    return otpEntityOtpMapper.otpEntityToOtp(otpEntity);
  }
  
  @Override
  public boolean verifyOtp(Long mobileNumber, Integer otp) {
    OtpEntity otpEntity = otpRepository.findByMobileNumberAndOtp(mobileNumber, otp);
    return otpEntity!=null ? true : false;
  }

  private String getRandomNumber() {
    Random random = null;
    try {
      random = SecureRandom.getInstanceStrong();
    } catch (NoSuchAlgorithmException e) {
      e.printStackTrace();
    }
    
    if(random!=null) {
      return String.format("%04d", random.nextInt(10000));
    }
    
    return null;
  }

  

}
