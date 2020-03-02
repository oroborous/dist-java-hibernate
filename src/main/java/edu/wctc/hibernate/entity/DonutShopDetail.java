package edu.wctc.hibernate.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * This class demonstrates a one-to-one table relationship. A DonutShop object has one DonutShopDetail. Each DonutShopDetail belongs to one DonutShop.
 */

@Data
@Entity
@Table(name = "donut_shop_detail")
public class DonutShopDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "detail_id")
    private int id;

    @Column(name = "year_founded")
    private int yearFounded;

    @Column(name = "hometown")
    private String hometown;

    @OneToOne(mappedBy = "detail",
            cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    private DonutShop shop;

    public DonutShopDetail() {
        // no-arg constructor
    }

    public DonutShopDetail(int yearFounded, String hometown) {
        this.yearFounded = yearFounded;
        this.hometown = hometown;
    }


}
