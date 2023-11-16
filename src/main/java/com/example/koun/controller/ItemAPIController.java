package com.example.koun.controller;
import com.example.koun.dto.ItemGetRequestDto;
import com.example.koun.dto.ItemRequestDto;
import com.example.koun.dto.ItemResponseDto;
//import com.example.koun.dto.ItemUpdateRequestDto;
import com.example.koun.service.ItemService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
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

    // 아이템 이름,유저Id로 조회
    @GetMapping("/search")
    public ResponseEntity<ItemResponseDto> getItemByItemNameUserId(@RequestParam("userId") Long userId,
                                                                   @RequestParam("itemName") String itemName)   {
    ItemGetRequestDto itemGetRequestDto = new ItemGetRequestDto();
    itemGetRequestDto.setItemName(itemName);
    itemGetRequestDto.setUserId(userId);
    ItemResponseDto itemByGetRequestDto = itemService.findItemByGetRequestDto(itemGetRequestDto);

        return new ResponseEntity<>(itemByGetRequestDto, HttpStatus.OK);

    }


    // 모든 아이템 조회
    @GetMapping("/all")
    public ResponseEntity<List<ItemResponseDto>> getAllItems() {
        List<ItemResponseDto> items = itemService.getAllItems();
        return new ResponseEntity<>(items, HttpStatus.OK);
    }


    //user의 찜 목록 조회
    @GetMapping("{userId}")
    public ResponseEntity<List<ItemResponseDto>> getUserLikeItems(@PathVariable Long userId){
        List<ItemResponseDto> items = itemService.getUserLikeItemList(userId);

        return new ResponseEntity<>(items,HttpStatus.OK);
    }

    // 인기 top 1~10 조회 (likeNum 기준) -- 통과

    @GetMapping("/tops")
    public ResponseEntity<Page<ItemResponseDto>> list(
            @PageableDefault(size = 10, sort = "likeNum", direction = Sort.Direction.DESC)
            Pageable pageable) {
        Page<ItemResponseDto> topLikedItems = itemService.getTopLikes(pageable);
        return new ResponseEntity<>(topLikedItems, HttpStatus.OK);
    }



    // 신규 top 1 ~ 10 조회 (uploadTime 기준) -- 통과
    @GetMapping("/uploads")
    public ResponseEntity<Page<ItemResponseDto>> listNewTops(
            @PageableDefault(size = 10, sort = "uploadTime", direction = Sort.Direction.DESC)
            Pageable pageable) {
        Page<ItemResponseDto> newTopItems = itemService.getNewTopLikes(pageable);
        return new ResponseEntity<>(newTopItems, HttpStatus.OK);
    }






}
