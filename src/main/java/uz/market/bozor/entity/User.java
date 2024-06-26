package uz.market.bozor.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;
import uz.market.bozor.entity.constants.Language;
import uz.market.bozor.entity.constants.Status;
import uz.market.bozor.entity.constants.Variables;
import uz.market.bozor.entity.template.BaseEntity;

import java.util.Set;


@Getter
@Setter
@Builder
@Table(name = "users")
@SQLDelete(sql = "update users set deleted = true where id=?")
@SQLRestriction(value = "deleted = false")
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User extends BaseEntity {

    @Column(length = 64)
    private String firstname;
    @Column(length = 64)
    private String lastname;
    @Email(message = "invalid email", regexp = Variables.emailRegex)
    @Column(unique = true)
    private String email;
    private String password;
    private String phoneNumber;
    @Enumerated(EnumType.STRING)
    private Status status;

    @Enumerated(EnumType.STRING)
    private Language language = Language.uz;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;


}
