package io.github.girirajvyas.upi.wallet.mapper;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;
import io.github.girirajvyas.upi.wallet.entity.OtpEntity;
import io.github.girirajvyas.upi.wallet.model.Otp;

@Component
@Mapper(componentModel = "spring")
public interface OtpEntityOtpMapper {

  OtpEntity otpToOtpEntity(Otp otp);

  Otp otpEntityToOtp(OtpEntity otpEntity);

}
