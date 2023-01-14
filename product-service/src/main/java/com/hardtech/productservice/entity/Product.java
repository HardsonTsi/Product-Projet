package com.hardtech.productservice.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.math.BigDecimal;

@DynamicUpdate
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @NotBlank
    String name;
    @Size(min = 1, max = 255)
    String description;
    @Min(value = 100)
    @Max(value = 1000000)
    @NotNull
    BigDecimal price;
    @Enumerated(EnumType.STRING)
    @NotNull
    Category category;
}
