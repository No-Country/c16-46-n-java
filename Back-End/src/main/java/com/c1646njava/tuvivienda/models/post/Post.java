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
    @Column(name = "views")
    @NotEmpty
    private Long views;
    @Column(name = "price")
    @NotEmpty
    private Long price;
    @Column(name = "comments")
    private String comments;
    @Column(name = "status")
    @NotEmpty
    private Boolean status;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;
    @ManyToOne
    @JoinColumn(name = "administrator_id", referencedColumnName = "id")
    private Administrator administrator;

    @OneToMany
    private List<ImagePost> image;




}
