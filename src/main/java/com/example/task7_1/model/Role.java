package com.example.task7_1.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

@Entity
@Table(name = "roles")
@Setter
@Getter
public class Role implements GrantedAuthority {
    @Id
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Override
    public String getAuthority() {
        return "ROLE_" + this.title;
    }
}
