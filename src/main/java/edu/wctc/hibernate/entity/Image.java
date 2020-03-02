package edu.wctc.hibernate.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name="image")
@Data
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="image_id")
    private int id;

    @Column(name="nm")
    private String name;

    @Column(name="mime_type")
    private String mimeType;

    @Column(name="file")
    private byte[] file;
}
