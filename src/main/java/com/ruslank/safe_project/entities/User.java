package com.ruslank.safe_project.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Generated;

@Entity
@Data
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy  = GenerationType.IDENTITY)
    private int id;

    private String username;

    private String password;

    private String authority;
}
