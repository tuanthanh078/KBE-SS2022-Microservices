package kbe.project.product.controller;

import kbe.project.product.model.Component;
import kbe.project.product.service.ComponentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping(path="/components")
public class ComponentController {

    private final ComponentService componentService;

    @GetMapping
    ResponseEntity<List<Component>> getProducts() {
        return ResponseEntity.ok(componentService.getAllComponents());
    }

    @GetMapping("/{id}")
    ResponseEntity<Component> getProductById(@PathVariable UUID id) {
        return ResponseEntity.ok(componentService.getComponentById(id));
    }

    @PostMapping
    Component addComponent(@RequestBody Component component) {
        return componentService.addComponent(component);
    }
}
