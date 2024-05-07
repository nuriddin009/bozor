package uz.market.bozor.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;
import uz.market.bozor.entity.template.BaseEntity;

@Builder
@Getter
@Setter
@SQLDelete(sql = "update category set deleted=true where id=?")
@SQLRestriction(value = "deleted=false")
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Category extends BaseEntity {

    @Column(nullable = false)
    private String nameUz;
    @Column(nullable = false)
    private String nameRu;
    @Column(nullable = false)
    private String nameEng;
    @ManyToOne(fetch = FetchType.LAZY)
    private Category parent;

    @OneToOne(fetch = FetchType.LAZY)
    private Attachment image;
    @Column(columnDefinition = "text")
    private String detailsUz;
    @Column(columnDefinition = "text")
    private String detailsRu;
    @Column(columnDefinition = "text")
    private String detailsEng;
    @OrderBy
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int index;
}
