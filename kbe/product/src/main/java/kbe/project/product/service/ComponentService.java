package kbe.project.product.service;

import kbe.project.product.model.Component;

import java.util.List;

public interface ComponentService {
    Component getComponentById(Long id);
    List<Component> getAllComponents();
    Component addComponent(Component component);
}
