package kbe.project.product.controller;

import kbe.project.product.model.Hardware;
import kbe.project.product.service.HardwareService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping(path="/hardwares")
public class HardwareController {

    private final HardwareService hardwareService;

    @GetMapping
    ResponseEntity<List<Hardware>> getProducts() {
        return ResponseEntity.ok(hardwareService.getAllHardwares());
    }

    @GetMapping("/{id}")
    ResponseEntity<Hardware> getProductById(@PathVariable Long id) {
        return ResponseEntity.ok(hardwareService.getHardwareById(id));
    }

    @PostMapping
    Hardware addHardware(@RequestBody Hardware hardware) {
        return hardwareService.addHardware(hardware);
    }
}
