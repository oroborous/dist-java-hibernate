package edu.wctc.hibernate.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "city")
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "city_id")
    private int id;
    @Column(name = "nm")
    private String name;
    @Column(name = "state")
    private String state;

    @ManyToMany(cascade = {CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.PERSIST,
            CascadeType.REFRESH})
    @JoinTable(name = "donut_shop_city",
            joinColumns = @JoinColumn(name = "city_id"),
            inverseJoinColumns = @JoinColumn(name = "shop_id"))
    private List<DonutShop> shops;

    public City() {
        // no-arg constructor
    }

    public City(String name, String state) {
        this.name = name;
        this.state = state;
    }

    public void add(DonutShop tempShop) {
        if (shops == null) {
            shops = new ArrayList<>();
        }
        shops.add(tempShop);
    }

}
