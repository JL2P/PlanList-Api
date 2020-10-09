package com.api.planlist.account.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.planlist.account.domain.Account;

public interface AccountRepository extends JpaRepository<Account, String> {

}
