package uz.market.bozor.entity;

import jakarta.persistence.Column;
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
public class Rating extends BaseEntity {

    @ManyToOne
    private User user;

    private short rating;
    @Column(columnDefinition = "text")
    private String description;
}
