package org.sid.bankaccountservice.web;

import org.sid.bankaccountservice.dto.BankAccountRequestDTO;
import org.sid.bankaccountservice.dto.BankAccountResponseDTO;
import org.sid.bankaccountservice.entities.BankAccount;
import org.sid.bankaccountservice.mappers.AccountMapper;
import org.sid.bankaccountservice.repositories.BankAccountRepository;
import org.sid.bankaccountservice.service.AccountService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

// Rest API (web service REST full)
@RestController
@RequestMapping("/api")
public class AccountRestController {
    private BankAccountRepository bankAccountRepository;
    private AccountService accountService;
    private AccountMapper accountMapper;

    public AccountRestController(BankAccountRepository bankAccountRepository, AccountService accountService , AccountMapper accountMapper) {
        this.bankAccountRepository = bankAccountRepository;
        this.accountService = accountService;
        this.accountMapper = accountMapper;
    }

    @GetMapping("/bankAccounts")
    public List<BankAccount> bankAccounts(){
        return bankAccountRepository.findAll();
    }
    @GetMapping("/bankAccounts/{id}")
    public BankAccount bankAccount(@PathVariable String id){
        return bankAccountRepository.findById(id).orElseThrow(()->new RuntimeException("Account not found" + id));
    }

    @PostMapping("/bankAccounts")
    public BankAccountResponseDTO save(@RequestBody BankAccountRequestDTO bankAccountRequestDTO){

        return accountService.addAccount(bankAccountRequestDTO);
    }

    @PutMapping("/bankAccounts/{id}")
    public BankAccount update(@PathVariable String id, @RequestBody BankAccount bankAccount){
        BankAccount bankAccount1 = bankAccountRepository.findById(id).orElseThrow(()->new RuntimeException("Account not found" + id));
        if (bankAccount.getBalance() != null) bankAccount1.setBalance(bankAccount.getBalance());
        if(bankAccount.getType() != null) bankAccount1.setType(bankAccount.getType());
        if(bankAccount.getCreatedAt() != null) bankAccount1.setCreatedAt(bankAccount.getCreatedAt());
        if(bankAccount.getCurrency() != null) bankAccount1.setCurrency(bankAccount.getCurrency());

        return bankAccountRepository.save(bankAccount1);
    }
    @DeleteMapping("/bankAccounts/{id}")
    public void delete(@PathVariable String id){
        bankAccountRepository.deleteById(id);
    }


}
