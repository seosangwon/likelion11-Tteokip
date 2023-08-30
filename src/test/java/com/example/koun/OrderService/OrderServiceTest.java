package com.example.koun.OrderService;

import com.example.koun.domain.*;
import com.example.koun.repository.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class OrderServiceTest {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private RaffleRepository raffleRepository;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private SectionRepository sectionRepository;


    @Test
    public void 오더테스트(){

        User user1 = User.builder()
                .userName("sangwon")
                .userEmail("zmdk1205@naver.com")
                .gender('M')
                .build();
        userRepository.save(user1);

        Item item= Item.builder()
                .itemName("lauv")
                .build();
        itemRepository.save(item);

        Section section = Section.builder()
                .sectionName("구역 가")
                .item(item)
                .build();
        sectionRepository.save(section);


        Raffle raffle = Raffle.builder()
                .user(user1)
                .item(item)
                .section(section)
                .build();
        raffleRepository.save(raffle);

        Order order = Order.builder()
                .user(user1)
                .raffle(raffle)
                .build();
        orderRepository.save(order);


        System.out.println("order정보으아아아아아아아아아ㅏ"+order.getRaffle().getItem().getItemName());
        System.out.println("order의 유저정보 으아아아아아아아"+order.getRaffle().getUser().getUserName());
        List<Order> orderList = user1.getOrders();
        System.out.println("유저가 주문한 목록 으아아앙아아아"+orderList);

        //아니 그러면 궁금한게 order 생성 할 때 user도 필요 없는거 아님??


    }


}
