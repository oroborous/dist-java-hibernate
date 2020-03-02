package edu.wctc.hibernate.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "donut_shop")
public class DonutShop {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "shop_id")
    private int id;
    @Column(name = "nm")
    private String name;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "detail_id")
    private DonutShopDetail detail;

    @OneToMany(mappedBy = "shop",
            cascade = CascadeType.ALL)
    private List<Donut> donuts;

    @ManyToMany(cascade = CascadeType.ALL
            /**{CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.PERSIST,
            CascadeType.REFRESH}**/)
    @JoinTable(name = "donut_shop_city",
            joinColumns = @JoinColumn(name = "shop_id"),
            inverseJoinColumns = @JoinColumn(name = "city_id"))
    private List<City> cities;

    public DonutShop() {

    }

    public DonutShop(String name) {
        this.name = name;
    }

    public void add(City tempCity) {
        if (cities == null) {
            cities = new ArrayList<>();
        }
        cities.add(tempCity);
    }


    public void add(Donut tempDonut) {
        if (donuts == null) {
            donuts = new ArrayList<>();
        }
        donuts.add(tempDonut);
        tempDonut.setShop(this);
    }


}
