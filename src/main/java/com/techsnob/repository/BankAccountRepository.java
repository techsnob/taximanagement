package com.techsnob.repository;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.techsnob.entitiy.BankAccount;

@Repository
@Transactional
public interface BankAccountRepository extends CrudRepository<BankAccount, String> {

}
