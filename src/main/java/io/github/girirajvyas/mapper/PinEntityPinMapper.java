package io.github.girirajvyas.mapper;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;
import io.github.girirajvyas.entity.PinEntity;
import io.github.girirajvyas.model.Pin;

@Component
@Mapper(componentModel = "spring")
public interface PinEntityPinMapper {

  PinEntity pinToPinEntity(Pin pin);

  Pin pinEntityToPin(PinEntity pinEntity);

}
