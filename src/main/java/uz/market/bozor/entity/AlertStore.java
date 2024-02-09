package uz.market.bozor.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.market.bozor.entity.template.BaseEntity;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class AlertStore extends BaseEntity {

    @ManyToOne
    private Alert alert;

    @ManyToOne
    private Store store;

    private int showCount;

}
