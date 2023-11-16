package com.example.koun;


import com.example.koun.domain.Item;
import com.example.koun.domain.Raffle;
import com.example.koun.domain.Section;
import com.example.koun.domain.User;
import com.example.koun.repository.ItemRepository;
import com.example.koun.repository.RaffleRepository;
import com.example.koun.repository.SectionRepository;
import com.example.koun.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;


@Component
@RequiredArgsConstructor
public class InitDB {
    private final InitService initService;

    @PostConstruct
    public void init() {
        initService.dbInit1();
    }

    @Component
    @Transactional
    @RequiredArgsConstructor
    static class InitService {

        private final UserRepository userRepository;
        private final SectionRepository sectionRepository;
        private final ItemRepository itemRepository;
        private final RaffleRepository raffleRepository;

        public void dbInit1() {


            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");


            LocalDateTime dateTime = LocalDateTime.parse("2023-10-17 18:00", formatter);
            LocalDate uploadTime = LocalDate.parse("2023-08-15", dateFormatter);
            LocalDateTime dateTime1 = LocalDateTime.parse("2023-08-26 18:00", formatter);
            LocalDate uploadTime1 = LocalDate.parse("2023-08-17", dateFormatter);
            LocalDateTime dateTime2 = LocalDateTime.parse("2023-10-17 18:00", formatter);
            LocalDate uploadTime2 = LocalDate.parse("2023-11-16", dateFormatter);
            LocalDateTime dateTime3 = LocalDateTime.parse("2023-08-25 18:00", formatter);
            LocalDate uploadTime3 = LocalDate.parse("2023-11-15", dateFormatter);
            LocalDateTime dateTime4 = LocalDateTime.parse("2023-09-09 18:00", formatter);
            LocalDate uploadTime4 = LocalDate.parse("2023-11-14", dateFormatter);
            LocalDateTime dateTime5 = LocalDateTime.parse("2023-09-02 18:00", formatter);
            LocalDate uploadTime5 = LocalDate.parse("2023-11-17", dateFormatter);
            LocalDateTime dateTime6 = LocalDateTime.parse("2023-10-03 18:00", formatter);
            LocalDate uploadTime6 = LocalDate.parse("2023-11-13", dateFormatter);
            LocalDateTime dateTime7 = LocalDateTime.parse("2023-09-12 18:00", formatter);
            LocalDate uploadTime7 = LocalDate.parse("2023-11-16", dateFormatter);
            LocalDateTime dateTime8 = LocalDateTime.parse("2023-09-09 18:00", formatter);
            LocalDate uploadTime8 = LocalDate.parse("2023-11-15", dateFormatter);
            LocalDateTime dateTime9 = LocalDateTime.parse("2023-08-25 18:00", formatter);
            LocalDate uploadTime9 = LocalDate.parse("2023-08-19", dateFormatter);

            LocalDateTime dateTime10 = LocalDateTime.parse("2024-01-27 18:00", formatter);
            LocalDate uploadTime10 = LocalDate.parse("2023-11-14", dateFormatter);

            LocalDateTime dateTime11 = LocalDateTime.parse("2023-11-24 18:00", formatter);
            LocalDate uploadTime11 = LocalDate.parse("2023-11-13", dateFormatter);

            LocalDateTime dateTime12 = LocalDateTime.parse("2023-12-30 18:00", formatter);
            LocalDate uploadTime12 = LocalDate.parse("2023-11-13", dateFormatter);

            LocalDateTime dateTime13 = LocalDateTime.parse("2023-12-22 18:00", formatter);
            LocalDate uploadTime13 = LocalDate.parse("2023-11-16", dateFormatter);

            LocalDateTime dateTime14 = LocalDateTime.parse("2023-12-23 18:00", formatter);
            LocalDate uploadTime14 = LocalDate.parse("2023-11-15", dateFormatter);


            Item item = Item.builder()
                    .itemName("2023 샘스미스 내한공연")
                    .artist("샘스미스")
                    .venue("KSP DOME")
                    .dateTime(dateTime)
                    .ageRequirement("만 19세이상")
                    .runningTime("150분")
                    .post("https://ticketimage.interpark.com/Play/image/large/23/23007654_p.gif")
                    .uploadTime(uploadTime)
                    .likeNum(150)
                    .build();
            itemRepository.save(item);

            Item item1 = Item.builder()
                    .itemName("싸이 흠뻑쇼 -부산")
                    .artist("싸이")
                    .venue("부산 아시아드 보조경기장")
                    .dateTime(dateTime1)
                    .ageRequirement("전체관람가")
                    .runningTime("240분")
                    .post("https://ticketimage.interpark.com/Play/image/large/23/23007291_p.gif")
                    .uploadTime(uploadTime1)
                    .likeNum(200)
                    .build();
            itemRepository.save(item1);

            Item item2 = Item.builder()
                    .itemName("2023 김동률 콘서트")
                    .artist("김동률")
                    .venue("KSPO DOME")
                    .dateTime(dateTime2)
                    .ageRequirement("8세 이상 관람가능")
                    .runningTime("150분")
                    .post("https://ticketimage.interpark.com/Play/image/large/23/23011026_p.gif")
                    .uploadTime(uploadTime2)
                    .likeNum(170)
                    .build();
            itemRepository.save(item2);

            Item item3 = Item.builder()
                    .itemName("2023 검정치마 앵콜 공연")
                    .artist("검정치마")
                    .venue("블루스퀘어 마스터카드홀")
                    .dateTime(dateTime3)
                    .ageRequirement("15세이상")
                    .runningTime("100분")
                    .post("https://ticketimage.interpark.com/Play/image/large/23/23011003_p.gif")
                    .uploadTime(uploadTime3)
                    .likeNum(500)
                    .build();
            itemRepository.save(item3);

            Item item4 = Item.builder()
                    .itemName("2PM 15th Concert")
                    .artist("2PM")
                    .venue("잠실실내체육관")
                    .dateTime(dateTime4)
                    .ageRequirement("만 7세이상")
                    .runningTime("140분")
                    .post("https://ticketimage.interpark.com/Play/image/large/23/23010652_p.gif")
                    .uploadTime(uploadTime4)
                    .likeNum(300)
                    .build();
            itemRepository.save(item4);

            Item item5 = Item.builder()
                    .itemName("성시경 with friends")
                    .artist("성시경")
                    .venue("KSPO DOME")
                    .dateTime(dateTime5)
                    .ageRequirement("초등학생이상 관람가")
                    .runningTime("120분")
                    .post("https://ticketimage.interpark.com/Play/image/large/23/23010177_p.gif")
                    .uploadTime(uploadTime5)
                    .likeNum(200)
                    .build();
            itemRepository.save(item5);

            Item item6 = Item.builder()
                    .itemName("히사이시조 음악콘서트")
                    .artist("위필하모닉 오케스트라")
                    .venue("롯데콘서트홀")
                    .dateTime(dateTime6)
                    .ageRequirement("초등학생이상 관람가")
                    .runningTime("120분")
                    .post("https://ticketimage.interpark.com/Play/image/large/23/23011060_p.gif")
                    .uploadTime(uploadTime6)
                    .likeNum(20)
                    .build();
            itemRepository.save(item6);

            Item item7 = Item.builder()
                    .itemName("LE SSERAFIM TOUR")
                    .artist("르세라핌")
                    .venue("잠실실내체육관")
                    .dateTime(dateTime7)
                    .ageRequirement("만 9세이상")
                    .runningTime("120분")
                    .post("https://ticketimage.interpark.com/Play/image/large/23/23009197_p.gif")
                    .uploadTime(uploadTime7)
                    .likeNum(800)
                    .build();
            itemRepository.save(item7);

            Item item8 = Item.builder()
                    .itemName("이문세의 숲속음악회")
                    .artist("이문세")
                    .venue("봉평 허브나라")
                    .dateTime(dateTime8)
                    .ageRequirement("만 7세이상")
                    .runningTime("110분")
                    .post("https://ticketimage.interpark.com/Play/image/large/23/23011005_p.gif")
                    .uploadTime(uploadTime8)
                    .likeNum(900)
                    .build();
            itemRepository.save(item8);

            Item item9 = Item.builder()
                    .itemName("2023 영탁 단독 콘서트")
                    .artist("영탁")
                    .venue("KSPO DOME")
                    .dateTime(dateTime9)
                    .ageRequirement("초등학생 이상 관람가")
                    .runningTime("120분")
                    .post("https://ticketimage.interpark.com/Play/image/large/23/23009717_p.gif")
                    .uploadTime(uploadTime9)
                    .likeNum(800)
                    .build();
            itemRepository.save(item9);

            Item item10 = Item.builder()
                    .itemName("10CM WINTER CONCERT ‘9+1’")
                    .artist("10CM")
                    .venue("KSPO DOME")
                    .dateTime(dateTime10)
                    .ageRequirement("만 8세이상")
                    .runningTime("120분")
                    .post("https://ticketimage.interpark.com/Play/image/large/23/23015862_p.gif")
                    .uploadTime(uploadTime10)
                    .likeNum(50)
                    .build();
            itemRepository.save(item10);

            Item item11 = Item.builder()
                    .itemName("AKMU 2023 CONCERT ［AKMUTOPIA］")
                    .artist("AKMU")
                    .venue("경희대 평화의전당")
                    .dateTime(dateTime11)
                    .ageRequirement("만 7세이상")
                    .runningTime("120분")
                    .post("https://ticketimage.interpark.com/Play/image/large/23/23012636_p.gif")
                    .uploadTime(uploadTime11)
                    .likeNum(20)
                    .build();
            itemRepository.save(item11);

            Item item12 = Item.builder()
                    .itemName("2023 god’s MASTERPIECE - 부산")
                    .artist("god")
                    .venue("부산 벡스코 제1전시장")
                    .dateTime(dateTime12)
                    .ageRequirement("8세이상 관람가능")
                    .runningTime("150분")
                    .post("https://ticketimage.interpark.com/Play/image/large/23/23014112_p.gif")
                    .uploadTime(uploadTime12)
                    .likeNum(180)
                    .build();
            itemRepository.save(item12);

            Item item13 = Item.builder()
                    .itemName("싸이 올나잇스탠드 2023 〈흰눈싸이로〉")
                    .artist("싸이")
                    .venue("KSPO DOME")
                    .dateTime(dateTime13)
                    .ageRequirement("전체관람가")
                    .runningTime("300분")
                    .post("https://ticketimage.interpark.com/Play/image/large/23/23016343_p.gif")
                    .uploadTime(uploadTime13)
                    .likeNum(80)
                    .build();
            itemRepository.save(item13);

            Item item14 = Item.builder()
                    .itemName("2023 CRUSH CONCERT")
                    .artist("크러쉬")
                    .venue("잠실실내체육관")
                    .dateTime(dateTime14)
                    .ageRequirement("만 7세이상")
                    .runningTime("150분")
                    .post("https://ticketimage.interpark.com/Play/image/large/23/23015513_p.gif")
                    .uploadTime(uploadTime14)
                    .likeNum(780)
                    .build();
            itemRepository.save(item14);



            User user = User.builder()
                    .userName("TestName")
                    .userEmail("TestEmail")
                    .build();
            userRepository.save(user);


            List<Item> items = Arrays.asList(item, item1, item2, item3, item4, item5,
                    item6, item7, item8, item9,item10,item11,item12,item13,item14);

            for (int i = 0; i < items.size(); i++) {
                createSectionsForItem(items.get(i), i);
            }


        }


        public void createSectionsForItem(Item item, int index) {
            String[] sectionNames = {"스탠딩-가", "스탠딩-나", "스탠딩-다", "스탠딩-라", "지정석-가", "지정석-나", "지정석-다"};
            int[] seatQuantities;
            int[] prices;

            if (index % 2 == 0) { // 짝수 번째 아이템
                seatQuantities = new int[]{150, 150, 150, 150, 200, 200, 200};
                prices = new int[]{120000, 120000, 120000, 120000, 90000, 90000, 90000};
            } else { // 홀수 번째 아이템
                seatQuantities = new int[]{100, 100, 100, 100, 150, 150, 150};
                prices = new int[]{100000, 100000, 100000, 100000, 80000, 80000, 80000};
            }

            for (int i = 0; i < sectionNames.length; i++) {
                Section section = Section.builder()
                        .sectionName(sectionNames[i])
                        .seatQuantity(seatQuantities[i])
                        .item(item)
                        .price(prices[i])
                        .build();
                sectionRepository.save(section);
            }


        }


    }


}
