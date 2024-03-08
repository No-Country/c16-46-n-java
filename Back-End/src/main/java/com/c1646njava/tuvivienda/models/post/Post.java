package com.c1646njava.tuvivienda.models.post;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.c1646njava.tuvivienda.models.administrator.Administrator;
import com.c1646njava.tuvivienda.models.comment.comment;
import com.c1646njava.tuvivienda.models.image.ImagePost;
import com.c1646njava.tuvivienda.models.user.User;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import com.c1646njava.tuvivienda.utils.createdDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "post")
public class Post extends createdDate{

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //InDTO
    @Column(name = "name")
    private String name;

    @Column(name = "description")
    @Lob
    private String description;

    @Column(name = "type")
    private String type;

    @Column(name = "bedrooms")
    private Integer bedrooms;

    @Column(name = "address")
    private String address;


    @Column(name="contract_type")
    private String contract_type;

    @Column(name = "price")
    private Long price;

    @Column(name="area")
    private float area;

    @Column(name="bathrooms")
    private int bathrooms;

    @Column(name="property_age")
    private int age;

    @Column(name="country")
    private String country;

    @Column(name="state")
    private String state;

    @OneToMany(mappedBy = "post", cascade = CascadeType.REMOVE)
    private List<ImagePost> image;


    //EXTERNAL

    //advertising payment
    @Column(name="featured")
    private int featured = 0;


    //fav feature
    @ManyToMany
    @JoinTable(name = "user_favorites",
            joinColumns = @JoinColumn(name = "post_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private List<User> fav = new ArrayList<>();


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "administrator_id", referencedColumnName = "id")
    private Administrator administrator;

    @JsonBackReference
    //comment feature
    @OneToMany(mappedBy = "post",cascade = CascadeType.REMOVE)
    private List<comment> comments = null;

    @Column(name = "featuredDate")
    private LocalDate featuredDate = null;



}
