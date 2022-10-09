package io.github.girirajvyas.upi.wallet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import io.github.girirajvyas.upi.wallet.entity.OtpEntity;

public interface OtpRepository extends JpaRepository<OtpEntity, Long> {

  @Query(
      value = "SELECT TOP 1 OTP_ID, MOBILE_NUMBER, OTP, TIMESTAMP from OTP WHERE MOBILE_NUMBER = :mobileNumber AND OTP=:otp order by OTP_ID Desc",
      nativeQuery = true)
  OtpEntity findByMobileNumberAndOtp(Long mobileNumber, Integer otp);

  @Query(
      value = "SELECT TOP 1 OTP_ID, MOBILE_NUMBER, OTP, TIMESTAMP from OTP WHERE MOBILE_NUMBER = :mobileNumber order by OTP_ID Desc",
      nativeQuery = true)
  OtpEntity findOtpByMobileNumber(Long mobileNumber);
}
