package com.second_hand_auction_system.service.knowYourCustomer;

import com.second_hand_auction_system.dtos.request.kyc.ApproveKyc;
import com.second_hand_auction_system.dtos.request.kyc.KycDto;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

public interface IKnowYourCustomerService {
    ResponseEntity<?> register(KycDto kyc);

    ResponseEntity<?> approveKyc(@Valid ApproveKyc kycDto, int kycId) throws MessagingException;

    ResponseEntity<?> getKycById(int kycId);

    ResponseEntity<?> getKycs( int page, int size);

    ResponseEntity<?> updateKyc(@Valid KycDto kycDto);

    ResponseEntity<?> getKycUserById();

}
