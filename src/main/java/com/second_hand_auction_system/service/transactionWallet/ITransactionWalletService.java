package com.second_hand_auction_system.service.transactionWallet;

import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;

public interface ITransactionWalletService {
    ResponseEntity<?> getAll(String keyword, LocalDateTime startDate, LocalDateTime endDate, Pageable pageable);

    ResponseEntity<?> getTransactionWallets(int size, int page, String name);

    ResponseEntity<?> getTransactionById(int id);
}
