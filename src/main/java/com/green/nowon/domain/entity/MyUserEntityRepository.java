package com.green.nowon.domain.entity;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MyUserEntityRepository extends JpaRepository<MyUserEntity, Long>{

	Optional<MyUserEntity> findByEmailAndIsSocial(String email, boolean isSocial);

}
