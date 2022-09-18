package kbe.project.warehouse.repository;

import kbe.project.warehouse.model.Component;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface ComponentRepository extends CrudRepository<Component, UUID> {
}
