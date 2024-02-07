package uz.market.bozor.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;
import uz.market.bozor.entity.template.BaseEntity;

@Getter
@Setter
@Builder
@SQLDelete(sql = "update branch set deleted = true where id=?")
@SQLRestriction(value = "deleted = false")
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Branch extends BaseEntity {

    private double longitude;
    private double latitude;
    private String address;
    @Column(columnDefinition = "text")
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    private Store store;
}
