package uz.market.bozor.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToOne;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;
import uz.market.bozor.entity.template.BaseEntity;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SQLDelete(sql = "update location set deleted = true where id=?")
@SQLRestriction(value = "deleted = false")
@Builder
@Entity
public class Location extends BaseEntity {

    private double latitude;
    private double longitude;

    @OneToOne(fetch = FetchType.LAZY)
    private Location location;
}
