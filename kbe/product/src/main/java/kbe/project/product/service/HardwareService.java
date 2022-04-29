package kbe.project.product.service;

import kbe.project.product.model.Hardware;

import java.util.List;

public interface HardwareService {
    Hardware getHardwareById(Long id);
    List<Hardware> getAllHardwares();
    Hardware addHardware(Hardware hardware);
}
