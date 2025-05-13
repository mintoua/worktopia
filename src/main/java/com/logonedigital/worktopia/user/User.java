package com.logonedigital.worktopia.user;

import com.logonedigital.worktopia.employe.Employee;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.security.auth.Subject;
import java.security.Principal;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class User implements UserDetails, Principal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long dbId;

    @Column(nullable = false, unique = true, updatable = false)
    private UUID publicId = null;
    @PrePersist
    private void initializeUuid() {
        this.publicId = UUID.randomUUID();
    }

    private String firstname;
    private String lastname;
    @Column(unique = true, nullable = false)
    private String email;
    @Column(nullable = false)
    private String password;
    private boolean enabled;
    private boolean accountLocked;
    @Column(name = "verification_code")
    private String verificationCode;
    @Column(name = "verification_expiration")
    private LocalDateTime verificationCodeExpiresAt;

    @OneToOne(mappedBy = "user")
    private Employee linkedEmployee;

    @Enumerated(EnumType.STRING)
    private Role role;


    @Override
    public String getName() {
        return this.email;
    }

    @Override
    public boolean implies(Subject subject) {
        return Principal.super.implies(subject);
    }


    public String fullName() {
        return firstname + " " + lastname;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(
                new SimpleGrantedAuthority("ROLE_"+role.name())
        );
    }

    @Override
    public String getUsername() {
        return this.email;
    }
}
