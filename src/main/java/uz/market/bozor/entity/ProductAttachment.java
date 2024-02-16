package uz.market.bozor.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import lombok.*;
import uz.market.bozor.entity.Attachment;
import uz.market.bozor.entity.Product;
import uz.market.bozor.entity.template.BaseEntity;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class ProductAttachment extends BaseEntity {
    @ManyToOne(fetch = FetchType.LAZY)
    private Attachment low;

    @ManyToOne(fetch = FetchType.LAZY)
    private Attachment high;

    @ManyToOne(fetch = FetchType.LAZY)
    private Product product;
}
