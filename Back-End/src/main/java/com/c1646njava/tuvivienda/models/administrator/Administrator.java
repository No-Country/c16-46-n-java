package com.c1646njava.tuvivienda.models.administrator;

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
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name = "administrator")
public class Administrator extends User {

    @Column(name = "phone_number")
    private String phoneNumber;
    @OneToMany
    private List<Post> posts;
}
