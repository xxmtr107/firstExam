package hsf302.f25.s3.firstexam.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Table(name = "sony_accounts")
@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = lombok.AccessLevel.PRIVATE)
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_id")
    Integer accountId;

    @Column(columnDefinition = "VARCHAR(13)", unique = true, nullable = false)
    @NotBlank(message = "required")
    String phone;
    @Column(columnDefinition = "VARCHAR(13)", nullable = false)
    @NotBlank(message = "required")
    String password;
    Integer roleId;
}
