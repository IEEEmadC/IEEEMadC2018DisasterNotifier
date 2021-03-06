package gr.teicm.ieee.madc.disasternotifierservice.mapper;

import gr.teicm.ieee.madc.disasternotifierservice.domain.entity.BaseEntity;
import gr.teicm.ieee.madc.disasternotifierservice.dto.Reference;
import org.mapstruct.TargetType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import java.util.Objects;
import java.util.Optional;

@Component
public class ReferenceMapper {

    private final EntityManager entityManager;

    @Autowired
    public ReferenceMapper(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public <T extends BaseEntity> T resolve(final Reference reference, @TargetType final Class<T> entityClass) {
        if (Objects.nonNull(reference)) {
            final Optional<T> entityOptional = Optional.ofNullable(entityManager.find(entityClass, reference.getId()));

            return entityOptional.orElseThrow(() -> new EntityNotFoundException(entityClass + "id" + reference.getId()));
        } else {
            return null;
        }
    }

    public Reference toReference(final BaseEntity entity) {
        if (Objects.nonNull(entity)) {
            final Reference reference = new Reference();

            reference.setId(entity.getId());

            return reference;
        } else {
            return null;
        }
    }
}