package com.example.koun.domain;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Section {

    @Id
    @GeneratedValue
    @Column(name="section_id")
    private Long id;

    private int price;

    @Column(name="seat_quantity")
    private int seatQuantity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="item_id")
    private Item item;
    
    @OneToMany(mappedBy = "section")
    private List<Raffle> raffles = new ArrayList<>();


    @Builder
    public Section(int price, int seatQuantity, Item item, List<Raffle> raffles) {
        this.price = price;
        this.seatQuantity = seatQuantity;
        this.item = item;
        this.raffles = raffles;
    }

    public void setItem(Item item) {
        if (this.item != null) {
            this.item.getSections().remove(this);
        }
        this.item = item;
        item.getSections().add(this);
    }
}
