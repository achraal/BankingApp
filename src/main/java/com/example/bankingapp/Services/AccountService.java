package com.example.bankingapp.Services;

import com.example.bankingapp.DTO.BankAccountRequestDTO;
import com.example.bankingapp.DTO.BankAccountResponseDTO;
import java.util.List;

public interface AccountService {
    BankAccountResponseDTO addAccount(BankAccountRequestDTO bankAccountDTO);
    BankAccountResponseDTO updateAccount(String id, BankAccountRequestDTO bankAccountDTO);
    // ICI : on ajoute
    List getAllAccounts();
    BankAccountResponseDTO getAccountById(String id);
    void deleteAccount(String id);
}