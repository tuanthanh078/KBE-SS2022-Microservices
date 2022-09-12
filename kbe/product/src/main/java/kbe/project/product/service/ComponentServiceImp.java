package kbe.project.product.service;

import kbe.project.product.model.Component;
import kbe.project.product.repository.ComponentRepository;
import kbe.project.product.service.exception.ComponentNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class ComponentServiceImp implements ComponentService {

    private final ComponentRepository componentRepository;

    @Override
    public Component getComponentById(Long id) {
        return componentRepository.findById(id).orElseThrow(() -> new ComponentNotFoundException(id));
    }

    @Override
    public List<Component> getAllComponents() {
        return (List<Component>) componentRepository.findAll();
    }

    @Override
    public Component addComponent(Component component) {
        return componentRepository.save(component);
    }
}
