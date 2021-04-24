package com.capstone.fans.domain.user;


import com.capstone.fans.domain.BaseTimeEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;


@NoArgsConstructor
@Getter
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "dtype")
public abstract class User extends BaseTimeEntity implements UserDetails {

    @Id
    @Column(name = "USER_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(length = 40, nullable = false)
    private String email;

    @Column(length = 100, nullable = false)
    private String password;

    @Column(length = 20, nullable = false)
    private String name;

    private String blockchain_address;

    private String nickname;

    private String address;

    private String phone_number;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    private byte[] profile_image;


    private String auth;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> roles = new HashSet<>();
        for (String role : auth.split(",")) {
            roles.add(new SimpleGrantedAuthority(role));
        }
        return roles;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
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

    public User(String email, String password, String name, String blockchain_address, String nickname, String address, String phone_number, byte[] profile_image, String auth) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.blockchain_address = blockchain_address;
        this.nickname = nickname;
        this.address = address;
        this.phone_number = phone_number;
        this.profile_image = profile_image;
        this.auth = auth;
    }
}
