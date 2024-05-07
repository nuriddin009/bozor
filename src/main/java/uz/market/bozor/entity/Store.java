package uz.market.bozor.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;
import uz.market.bozor.entity.template.BaseEntity;

import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SQLDelete(sql = "update store set deleted =true where id=?")
@SQLRestriction(value = "deleted=false")
@Entity
public class Store extends BaseEntity implements Serializable {


    @Column(nullable = false)
    private String nameUz;
    @Column(nullable = false)
    private String nameRu;
    @Column(nullable = false)
    private String nameEng;

    private Double latitude;
    private Double longitude;

    private String supportEmail;
    private String supportPhone;
    private BigDecimal ordersCount;

    @ManyToOne(fetch = FetchType.LAZY)
    private User owner;
}
