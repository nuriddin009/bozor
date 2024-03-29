package uz.market.bozor.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;
import uz.market.bozor.entity.template.BaseEntity;

import java.util.List;

@Getter
@Setter
@Builder
@SQLDelete(sql = "update category set deleted = true where id=?")
@SQLRestriction(value = "deleted = false")
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Category extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    private Attachment attachment;
    private String nameUz;
    private String nameRu;
    private String nameEng;
    @ManyToOne(fetch = FetchType.LAZY)
    private Store store;
    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    private List<Product> products;
}
