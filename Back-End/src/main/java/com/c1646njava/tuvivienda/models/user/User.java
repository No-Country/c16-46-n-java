package com.c1646njava.tuvivienda.models.user;

import java.util.ArrayList;
import java.util.List;

import com.c1646njava.tuvivienda.models.administrator.Administrator;
import com.c1646njava.tuvivienda.models.comment.comment;
import com.c1646njava.tuvivienda.models.image.ImageUser;
import com.c1646njava.tuvivienda.models.post.Post;

import com.c1646njava.tuvivienda.models.user.dto.RequestUser;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;

@Data
@NoArgsConstructor
@Entity
@AllArgsConstructor
@Table(name = "user")
public class User {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "email")
    private String email;
    @Column(name = "password")
    private String password;
    @OneToOne
    @JoinColumn(name = "image_id", referencedColumnName = "id")
    private ImageUser avatar;

    @Column(name = "country")
    private String country;

    @OneToOne(mappedBy = "user", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    Administrator administrator;

    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
    private List<comment> comments;


    @ManyToMany(mappedBy = "fav")
    private List<Post> fav = new ArrayList<>();

    //advertise Post feature
    @Column(name = "advertisingTokens")
    private int advertisingToken;



    public User(RequestUser user){
        this.name = user.name();
        this.email = user.email();
        this.password = user.password();
        this.avatar = (ImageUser) user.avatar();
        this.country = user.country();
        this.fav = new ArrayList<>();
        this.advertisingToken = 2;
    }






}
