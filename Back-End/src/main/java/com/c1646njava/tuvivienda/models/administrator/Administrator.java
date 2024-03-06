package com.c1646njava.tuvivienda.models.administrator;

import com.c1646njava.tuvivienda.models.image.ImageUser;
import com.c1646njava.tuvivienda.models.post.Post;
import com.c1646njava.tuvivienda.models.user.User;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
@Table(name = "administrator")
public class Administrator {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "phone_number")
    private String phoneNumber;

    @JsonBackReference("administratorUser")
    @OneToMany(mappedBy = "administrator", cascade = CascadeType.REMOVE)
    private List<Post> posts;

    @JsonBackReference
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_Id", referencedColumnName = "id")
    private User user;



    //This doesn't receive a list of post because when a user upgrade, it doesn't have any post yet
    public Administrator(String phoneNumber, User associatedUser) {
        this.phoneNumber = phoneNumber;
        this.user = associatedUser;
    }
}
