package com.richards.eWallet.repository;

import com.richards.eWallet.models.Account;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface AccountsRepository extends MongoRepository<Account, String> {
    Account findByUserName(String userName);


    Account findByAccountNumber(String phoneNumber);


}
