package io.github.girirajvyas.upi.wallet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import io.github.girirajvyas.upi.wallet.entity.PinEntity;

public interface PinRepository extends JpaRepository<PinEntity, Long> {

}
