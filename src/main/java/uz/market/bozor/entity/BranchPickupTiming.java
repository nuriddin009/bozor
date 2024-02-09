package uz.market.bozor.entity;

import jakarta.persistence.*;
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
@SQLDelete(sql = "update branch_pickup_timing set deleted = true where id=?")
@SQLRestriction(value = "deleted = false")
@Entity
public class BranchPickupTiming extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    private Branch branch;
    @Enumerated(EnumType.STRING)
    private DAY day;
    private LocalTime openingTime;
    private LocalTime closingTime;

}
