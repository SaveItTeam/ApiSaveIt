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
    private boolean is_buyer = false;
    public Employee() {
    }

    public Employee(long id, String name, String email, String password, long enterprise_id, boolean is_buyer) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.enterprise_id = enterprise_id;
        this.is_buyer = is_buyer;
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
    public boolean getIs_buyer() {
        return is_buyer;
    }
    public void setIs_buyer(boolean is_buyer) {
        this.is_buyer = is_buyer;
    }

    @Override
    public String toString() {
        return "Funcionario{" +
                "id=" + id +
                ", nome='" + name + '\'' +
                ", email='" + email + '\'' +
                ", senha='" + password + '\'' +
                ", empresa_id=" + enterprise_id +
                ", is_buyer=" + is_buyer +
                '}';
    }
}
