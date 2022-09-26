package io.github.girirajvyas.mapper;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;
import io.github.girirajvyas.entity.OtpEntity;
import io.github.girirajvyas.model.Otp;

@Component
@Mapper(componentModel = "spring")
public interface OtpEntityOtpMapper {

  OtpEntity otpToOtpEntity(Otp otp);

  Otp otpEntityToOtp(OtpEntity otpEntity);

}
