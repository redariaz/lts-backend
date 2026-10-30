package com.example.login_service.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table("login_details")
public class Login {

    @Id
    private Long id;
    private Long userId;
    private String email;
    private String password;

}
