package io.github.girirajvyas.upi.wallet.mapper;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;
import io.github.girirajvyas.upi.wallet.entity.PinEntity;
import io.github.girirajvyas.upi.wallet.model.Pin;

@Component
@Mapper(componentModel = "spring")
public interface PinEntityPinMapper {

  PinEntity pinToPinEntity(Pin pin);

  Pin pinEntityToPin(PinEntity pinEntity);

}
