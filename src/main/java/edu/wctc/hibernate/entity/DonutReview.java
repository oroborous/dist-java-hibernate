package edu.wctc.hibernate.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "donut_review")
public class DonutReview {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_id")
    private int id;
    @Column(name = "review")
    private String review;
    @Column(name = "stars")
    private double stars;
    @Column(name = "review_date")
    private LocalDate reviewDate;

    public DonutReview() {
        // no-arg constructor
    }

    public DonutReview(String review, double stars, LocalDate reviewDate) {
        this.review = review;
        this.stars = stars;
        this.reviewDate = reviewDate;
    }

}
