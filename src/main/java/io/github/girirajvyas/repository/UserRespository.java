package io.github.girirajvyas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import io.github.girirajvyas.entity.UserEntity;

public interface UserRespository extends JpaRepository<UserEntity, Long>{

}
