package com.example.koun.RaffleService;

import com.example.koun.domain.*;
import com.example.koun.dto.RaffleSaveRequestDto;
import com.example.koun.dto.RaffleFindResponseDto;
import com.example.koun.repository.ItemRepository;
import com.example.koun.repository.SectionRepository;
import com.example.koun.repository.UserRepository;
import com.example.koun.service.RaffleService;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class RaffleServiceTest {

    @Autowired
    private RaffleService raffleService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private SectionRepository sectionRepository;



    @Test
    public void joinRaffleAndFind() {
        // 1. 임시로 User, Item, Section 생성
        User user = User.builder()
                .userName("sangwon")
                .userEmail("zmdk12055@naver.com")
                .build();
        // user 필드 설정
        userRepository.save(user);

        User user1 = User.builder()
                .userName("miyeon")
                .userEmail("aldus0123@naver.com")
                .build();
        userRepository.save(user1);
        User user2 = User.builder()
                .userName("jiyoen")
                .userEmail("al1231233@naver.com")
                .build();
        userRepository.save(user2);

        Item item = Item.builder()
                .itemName("lauv")
                .dateTime(LocalDateTime.of(2100, 7, 1, 2, 30))
                .build();
        // item 필드 설정
        itemRepository.save(item);

        Item item1 = Item.builder()
                .itemName("브루노마스!!")
                .dateTime(LocalDateTime.of(2023, 8, 4, 15, 30))
                .build();
        itemRepository.save(item1);



        Section section = Section.builder()
                .item(item)
                .build();
        // section 필드 설정
        sectionRepository.save(section);

        Section section1 = Section.builder()
                .item(item1)
                .build();
        sectionRepository.save(section1);

        Section section2 = Section.builder()
                .item(item1)
                .build();
        sectionRepository.save(section2);

//        Order order = Order.builder()
//                .user(user)
//                .build();
//
//        orderRepository.save(order);

//        Order order1 = Order.builder()
//                .user(user1)
//                .build();
//
//        orderRepository.save(order1);
//
//        Order order2 = Order.builder()
//                .user(user1)
//                .build();
//
//        orderRepository.save(order2);

        System.out.println("1111111111111111");
        // 2. Raffle 생성 //user, item(lauv) , section
        RaffleSaveRequestDto raffleRequestDto = RaffleSaveRequestDto.builder()
                .raffleCount(1)
                .raffleDrawDate("2023-08-04 15:30")
                .userId(user.getId())
                .itemId(item.getId())
                .sectionId(section.getId())
                .build();

        //user,item1(브루노마스),section1
        RaffleSaveRequestDto raffleRequestDto1 = RaffleSaveRequestDto.builder()
                .raffleCount(1)
                .raffleDrawDate("2023-08-04 15:30")
                .userId(user.getId())
                .itemId(item1.getId())
                .sectionId(section1.getId())
                .build();

        RaffleSaveRequestDto raffleRequestDto2 = RaffleSaveRequestDto.builder()
                .raffleCount(1)
                .raffleDrawDate("2023-08-04 15:30")
                .userId(user1.getId())
                .itemId(item1.getId())
                .sectionId(section1.getId())
                .build();

        RaffleSaveRequestDto raffleRequestDto3 = RaffleSaveRequestDto.builder()
                .raffleCount(1)
                .raffleDrawDate("2023-08-04 15:30")
                .userId(user2.getId())
                .itemId(item1.getId())
                .sectionId(section2.getId())
                .build();


        Long raffleId = raffleService.joinRaffle(raffleRequestDto);
        Long raffleId1 = raffleService.joinRaffle(raffleRequestDto1);
        Long raffleId2 = raffleService.joinRaffle(raffleRequestDto2);
        Long raffleId3 = raffleService.joinRaffle(raffleRequestDto3);






        // 3. Raffle 조회
        //     RaffleResponseDto raffleResponseDto = raffleService.findRaffle(raffleId);
        List<RaffleFindResponseDto> userRaffles = raffleService.findRafflesByUserId(user.getId());
        List<RaffleFindResponseDto> itemRaffles = raffleService.findRafflesByItemId(item1.getId());
        List<RaffleFindResponseDto> sectionRaffles = raffleService.findRafflesBySectionId((item1.getId()));
        List<Section> sections = item1.getSections();


        System.out.println("유저 래플 응모 테스트:" + user.getRaffles());
        System.out.println("item1의 section들 "+ sections);
        System.out.println("유저 래플 응모 테스트:" + userRaffles);
        System.out.println("콘서트 래플 응모 테스트:" + itemRaffles);
        System.out.println("iem1의 section 테스트"+ sectionRaffles);


        //user sangwon의 item - 브루노마스
        RaffleFindResponseDto findResponseDto = raffleService.findRaffleByUserIdAndItemId(user.getId(), item1.getId());

        System.out.println("찾아온 리스판스Dto 곧 삭제될거임"+findResponseDto);

        raffleService.deleteRaffle(findResponseDto.getId());
        System.out.println("11111111111111");



        List<RaffleFindResponseDto> userRaffles1 = raffleService.findRafflesByUserId(user.getId());
        List<RaffleFindResponseDto> itemRaffles1 = raffleService.findRafflesByItemId(item1.getId());
        List<RaffleFindResponseDto> sectionRaffles1 = raffleService.findRafflesBySectionId((item1.getId()));

        System.out.println("유저 래플 응모 테스트:" + user.getRaffles());
        System.out.println("유저의 불러온 래플즈"+ userRaffles1);
        System.out.println("콘서트 래플 응모 테스트:" + itemRaffles1);
        System.out.println("iem1의 section 테스트"+ sectionRaffles1);



        // 4. 결과 검증


        // 이외에 다른 필드들도 검증
    }
}
