package com.c1646njava.tuvivienda.models.post;

import java.util.List;

import com.c1646njava.tuvivienda.models.administrator.Administrator;
import com.c1646njava.tuvivienda.models.image.ImagePost;
import com.c1646njava.tuvivienda.models.user.User;
import jakarta.persistence.*;


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
    @Column(name = "comments")
    private String comments;
    @Column(name = "status")
    @NotEmpty
    private String status;
    @Column(name="area")
    private float area;
    @Column(name="bathrooms")
    private int bathrooms;
    @Column(name="property_age")
    private int age;
    @Column(name="outstanding")
    private boolean outstanding;
    @Column(name="country")
    private String country;
    @Column(name="state")
    private String state;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;
    @ManyToOne
    @JoinColumn(name = "administrator_id", referencedColumnName = "id")
    private Administrator administrator;

    @OneToMany
    private List<ImagePost> image;




}
