package com.example.bankingapp.Web;

import com.example.bankingapp.DTO.BankAccountRequestDTO;
import com.example.bankingapp.DTO.BankAccountResponseDTO;
import com.example.bankingapp.Entities.BankAccount;
import com.example.bankingapp.Mappers.AccountMapper;
import com.example.bankingapp.Repositories.BankAccountRepository;
import com.example.bankingapp.Services.AccountService;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api")
public class AccountRestController {
    private BankAccountRepository bankAccountRepository;
    private AccountService accountService;
    private AccountMapper accountMapper;

    public AccountRestController(BankAccountRepository bankAccountRepository, AccountService accountService, AccountMapper accountMapper) {
        this.bankAccountRepository = bankAccountRepository;
        this.accountService = accountService;
        this.accountMapper = accountMapper;
    }

//    @GetMapping("/bankAccounts")
//    public List<BankAccount> bankAccounts() {
//        return bankAccountRepository.findAll();
//    }
//
//    @GetMapping("/bankAccounts/{id}")
//    public BankAccount bankAccount(@PathVariable String id) {
//        return bankAccountRepository.findById(id)
//                .orElseThrow(() -> new RuntimeException(String.format("Account %s not found", id)));
//    }

    @GetMapping("/bankAccounts")
    public List bankAccounts() {
        return accountService.getAllAccounts();
    }

    @GetMapping("/bankAccounts/{id}")
    public BankAccountResponseDTO bankAccount(@PathVariable String id) {
        return accountService.getAccountById(id);
    }

    @PostMapping("/bankAccounts")
    public BankAccountResponseDTO save(@RequestBody BankAccountRequestDTO requestDTO) {
        return accountService.addAccount(requestDTO);
    }

    @PutMapping("/bankAccounts/{id}")
    public BankAccountResponseDTO update(@RequestBody BankAccountRequestDTO bankAccountDTO, @PathVariable String id) {
        return accountService.updateAccount(id, bankAccountDTO);
    }

    @DeleteMapping("/bankAccounts/{id}")
    public String deleteAccount(@PathVariable String id) {
        accountService.deleteAccount(id);
        return "Account " + id + " deleted successfully";
    }

//    @PostMapping("/bankAccounts")
//    public BankAccount save(@RequestBody BankAccount bankAccount) {
//        if (bankAccount.getId() == null) bankAccount.setId(UUID.randomUUID().toString());
//        return bankAccountRepository.save(bankAccount);
//    }

//    @PutMapping("/bankAccounts/{id}")
//    public BankAccount update(@RequestBody BankAccount bankAccount, @PathVariable String id) {
//        BankAccount account = bankAccountRepository.findById(id)
//                .orElseThrow(() -> new RuntimeException(String.format("Account %s not found", id)));
//        //if (bankAccount.getId() != null) account.setId(bankAccount.getId());
//        if (bankAccount.getBalance() != null) account.setBalance(bankAccount.getBalance());
//        if (bankAccount.getCurrency() != null) account.setCurrency(bankAccount.getCurrency());
//        if (bankAccount.getType() != null) account.setType(bankAccount.getType());
//        if (bankAccount.getCreatedAt() != null) account.setCreatedAt(new Date());
//        return bankAccountRepository.save(account);
//    }
//
//    @DeleteMapping("/bankAccounts/{id}")
//    public String deleteAccount(@PathVariable String id) {
//        bankAccountRepository.deleteById(id);
//        return "Account " + id + " deleted successfully";
//    }
}
