package com.capstone.fans.domain.user;


import com.capstone.fans.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@NoArgsConstructor
@Getter
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "dtype")
public abstract class User extends BaseTimeEntity {

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

    private String address;

    private String phone_number;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    private byte[] profile_image;

    public User(String email, String password, String name, String blockchain_address, String address, String phone_number, byte[] profile_image) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.blockchain_address = blockchain_address;
        this.address = address;
        this.phone_number = phone_number;
        this.profile_image = profile_image;
    }
}
