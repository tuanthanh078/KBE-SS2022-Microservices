package kbe.project.warehouse.data;

import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface ComponentRepository extends CrudRepository<Component, UUID> {
}
