package uz.market.bozor.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.*;
import uz.market.bozor.entity.template.BaseEntity;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class AttachmentQuality extends BaseEntity {

    @ManyToOne
    private Attachment low;

    @ManyToOne
    private Attachment high;

}
