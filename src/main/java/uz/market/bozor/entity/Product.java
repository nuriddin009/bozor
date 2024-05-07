package uz.market.bozor.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;
import uz.market.bozor.entity.template.BaseEntity;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@SQLDelete(sql = "update product set deleted=true where id=?")
@SQLRestriction(value = "deleted=false")
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Product extends BaseEntity {
    @Column(nullable = false)
    private String nameUz;
    @Column(nullable = false)
    private String nameRu;
    @Column(nullable = false)
    private String nameEng;
    @PositiveOrZero
    @Column(nullable = false)
    private BigDecimal price;
    @PositiveOrZero
    @Column(nullable = false)
    private BigDecimal oldPrice;

    @Column(columnDefinition = "text")
    private String detailsUz;
    @Column(columnDefinition = "text")
    private String detailsRu;
    @Column(columnDefinition = "text")
    private String detailsEng;

    @Positive
    private float rating;

    @ElementCollection
    private List<String> colors;

    @PositiveOrZero
    private BigDecimal sellCount = BigDecimal.ZERO;

    @JoinTable(
            name = "product_category",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE,
            CascadeType.PERSIST, CascadeType.REFRESH})
    private List<Category> categories;
    @JoinTable(
            name = "product_image",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE,
            CascadeType.PERSIST, CascadeType.REFRESH})
    private List<Attachment> images;

}
