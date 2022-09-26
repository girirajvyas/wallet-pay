package io.github.girirajvyas.entity;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import io.github.girirajvyas.utils.AttributeEncryptor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "PIN")
public class PinEntity {
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name="PIN_ID")
  private Long pinId;
  
  @Column(name="PIN")
  @Convert(converter = AttributeEncryptor.class)
  private String pin;
  
  @Column(name="MOBILE_NUMBER")
  private Long mobileNumber;
}
