package com.psionicgeek.crmbackendspring.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String employeeUserName;
    private String employeePassword;
    private boolean isActive;
    private String employeeEmail;
    private LocalDateTime dateCreated;
    private LocalDateTime dateModified;


    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinTable(name = "user_role",joinColumns = @JoinColumn(name = "id"),inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> authorities;
}
