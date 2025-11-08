package hsf302.f25.s3.firstexam.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Table(name = "sony_categories")
@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = lombok.AccessLevel.PRIVATE)
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cate_id")
    Integer cateId;
    @Column(name = "cate_name", columnDefinition = "VARCHAR(50)", nullable = false)
    @NotBlank(message = "required")
    String cateName;
    @Column(columnDefinition = "VARCHAR(10)", nullable = false)
    String status;

    @OneToMany(mappedBy = "cateId")
    List<Product> products;
}
