package com.example.demo.user;


import jakarta.persistence.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


import java.util.Collection;
import java.util.Date;
import java.util.UUID;

@Entity // This tells Hibernate to make a table out of this class
@Table(name="usersAuth")
public class UserAuth implements UserDetails {

    @Id
    //@Basic(optional = false)
    @Column(name="uuid")
    @GeneratedValue(strategy = GenerationType.UUID)
    @JdbcTypeCode(SqlTypes.VARCHAR)
    UUID id;

    @Column(name="username")
    String username;

    @Column(name="password")
    String password;

    @Column(name="password_change_date")
    Date passwordChangeDate;

    @Column(name="last_activity_date")
    Date lastActivityDate;

    @Column(name="account_created_date")
    Date accountCreatedDate;

    @Column(name="account_enabled")
    Boolean accountEnabled;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
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
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return accountEnabled;
    }
}
