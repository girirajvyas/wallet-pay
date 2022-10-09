package io.github.girirajvyas.upi.wallet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import io.github.girirajvyas.upi.wallet.entity.UserEntity;

public interface UserRespository extends JpaRepository<UserEntity, Long>{

}
