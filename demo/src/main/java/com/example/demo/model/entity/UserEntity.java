package com.example.demo.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.util.Set;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
public class UserEntity extends BaseEntity {

    @Column(name = "username", nullable = false, unique = true)
    @Size(min = 2, message = "The username must be at least 2 characters long")
    private String username;

    @Column(name = "password", nullable = false)
    @Size(min = 3, message = "The password must be at least 3 characters long")
    private String password;

    @Column(name = "email", nullable = false, unique = true)
    @Email(message = "Invalid email")
    private String email;

    @Column(name = "github_address", nullable = false, unique = true)
    private String githubAddress;

    @ManyToOne
    private RoleEntity role;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private Set<HomeworkEntity> homework;

}
