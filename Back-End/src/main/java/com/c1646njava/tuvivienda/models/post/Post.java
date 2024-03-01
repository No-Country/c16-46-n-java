package com.c1646njava.tuvivienda.models.post;

import java.util.ArrayList;
import java.util.List;

import com.c1646njava.tuvivienda.models.administrator.Administrator;
import com.c1646njava.tuvivienda.models.image.ImagePost;
import com.c1646njava.tuvivienda.models.user.User;
import jakarta.persistence.*;


import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;


@Entity
@Data
@NoArgsConstructor
@Table(name = "post")
public class Post {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //InDTO
    @Column(name = "name")
    @NotEmpty
    private String name;

    @Column(name = "description")
    @NotEmpty
    private String description;

    @Column(name = "type")
    @NotEmpty
    private String type;

    @Column(name = "bedrooms")
    @NotEmpty
    private Integer bedrooms;

    @Column(name = "address")
    @NotEmpty
    private String address;

    @NotEmpty
    @Column(name="contract_type")
    private String contract_type;

    @Column(name = "price")
    @NotEmpty
    private Long price;

    @Column(name="area")
    @NotEmpty
    private float area;

    @Column(name="bathrooms")
    @NotEmpty
    private int bathrooms;

    @Column(name="property_age")
    private int age;

    @Column(name="country")
    private int country;

    @Column(name="state")
    private int state;



    @ManyToMany
    @JoinTable(name = "user_favorites",
            joinColumns = @JoinColumn(name = "post_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private List<User> fav = new ArrayList<>();
    @ManyToOne
    @JoinColumn(name = "administrator_id", referencedColumnName = "id")
    private Administrator administrator;

    @OneToMany
    private List<ImagePost> image;

    //EXTERNAL
    @Column(name = "comments")
    private String comments;

    //on Rent, on sale, not available
    @Column(name = "status")
    @NotEmpty
    private String status;

    //advertising payment
    @Column(name="outstanding")
    private boolean outstanding = false;






}
