package io.github.girirajvyas.upi.wallet.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import io.github.girirajvyas.upi.wallet.entity.UserEntity;
import io.github.girirajvyas.upi.wallet.mapper.UserEntityUserMapper;
import io.github.girirajvyas.upi.wallet.model.User;
import io.github.girirajvyas.upi.wallet.repository.UserRespository;

@Service
public class UserServiceImpl implements UserService {

  @Autowired
  private UserRespository userRespository;

  @Autowired
  private UserEntityUserMapper userEntityUserMapper;

  @Override
  public User saveUser(User user) {
    UserEntity entity = userEntityUserMapper.userToUserEntity(user);
    UserEntity savedEntity = userRespository.save(entity);
    
    return userEntityUserMapper.userEntityToUser(savedEntity);
  }

}
