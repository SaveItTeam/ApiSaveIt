package com.api.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
@Entity
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true)
    private long id;
    private String name;
    private String email;
    private String password;
    private long enterprise_id;

    public Employee() {
    }

    public Employee(long id, String name, String email, String password, long enterprise_id) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.enterprise_id = enterprise_id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public long getEnterprise_id() {
        return enterprise_id;
    }

    public void setEnterprise_id(long enterprise_id) {
        this.enterprise_id = enterprise_id;
    }


    @Override
    public String toString() {
        return "Funcionario{" +
                "id=" + id +
                ", nome='" + name + '\'' +
                ", email='" + email + '\'' +
                ", senha='" + password + '\'' +
                ", empresa_id=" + enterprise_id +
                '}';
    }
}
