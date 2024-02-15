package uz.market.bozor.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;
import uz.market.bozor.entity.constants.BadgeType;
import uz.market.bozor.entity.template.BaseEntity;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@SQLDelete(sql = "update badges set deleted = true where id=?")
@SQLRestriction(value = "deleted = false")
@Entity
public class Badges extends BaseEntity {

    private String backgroundColor;
    private String color;
    private String text;
    @Enumerated(EnumType.STRING)
    private BadgeType typeName;

}
