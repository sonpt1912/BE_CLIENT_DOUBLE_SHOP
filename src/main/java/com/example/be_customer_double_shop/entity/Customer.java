package com.example.be_customer_double_shop.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "customer")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Customer implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;


    @Column(name = "username", length = 45)
    private String username;

    @Column(name = "name", length = 45)
    private String name;

    @Column(name = "gender")
    private Integer gender;

    @Column(name = "phone", length = 45)
    private String phone;

    @Column(name = "birth_day", length = 45)
    private String birthDay;

    @Column(name = "email", length = 45)
    private String email;

    @Column(name = "password", length = 145)
    private String password;

    @Column(name = "status")
    private Integer status;

    @Column(name = "created_by", nullable = false, length = 45)
    private String createdBy;

    @Column(name = "updated_by", length = 45)
    private String updatedBy;

    @Column(name = "created_time", nullable = false, length = 45)
    private String createdTime;

    @Column(name = "updated_time", length = 45)
    private String updatedTime;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}

