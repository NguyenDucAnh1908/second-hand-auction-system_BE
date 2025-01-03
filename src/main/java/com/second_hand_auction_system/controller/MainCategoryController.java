package com.second_hand_auction_system.controller;

import com.second_hand_auction_system.dtos.request.mainCategory.MainCategoryDto;
import com.second_hand_auction_system.dtos.responses.ResponseObject;
import com.second_hand_auction_system.dtos.responses.mainCategory.CategoryVsSubCategoryResponse;
import com.second_hand_auction_system.dtos.responses.mainCategory.MainCategoryResponse;
import com.second_hand_auction_system.service.mainCategory.IMainCategoryService;
import com.second_hand_auction_system.service.mainCategory.MainCategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("${api.prefix}/main-category")
public class MainCategoryController {
    private final IMainCategoryService mainCategoryService;


    @PostMapping("")
    public ResponseEntity<?> createMainCategory(
            @Valid @RequestBody MainCategoryDto mainCategoryDto,
            BindingResult result
    ) throws Exception {
        if (result.hasErrors()) {
            List<String> errorMessages = result.getFieldErrors()
                    .stream()
                    .map(FieldError::getDefaultMessage)
                    .toList();
            return ResponseEntity.badRequest().body(errorMessages);
        }
        mainCategoryService.addMainCategory(mainCategoryDto);
        return ResponseEntity.ok(
                ResponseObject.builder()
                        .status(HttpStatus.OK)
                        .message("Tạo danh mục thành công")
                        .build()
        );
    }

    //@PutMapping("/main-category-id")
    @PutMapping("/{id}")
    public ResponseEntity<?> updateMainCategory(
            @PathVariable Integer id,
            @Valid @RequestBody MainCategoryDto mainCategoryDto,
            BindingResult result
    ) throws Exception {
        if (result.hasErrors()) {
            List<String> errorMessages = result.getFieldErrors()
                    .stream()
                    .map(FieldError::getDefaultMessage)
                    .toList();
            return ResponseEntity.badRequest().body(errorMessages);
        }
        mainCategoryService.updateMainCategory(id, mainCategoryDto);
        return ResponseEntity.ok(
                ResponseObject.builder()
                        .status(HttpStatus.OK)
                        .message("Cập nhật thành công")
                        .build()
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteMainCategory(
            @PathVariable Integer id
    ) throws Exception {
        mainCategoryService.deleteMainCategory(id);
        return ResponseEntity.ok(
                ResponseObject.builder()
                        .status(HttpStatus.OK)
                        .message("Xóa danh mục thành công")
                        .build()
        );
    }

    @GetMapping("")
    public ResponseEntity<?> getMainCategory() throws Exception {
        List<CategoryVsSubCategoryResponse> mainCategoryResponses = mainCategoryService.getMainCategoryVsSubCategory();
        try {
            return ResponseEntity.ok(
                    ResponseObject.builder()
                            .status(HttpStatus.OK)
                            .message("thành công")
                            .data(mainCategoryResponses)
                            .build()
            );
        } catch (Exception e) {
            return ResponseEntity.ok(
                    ResponseObject.builder()
                            .status(HttpStatus.BAD_REQUEST)
                            .message("Lỗi " + e.getMessage())
                            //.error(e.getMessage())
                            .build()
            );
        }

    }

    @GetMapping("{id}")
    public ResponseEntity<?> getMainCategoryT(int id) throws Exception {
        MainCategoryResponse mainCategoryResponses = mainCategoryService.getMainCategoryTest(id);
        try {
            return ResponseEntity.ok(
                    ResponseObject.builder()
                            .status(HttpStatus.OK)
                            .message("thành công")
                            .data(mainCategoryResponses)
                            .build()
            );
        } catch (Exception e) {
            return ResponseEntity.ok(
                    ResponseObject.builder()
                            .status(HttpStatus.BAD_REQUEST)
                            .message("Lỗi " + e.getMessage())
                            //.error(e.getMessage())
                            .build()
            );
        }

    }

    @GetMapping("get-main")
    public ResponseEntity<?> getMainCategoryT() throws Exception {
        return mainCategoryService.getMainCategorys();
    }
}
