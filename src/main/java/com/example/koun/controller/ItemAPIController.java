package com.example.koun.controller;
import com.example.koun.dto.ItemRequestDto;
import com.example.koun.dto.ItemResponseDto;
//import com.example.koun.dto.ItemUpdateRequestDto;
import com.example.koun.service.ItemService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/items")
@RequiredArgsConstructor
public class ItemAPIController {
    private final ItemService itemService;
    // 새로운 아이템 등록
    @PostMapping("/admin")
    public ResponseEntity<Long> createItem(@RequestBody ItemRequestDto requestDto) {
        Long itemId = itemService.joinItem(requestDto);
        return new ResponseEntity<>(itemId, HttpStatus.CREATED);
    }
    // 특정 아이템 조회
    @GetMapping
    public ResponseEntity<ItemResponseDto> getItem(@RequestParam Long itemId) {
        ItemResponseDto item = itemService.findById(itemId);
        return new ResponseEntity<>(item, HttpStatus.OK);
    }
    // 아이템 정보 업데이트
    // (주석 해제하고 사용할 수 있도록 작성)
//    @PutMapping("/update")
//    public ResponseEntity<Void> updateItem(@RequestParam Long itemId, @RequestBody ItemUpdateRequestDto updateDto) {
//        itemService.updateItem(itemId, updateDto);
//        return new ResponseEntity<>(HttpStatus.OK);
//    }
    // 아이템 삭제
    @DeleteMapping("/delete")
    public ResponseEntity<Void> deleteItem(@RequestParam Long itemId) {
        itemService.deleteItem(itemId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // 아이템 이름으로 조회
    @GetMapping("/search")
    public ResponseEntity<ItemResponseDto> searchItemsByName(@RequestParam String itemName) {
        ItemResponseDto item = itemService.findItemsByName(itemName);
//        if (item != null) {
//            return new ResponseEntity<>(item, HttpStatus.OK);
//        } else {정
        return new ResponseEntity<>(item, HttpStatus.OK);
    }


    // 모든 아이템 조회
    @GetMapping("/all")
    public ResponseEntity<List<ItemResponseDto>> getAllItems() {
        List<ItemResponseDto> items = itemService.getAllItems();
        return new ResponseEntity<>(items, HttpStatus.OK);
    }
}
