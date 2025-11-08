package hsf302.f25.s3.firstexam.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Table(name = "sony_products")
@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = lombok.AccessLevel.PRIVATE)
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    Integer productId;
    @Column(name = "product_name", columnDefinition = "VARCHAR(50)", nullable = false)
    @NotBlank(message = "required")
    String productName;

    @NotNull(message = "required")
    Integer price;

    @NotNull(message = "required")
    Integer stock;

    @Column(name = "created_at")
    LocalDateTime createdAt;
    @ManyToOne(fetch = FetchType.EAGER)
    Category cate;

    @PrePersist
    public void onCreate() {
        if (createdAt == null) {
            this.createdAt = LocalDateTime.now();
        }
    }
}
