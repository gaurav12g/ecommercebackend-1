package com.ecommerceBackend.ecommerceBackend.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private  Long id;

    @NotNull
    @Size(max = 50)
    private String name;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="parent_category_id")
    private Category parentCategory;

    private int level;

}
