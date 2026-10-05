package com.example.bankingapp.Services;

import com.example.bankingapp.DTO.BankAccountRequestDTO;
import com.example.bankingapp.DTO.BankAccountResponseDTO;
import com.example.bankingapp.Entities.BankAccount;

import java.util.List;

public interface AccountService {
    BankAccountResponseDTO addAccount(BankAccountRequestDTO bankAccountDTO);
    BankAccountResponseDTO updateAccount(String id, BankAccountRequestDTO bankAccountDTO);
    List<BankAccountResponseDTO> getAllAccounts();
    BankAccountResponseDTO getAccountById(String id);
    void deleteAccount(String id);
}