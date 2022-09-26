package io.github.girirajvyas.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import io.github.girirajvyas.entity.PinEntity;
import io.github.girirajvyas.mapper.PinEntityPinMapper;
import io.github.girirajvyas.model.Pin;
import io.github.girirajvyas.repository.PinRepository;

@Service
public class PinServiceImpl implements PinService {

  @Autowired
  private PinRepository pinRepository;
  
  @Autowired
  private PinEntityPinMapper pinEntityPinMapper;
  
  @Override
  public Pin setPin(Pin pin) {
    PinEntity pinEntity =  pinEntityPinMapper.pinToPinEntity(pin);
    PinEntity updatedPinEntity = pinRepository.save(pinEntity);
    return pinEntityPinMapper.pinEntityToPin(updatedPinEntity);
  }

}
