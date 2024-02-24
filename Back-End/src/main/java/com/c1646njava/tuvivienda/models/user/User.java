package com.c1646njava.tuvivienda.models.user;

import java.util.ArrayList;
import java.util.List;

import com.c1646njava.tuvivienda.models.image.ImageUser;
import com.c1646njava.tuvivienda.models.post.Post;

import com.c1646njava.tuvivienda.models.user.dto.RequestUser;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    @OneToMany
    private List<Post> fav;

    public User(RequestUser user){
        this.name = user.name();
        this.email = user.email();
        this.password = user.password();
        this.avatar = (ImageUser) user.avatar();
        this.country = user.country();
        this.fav = new ArrayList<>();
    }
}
