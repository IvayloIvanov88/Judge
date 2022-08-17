package com.example.demo.model.binding;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserLoginBindingModel {

    @Length(min = 2, message = "Username length must be minimum two characters")
    @Length(max = 10, message = "Username length must be maximum ten characters")
    @NotNull
    private String username;

    @Length(min = 3, message = "Password length must be minimum three characters")
    @NotNull
    private String password;

}
