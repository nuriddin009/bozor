package uz.market.bozor.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;
import uz.market.bozor.entity.template.BaseEntity;

@Getter
@Setter
@Builder
@SQLDelete(sql = "update store set deleted = true where id=?")
@SQLRestriction(value = "deleted = false")
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Store extends BaseEntity {


    private String name;
    private String supportPhone;


    private String email;
    @Column(length = 16)
    @Pattern(regexp = "^[0-9]{13,19}$", message = "Invalid card number format")
    private String bankAccount;
    private String bankAccountDetails;

    @OneToOne(fetch = FetchType.LAZY)
    private Location location;
}
