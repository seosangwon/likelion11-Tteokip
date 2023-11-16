package com.example.koun.ItemService;

import com.example.koun.domain.User;
import com.example.koun.dto.ItemRequestDto;
import com.example.koun.dto.ItemResponseDto;
import com.example.koun.repository.UserRepository;
import com.example.koun.service.ItemService;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

//
//@SpringBootTest
//public class ItemServiceTest {
//
//    @Autowired
//    private ItemService itemService;
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @Test
//    public void createItemAndFind() {
//        // 1. 임시로 User 생성
//        LocalDateTime userBirth = LocalDateTime.of(1990, 8, 11, 0, 0);
//        User user = User.builder()
//            .userName("sangwon")
//            .phoneNum("3939480")
//            .userEmail("zmdk12055@naver.com")
//            .gender('M')
//            .userAddress("의정부시")
//            .userBirth(userBirth)
//            .build();
//        userRepository.save(user);
//
//        System.out.println("user 조회: " + user.getUserName());
//
//        // 2. Item 생성 및 저장
//        ItemRequestDto itemRequestDto1 = ItemRequestDto.builder()
//            .raffleQuantity(100)
//            .artist("John Smith")
//            .venue("Sample Venue")
//            .likeNum(200)
//            .itemName("Sample Item")
//            .description("Sample Description")
//            .build();
//
//        ItemRequestDto itemRequestDto2 = ItemRequestDto.builder()
//            .raffleQuantity(200)
//            .artist("싸이")
//            .venue("Sample Venue2")
//            .likeNum(230)
//            .itemName("Sample Item2")
//            .description("Sample Description2")
//            .build();
//
//        Long itemId = itemService.joinItem(itemRequestDto1);
//
//        System.out.println("콘서트 생성" + itemRequestDto1.getItemName());
//        System.out.println("콘서트 생성2" + itemRequestDto2.getItemName());
//
//
//        // 3. Item 조회
//
//        // 3-1. 콘서트 아이디로 아이템 조회
//        ItemResponseDto foundItem = itemService.findById(itemId);
//        System.out.println("콘서트 아이디로 아이템 조회: " + foundItem.getId());
//
//        // 3-2. 콘서트 이름으로 아이템 조회
//        ItemResponseDto test2 = itemService.findItemsByName("Sample Item");
//        System.out.println("이름으로 조회: "+ test2.getItemName());
//
//        // 3-3. 전체 아이템 조회
//        List<ItemResponseDto> allItems = itemService.getAllItems();
//          for (ItemResponseDto item : allItems) {
//              System.out.println("전체아이템 조회: " + item.getItemName());
//        }
//
//
//        // 4. 아이템 삭제
//
//        // 5. 결과 검증
////        assertEquals(itemRequestDto.getRaffleQuantity(), foundItem.getRaffleQuantity());
////        assertEquals(itemRequestDto.getArtist(), foundItem.getArtist());
////        assertEquals(itemRequestDto.getVenue(), foundItem.getVenue());
////        assertEquals(itemRequestDto.getTicketPrice(), foundItem.getTicketPrice());
////        assertEquals(itemRequestDto.getLikeNum(), foundItem.getLikeNum());
////        assertEquals(itemRequestDto.getItemName(), foundItem.getItemName());
////        assertEquals(itemRequestDto.getDescription(), foundItem.getDescription());
//
//        // 6. 이외에 필요한 검증 작업 수행
//
//
//    }
////    @Test
////    public void findItemByName(String itemName) {
////        ItemResponseDto foundByName = itemService.findItemsByName(itemName);
////
//////        assertEquals("Sample Item", foundByName.getItemName());
////        // 나머지 필드들도 검증
////    }
//
//
//
//}
//
