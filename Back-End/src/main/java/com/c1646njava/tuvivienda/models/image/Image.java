package com.c1646njava.tuvivienda.models.image;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "image")
public class Image {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "image_id")
    private String imageId;

    public Image(String name, String imageUrl, String imageId){
        this.name = name;
        this.imageUrl = imageUrl;
        this.imageId = imageId;
    }



}
