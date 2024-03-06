package com.example.demo.user;


import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


import java.util.*;

@Entity // This tells Hibernate to make a table out of this class
@Table(name="usersAuth")
public class UserAuth implements UserDetails {

    @Id
    //@Basic(optional = false)
    @Column(name="uuid")
    @GeneratedValue(strategy = GenerationType.UUID)
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private UUID id;

    @Column(name="username")
    private String username;

    @Column(name="password")
    private String password;

    @Column(name="password_change_date")
    private Date passwordChangeDate;

    @Column(name="last_activity_date")
    @CreationTimestamp
    private Date lastActivityDate;

    @Column(name="account_created_date")
    @CreationTimestamp
    private Date accountCreatedDate;

    @Column(name="mail")
    private String email;

    @Column(name="role")
    @Enumerated(EnumType.STRING)
    private Roles role;

    public UserAuth(){};
    public UserAuth(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = Roles.ROLE_USER;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> list = new ArrayList<GrantedAuthority>();
        list.add(new SimpleGrantedAuthority(role.toString()));
        return list;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
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

    public Roles getRole() {
        return role;
    }

    public String getEmail(){return email;}

    public void setRole(Roles role) {
        this.role = role;
    }
}

