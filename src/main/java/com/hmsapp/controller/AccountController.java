package com.hmsapp.controller;

import com.hmsapp.entity.Account;
import com.hmsapp.repository.AccountRepository;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class AccountController {

    private AccountRepository accountRepository;

    public AccountController(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }
    @PostMapping("/account")
    public String getAccount(
            @RequestBody Account account
    ){
        accountRepository.save(account);
        return "This is an account resource";
    }

    @PutMapping("/a1/account")
    public String updateAccount(
            @RequestParam long id
    ){
        Account account = accountRepository.findById(1L).get();
        account.setName("New Name again");
        account.setVersion(1L);
        System.out.println(account.getVersion());
        accountRepository.save(account);
        return "Account updated successfully";
    }
}
