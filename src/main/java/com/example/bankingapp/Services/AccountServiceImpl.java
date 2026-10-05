package com.example.bankingapp.Services;

import com.example.bankingapp.DTO.BankAccountRequestDTO;
import com.example.bankingapp.DTO.BankAccountResponseDTO;
import com.example.bankingapp.Entities.BankAccount;
import com.example.bankingapp.Mappers.AccountMapper;
import com.example.bankingapp.Repositories.BankAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
public class AccountServiceImpl implements AccountService {
    @Autowired
    private BankAccountRepository bankAccountRepository;
    @Autowired
    private AccountMapper accountMapper;

    @Override
    public BankAccountResponseDTO addAccount(BankAccountRequestDTO bankAccountDTO) {
        BankAccount bankAccount = new BankAccount().builder()
                .id(UUID.randomUUID().toString())
                .createdAt(new Date())
                .balance(bankAccountDTO.getBalance())
                .type(bankAccountDTO.getType())
                .currency(bankAccountDTO.getCurrency())
                .build();
        BankAccount savedBankAccount = bankAccountRepository.save(bankAccount);
        BankAccountResponseDTO bankAccountResponseDTO = accountMapper.fromBankAccountResponseDTO(savedBankAccount);

        //L'utilité de Mapper nous évite de répéter ces blocs de code
//        BankAccountResponseDTO bankAccountResponseDTO = new BankAccountResponseDTO().builder()
//                .id(savedBankAccount.getId())
//                .createdAt(savedBankAccount.getCreatedAt())
//                .balance(savedBankAccount.getBalance())
//                .type(savedBankAccount.getType())
//                .currency(savedBankAccount.getCurrency())
//                .build();
//        bankAccountResponseDTO.setId(bankAccount.getId());
        return bankAccountResponseDTO;
    }
    @Override
    public BankAccountResponseDTO updateAccount(String id, BankAccountRequestDTO bankAccountDTO) {
        BankAccount account = bankAccountRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(String.format("Account %s not found", id)));

        // if (bankAccount.getId() != null) account.setId(bankAccount.getId());
        if (bankAccountDTO.getBalance() != null) account.setBalance(bankAccountDTO.getBalance());
        if (bankAccountDTO.getCurrency() != null) account.setCurrency(bankAccountDTO.getCurrency());
        if (bankAccountDTO.getType() != null) account.setType(bankAccountDTO.getType());

        // Mise à jour de la date (comme vous l'aviez fait dans le contrôleur)
        account.setCreatedAt(new Date());

        BankAccount savedBankAccount = bankAccountRepository.save(account);
        return accountMapper.fromBankAccountResponseDTO(savedBankAccount);
    }

    @Override
    // ICI : on ajoute
    public List<BankAccount> getAllAccounts() {
        List bankAccounts = bankAccountRepository.findAll();
        return bankAccounts.stream()
                .map(account -> accountMapper.fromBankAccountResponseDTO(account))
                .collect(Collectors.toList());
    }

    @Override
    public BankAccountResponseDTO getAccountById(String id) {
        BankAccount account = bankAccountRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(String.format("Account %s not found", id)));
        return accountMapper.fromBankAccountResponseDTO(account);
    }

    @Override
    public void deleteAccount(String id) {
        bankAccountRepository.deleteById(id);
    }
}
