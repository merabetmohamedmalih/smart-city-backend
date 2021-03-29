package com.example.springmvcrest.order.domain;

import com.example.springmvcrest.user.provider.domain.Provider;
import com.example.springmvcrest.user.simple.domain.SimpleUser;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(exclude = {"user","provider"})
@Builder
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "simpleUser_id")
    private SimpleUser user;

    @ManyToOne
    @JoinColumn(name = "provider_id")
    private Provider provider;

    @OneToMany(mappedBy = "order",cascade = {CascadeType.MERGE,CascadeType.REMOVE})
    Set<OrderProductVariant> cartProductVariants=new HashSet<>();

}
