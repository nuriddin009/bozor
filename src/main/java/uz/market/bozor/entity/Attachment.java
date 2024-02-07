package uz.market.bozor.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;
import uz.market.bozor.entity.template.BaseEntity;

@Getter
@Setter
@Builder
@SQLDelete(sql = "update attachment set deleted = true where id=?")
@SQLRestriction(value = "deleted = false")
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Attachment extends BaseEntity {

    @Column(name = "object_name")
    private String objectName;

    @Column(name = "content_type")
    private String contentType;

    @Column(name = "size")
    private Long size;

    @Column(name = "url", columnDefinition = "varchar")
    private String url;


    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

}
