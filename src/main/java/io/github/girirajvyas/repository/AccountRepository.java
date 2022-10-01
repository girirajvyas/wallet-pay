package io.github.girirajvyas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import io.github.girirajvyas.entity.AccountEntity;

public interface AccountRepository extends JpaRepository<AccountEntity, Long> {

}
