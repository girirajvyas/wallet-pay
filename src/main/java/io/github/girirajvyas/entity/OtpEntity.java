package io.github.girirajvyas.entity;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "OTP")
public class OtpEntity {
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name="OTP_ID")
  private Long otpId;
  
  @Column(name="MOBILE_NUMBER")
  private Long mobileNumber;
  
  @Column(name="OTP")
  private String otp;
  
  @Column(name="TIMESTAMP")
  private Timestamp timestamp;

}
