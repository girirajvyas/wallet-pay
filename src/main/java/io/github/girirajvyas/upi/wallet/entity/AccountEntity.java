package io.github.girirajvyas.upi.wallet.entity;

import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "ACCOUNT")
public class AccountEntity {

  @Id
  @Column(name="ACCOUNT_ID")
  private Long accountId;
  
  @Column(name = "AMOUNT")
  private BigDecimal amount;
  
  @Column(name="MOBILE_NUMBER")
  private Long mobileNumber;
  
}
