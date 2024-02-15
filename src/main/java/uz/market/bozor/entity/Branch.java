package uz.market.bozor.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
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
    @Column(length = 500)
    private String nameUz;
    @Column(length = 500)
    private String nameRu;
    @Column(length = 500)
    private String nameEng;
    @ManyToOne(fetch = FetchType.LAZY)
    private Store store;
}
