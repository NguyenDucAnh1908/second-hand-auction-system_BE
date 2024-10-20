package com.second_hand_auction_system.controller;

import com.second_hand_auction_system.dtos.request.item.ItemApprove;
import com.second_hand_auction_system.dtos.request.item.ItemDto;
import com.second_hand_auction_system.dtos.responses.ResponseObject;
import com.second_hand_auction_system.dtos.responses.item.AuctionItemResponse;
import com.second_hand_auction_system.service.item.IItemService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("${api.prefix}/item")
public class ItemController {
    private final IItemService itemService;

    @PostMapping("")
    public ResponseEntity<?> createItem(
            @Valid @RequestBody ItemDto itemDto,
            BindingResult result
    ) throws Exception {
        if (result.hasErrors()) {
            List<String> errorMessages = result.getFieldErrors()
                    .stream()
                    .map(FieldError::getDefaultMessage)
                    .toList();
            return ResponseEntity.badRequest().body(
                    ResponseObject.builder()
                            .status(HttpStatus.BAD_REQUEST)
                            .message(String.valueOf(errorMessages))
                            .build()
            );
        }
        itemService.addItem(itemDto);
        return ResponseEntity.ok(
                ResponseObject.builder()
                        .status(HttpStatus.OK)
                        .message("Success")
                        .build()
        );
    }

    @PutMapping("/{itemId}")
    public ResponseEntity<?> updateItem(
            @PathVariable Integer itemId,
            @Valid @RequestBody ItemDto itemDto,
            BindingResult result
    ) throws Exception {
        if (result.hasErrors()) {
            List<String> errorMessages = result.getFieldErrors()
                    .stream()
                    .map(FieldError::getDefaultMessage)
                    .toList();
            return ResponseEntity.badRequest().body(
                    ResponseObject.builder()
                            .status(HttpStatus.BAD_REQUEST)
                            .message(String.valueOf(errorMessages))
                            .build()
            );
        }
        itemService.updateItem(itemId, itemDto);
        return ResponseEntity.ok(
                ResponseObject.builder()
                        .status(HttpStatus.OK)
                        .message("Success")
                        .build()
        );
    }

    @PutMapping("/approve/{itemId}")
    public ResponseEntity<?> approveItem(
            @PathVariable Integer itemId,
            @Valid @RequestBody ItemApprove itemApprove,
            BindingResult result
    ) throws Exception {
        if (result.hasErrors()) {
            List<String> errorMessages = result.getFieldErrors()
                    .stream()
                    .map(FieldError::getDefaultMessage)
                    .toList();
            return ResponseEntity.badRequest().body(
                    ResponseObject.builder()
                            .status(HttpStatus.BAD_REQUEST)
                            .message(String.valueOf(errorMessages))
                            .build()
            );
        }
        itemService.approve(itemId, itemApprove);
        return ResponseEntity.ok(
                ResponseObject.builder()
                        .status(HttpStatus.OK)
                        .message("Success")
                        .build()
        );
    }

    @DeleteMapping("/{itemId}")
    public ResponseEntity<?> deleteItem(
            @PathVariable Integer itemId
    ) throws Exception {
        itemService.deleteItem(itemId);
        return ResponseEntity.ok(
                ResponseObject.builder()
                        .status(HttpStatus.OK)
                        .message("Success")
                        .build()
        );
    }

    @GetMapping("top-10-featured-item")
    public ResponseEntity<?> getTop10FeaturedItem() throws Exception {
        List<AuctionItemResponse> itemResponseList = itemService.getTop10FeaturedItem();
        return ResponseEntity.ok(
                ResponseObject.builder()
                        .status(HttpStatus.OK)
                        .message("Success")
                        .data(itemResponseList)
                        .build()
        );
    }
}
