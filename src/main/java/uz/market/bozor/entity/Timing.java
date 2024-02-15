package uz.market.bozor.entity;

import jakarta.persistence.*;
import lombok.*;
import uz.market.bozor.entity.constants.DAY;
import uz.market.bozor.entity.template.BaseEntity;

import java.math.BigInteger;
import java.time.LocalTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Timing extends BaseEntity {

    @Enumerated(EnumType.STRING)
    private DAY day;

    private LocalTime openingTime;
    private LocalTime closingTime;

    @ManyToOne(fetch = FetchType.LAZY)
    private Store store;

}
