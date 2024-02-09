package uz.market.bozor.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;
import uz.market.bozor.entity.template.BaseEntity;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@SQLDelete(sql = "update alert set deleted = true where id=?")
@SQLRestriction(value = "deleted = false")
@Entity
public class Alert extends BaseEntity {

    private String title;

    @Column(columnDefinition = "text")
    private String message;

    private Integer showCount;
}
