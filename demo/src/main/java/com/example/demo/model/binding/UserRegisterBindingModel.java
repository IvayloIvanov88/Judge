package com.example.demo.model.binding;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRegisterBindingModel {

    @Length(min = 2, message = "Username length must be minimum two characters")
    @Length(max = 10, message = "Username length must be maximum ten characters")
    @NotNull
    private String username;

    @NotNull
    @Length(min = 3, message = "Password length must be minimum three characters")
    private String password;

    @NotNull
    private String confirmPassword;

    @NotNull
    @Email(message = "Enter valid email address")
    private String email;

    @NotNull
    @Pattern(regexp = "https:\\/\\/github\\.com\\/.+", message = "Enter valid git address")
    private String git;
}
