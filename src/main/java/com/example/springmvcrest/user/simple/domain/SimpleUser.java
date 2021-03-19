package com.example.springmvcrest.user.simple.domain;

import com.example.springmvcrest.product.domain.Category;
import com.example.springmvcrest.user.user.domain.Authority;
import com.example.springmvcrest.user.user.domain.Role;
import com.example.springmvcrest.user.user.domain.User;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class SimpleUser extends User {

    @ManyToMany
    @JoinTable(name = "simple_users_category",
            joinColumns = @JoinColumn(name = "category_id"),
            inverseJoinColumns = @JoinColumn(name = "users_id"))
    private Set<Category> interestCenter = new HashSet<>();

    @Singular
    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST}, fetch = FetchType.EAGER)
    @JoinTable(name = "simple_user_role",
            joinColumns = {@JoinColumn(name = "SimpleUser_ID", referencedColumnName = "ID")},
            inverseJoinColumns = {@JoinColumn(name = "ROLE_ID", referencedColumnName = "ID")})
    private Set<Role> roles=new HashSet<Role>();

    @Transient
    private Set<Authority> authorities=new HashSet<Authority>();

    public Set<Authority> getAuthorities() {
        return this.roles.stream()
                .map(Role::getAuthorities)
                .flatMap(Set::stream)
                .collect(Collectors.toSet());
    }

    @Builder.Default
    private Boolean accountNonExpired = true;

    @Builder.Default
    private Boolean accountNonLocked = true;

    @Builder.Default
    private Boolean credentialsNonExpired = true;

    @Builder.Default
    private Boolean enabled = true;
}