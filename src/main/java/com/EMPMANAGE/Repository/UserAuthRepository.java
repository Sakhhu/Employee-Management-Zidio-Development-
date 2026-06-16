package com.EMPMANAGE.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.EMPMANAGE.Entity.UserAuthentication;

@Repository
public interface UserAuthRepository extends JpaRepository<UserAuthentication, Long> {
		Optional<UserAuthentication> findByUserOfficialEmail(Object object);
	

}
