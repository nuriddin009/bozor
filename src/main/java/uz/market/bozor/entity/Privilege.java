package uz.market.bozor.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;
import uz.market.bozor.entity.constants.PrivilegeName;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Privilege {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    private PrivilegeName privilegeName;

    public Privilege(PrivilegeName privilegeName) {
        this.privilegeName = privilegeName;
    }
}
