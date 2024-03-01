package com.c1646njava.tuvivienda.models.image;

import com.c1646njava.tuvivienda.models.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Table(name = "image_user")
public class ImageUser extends Image{

    @OneToOne
    private User user;

    public ImageUser(User user, Image image) {
    }
}
