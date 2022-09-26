package kbe.project.product.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
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
    @Operation(summary = "Get all components.")
    ResponseEntity<List<Component>> getComponents() {
        return ResponseEntity.ok(componentService.getAllComponents());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get component by id.")
    ResponseEntity<Component> getComponentById(
            @Parameter(description = "UUID of the component")
            @PathVariable UUID id) {
        return ResponseEntity.ok(componentService.getComponentById(id));
    }

    @PostMapping
    @Operation(summary = "Add a component.")
    Component addComponent(
            @Parameter(description = "Component that needs to be added")
            @RequestBody Component component) {
        return componentService.addComponent(component);
    }
}
