package io.github.girirajvyas.upi.wallet.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "USERS")
public class UserEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name="USER_ID")
  private Long userId;

  @Column(name="FIRST_NAME")
  private String firstName;
  
  @Column(name="LAST_NAME")
  private String lastName;
  
  @Column(name="MOBILE_NUMBER")
  private Long mobileNumber;
  
  @Column(name="EMAIL_ID")
  private String emailId;
  
}
