package io.github.girirajvyas.upi.wallet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import io.github.girirajvyas.upi.wallet.entity.AccountEntity;

public interface AccountRepository extends JpaRepository<AccountEntity, Long> {

}
