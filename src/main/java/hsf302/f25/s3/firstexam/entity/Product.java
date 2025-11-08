package hsf302.f25.s3.firstexam.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
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
    @Size(min = 5, max = 50, message = "ProductName must be between 5 and 50 characters")
    @NotBlank(message = "required")
    String productName;

    @NotNull(message = "required")
    @Min(value = 100, message = "Price must be greater than or equal to 100")
    Integer price;

    @NotNull(message = "required")
    @Min(value = 0, message = "Stock must be greater than or equal to 0")
    @Max(value = 1000, message = "Stock must be less than or equal to 1000")
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
