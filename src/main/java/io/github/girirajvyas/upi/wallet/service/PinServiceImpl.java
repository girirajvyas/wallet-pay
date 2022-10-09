package io.github.girirajvyas.upi.wallet.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import io.github.girirajvyas.upi.wallet.entity.PinEntity;
import io.github.girirajvyas.upi.wallet.mapper.PinEntityPinMapper;
import io.github.girirajvyas.upi.wallet.model.Pin;
import io.github.girirajvyas.upi.wallet.repository.PinRepository;

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
