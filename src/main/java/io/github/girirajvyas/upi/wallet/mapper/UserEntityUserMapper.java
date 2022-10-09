package io.github.girirajvyas.upi.wallet.mapper;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;
import io.github.girirajvyas.upi.wallet.entity.UserEntity;
import io.github.girirajvyas.upi.wallet.model.User;

@Component
@Mapper(componentModel = "spring")
public interface UserEntityUserMapper {

  UserEntity userToUserEntity(User user);

  User userEntityToUser(UserEntity userEntity);

}
