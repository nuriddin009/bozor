package uz.market.bozor.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;
import uz.market.bozor.entity.constants.DAY;
import uz.market.bozor.entity.template.BaseEntity;

import java.time.LocalTime;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SQLDelete(sql = "update timing set deleted=true where id=?")
@SQLRestriction(value = "deleted=false")
@Entity
public class Timing extends BaseEntity {

    private LocalTime openingTime;
    private LocalTime closingTime;
    @Enumerated
    private DAY day;
    @ManyToOne(fetch = FetchType.LAZY)
    private Store store;

}
