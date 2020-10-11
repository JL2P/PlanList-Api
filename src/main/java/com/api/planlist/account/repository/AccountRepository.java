package com.api.planlist.account.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.api.planlist.account.domain.Account;

public interface AccountRepository extends JpaRepository<Account, String> {

	@Query("select a from Account a where a.email = :email and a.password = :password")
	Account signin(@Param("email") String email, @Param("password") String password);
	
	@Query("select e from Account e where e.email = :email")
	Account auth(@Param("email") String email);
}
