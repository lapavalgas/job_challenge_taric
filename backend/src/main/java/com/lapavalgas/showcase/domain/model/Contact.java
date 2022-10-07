package com.lapavalgas.showcase.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "contact")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@EqualsAndHashCode(exclude = {"customer"})
public class Contact {

    @Id
    @Column(name = "customer_id")
    private Long id;

    @Column(name = "email")
    private String email;

    @Column(name = "tel")
    private String tel;

    @OneToOne(cascade = CascadeType.ALL)
    @MapsId
    @JoinColumn(name = "customer_id")
    @JsonIgnoreProperties("contact")
    @ToString.Exclude
    private Customer customer;

}
