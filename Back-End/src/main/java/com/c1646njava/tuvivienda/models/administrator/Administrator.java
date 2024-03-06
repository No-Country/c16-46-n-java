package com.c1646njava.tuvivienda.models.administrator;

import com.c1646njava.tuvivienda.models.image.ImageUser;
import com.c1646njava.tuvivienda.models.post.Post;
import com.c1646njava.tuvivienda.models.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Table(name = "administrator")
public class Administrator extends User {

    @Column(name = "phone_number")
    private String phoneNumber;
    @OneToMany
    private List<Post> posts;
    @Column(name = "user_id")
    private Long userId;

    public Administrator(String name,String country, String email, String password, String phoneNumber, ImageUser avatar, List<Post> fav) {
        super.setName(name);
        super.setCountry(country);
        super.setEmail(email);
        super.setPassword(password);
        this.setPhoneNumber(phoneNumber);
        super.setAvatar(avatar);
        super.setFav(fav);
    }
}
