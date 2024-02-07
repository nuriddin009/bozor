package uz.market.bozor.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;
import uz.market.bozor.entity.template.BaseEntity;

import java.util.List;
import java.util.UUID;

@NoRepositoryBean
public interface BaseRepository<T extends BaseEntity> extends JpaRepository<T, UUID>, JpaSpecificationExecutor<T> {
    T findByIdAndDeletedFalse(UUID id);
    T trash(UUID id);
    List<T> trashList(List<UUID> ids);
    List<T> findAllNotDeleted();
    Page<T> findAllNotDeleted(Pageable pageable);
}
