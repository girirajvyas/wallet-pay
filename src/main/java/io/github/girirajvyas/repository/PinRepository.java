package io.github.girirajvyas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import io.github.girirajvyas.entity.PinEntity;

public interface PinRepository extends JpaRepository<PinEntity, Long> {

}
