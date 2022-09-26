package io.github.girirajvyas.mapper;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;
import io.github.girirajvyas.entity.UserEntity;
import io.github.girirajvyas.model.User;

@Component
@Mapper(componentModel = "spring")
public interface UserEntityUserMapper {

  UserEntity userToUserEntity(User user);

  User userEntityToUser(UserEntity userEntity);

}
