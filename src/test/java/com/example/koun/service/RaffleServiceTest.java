package com.example.koun.service;

import com.example.koun.domain.*;
import com.example.koun.repository.ItemRepository;
import com.example.koun.repository.RaffleRepository;
import com.example.koun.repository.SectionRepository;
import com.example.koun.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.security.crypto.keygen.KeyGenerators.string;

@SpringBootTest
class RaffleServiceTest {

    @Autowired
    private RaffleService raffleService;

    @Autowired
    private SectionRepository sectionRepository;

    @Autowired
    private RaffleRepository raffleRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private UserRepository userRepository;

    @Test
    void drawRaffleForSection() {


        Item item1 = Item.builder()
                .itemName("브루노마스!!")
                .dateTime(LocalDateTime.of(2023, 8, 4, 15, 30))
                .build();
        itemRepository.save(item1);


        // 1. Section 생성 및 저장
        Section section = Section.builder()
                .seatQuantity(100) // 예시로 100개의 좌석
                .item(item1)
                .build();
        sectionRepository.save(section);

        // 2. 해당 Section에 래플 데이터 여러 개 저장
        for (int i = 0; i < 100; i++) { // 10개의 래플 데이터 생성
            String userEmail = "user" + i + "@example.com";
            User user = User.builder()
                    .userEmail(userEmail)
                    .userName("상원")
                    .gender('M')
                    .build();
            userRepository.save(user);

            Raffle raffle = Raffle.builder()
                    .raffleCount(1) // 각 래플은 10개씩 응모
                    .section(section)
                    .user(user)
                    .item(item1)
                    .build();
            raffleRepository.save(raffle);


        }
        for (int i = 101; i < 200; i++) { // 10개의 래플 데이터 생성
            String userEmail = "user" + i + "@example.com";
            User user = User.builder()
                    .userEmail(userEmail)
                    .userName("홍길동")
                    .gender('M')

                    .build();
            userRepository.save(user);

            Raffle raffle = Raffle.builder()
                    .raffleCount(2) // 각 래플은 10개씩 응모
                    .section(section)
                    .user(user)
                    .item(item1)
                    .build();
            raffleRepository.save(raffle);


        }

        System.out.println("section에 응모된 Raffles" + section.getRaffles());

        // 3. 래플 실행 메서드 호출
        raffleService.drawRaffleForSection(section.getId());

        // 4. 결과 검증
        List<Raffle> updatedRaffles = raffleRepository.findAll();
        int winCount = 0;
        for (Raffle r : updatedRaffles) {
            if ("true".equals(r.getRaffleStatus())) {
                winCount += r.getRaffleCount();
            }
        }

        if (section.getSeatQuantity() == winCount){
            System.out.println("최종결과는 둑구두구두구두구둗구두굳구 True");
        }


    }
}