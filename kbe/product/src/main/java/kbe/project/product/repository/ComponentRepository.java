package kbe.project.product.repository;

import kbe.project.product.model.Component;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface ComponentRepository extends CrudRepository<Component, UUID> {
}
