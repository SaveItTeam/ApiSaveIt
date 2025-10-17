package com.api.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true)
    private long id;
    private String name;
    @Column(unique = true)
    private String email;
    private String password;
    @Column(name = "enterprise_id")
    private long enterpriseId;
    @Column(name = "is_admin")
    private boolean isAdmin;
}
