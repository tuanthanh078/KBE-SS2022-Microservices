package kbe.project.product.service;

import kbe.project.product.model.Component;

import java.util.List;
import java.util.UUID;

public interface ComponentService {
    Component getComponentById(UUID id);
    List<Component> getAllComponents();
    Component addComponent(Component component);
}
