package com.second_hand_auction_system.service.user;

import com.second_hand_auction_system.dtos.request.user.Authentication;
import com.second_hand_auction_system.dtos.request.user.ChangePassWordDTO;
import com.second_hand_auction_system.dtos.request.user.RegisterRequest;
import com.second_hand_auction_system.dtos.request.user.UserDto;
import com.second_hand_auction_system.dtos.responses.user.AuthenticationResponse;
import com.second_hand_auction_system.dtos.responses.user.ListUserResponse;
import com.second_hand_auction_system.dtos.responses.user.RegisterResponse;
import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.Principal;

@Service
public interface IUserService {
    ResponseEntity<RegisterResponse> register(@Valid RegisterRequest registerRequest);

    ResponseEntity<AuthenticationResponse> login(@Valid Authentication authentication);

    void refresh(HttpServletRequest request, HttpServletResponse response) throws IOException;

    ResponseEntity<ListUserResponse> getListUser();

    ResponseEntity<?> isValidOtp(String email, String otp);

    ResponseEntity<?> registerStaff(RegisterRequest registerRequest);

    ResponseEntity<?> updateUser(int id, UserDto userResponse);

    ResponseEntity<?> getUserId(int id);

    ResponseEntity<?> deleteUser(int id);

    ResponseEntity<?> forgotPassword(String email) throws MessagingException;

    ResponseEntity<?> changePassword(ChangePassWordDTO request, Principal connectedUser);
}
