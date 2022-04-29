package kbe.project.product.service;

import kbe.project.product.model.Hardware;
import kbe.project.product.repository.HardwareRepository;
import kbe.project.product.service.exception.HardwareNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class HardwareServiceImp implements HardwareService {

    private final HardwareRepository hardwareRepository;

    @Override
    public Hardware getHardwareById(Long id) {
        return hardwareRepository.findById(id).orElseThrow(() -> new HardwareNotFoundException(id));
    }

    @Override
    public List<Hardware> getAllHardwares() {
        return (List<Hardware>) hardwareRepository.findAll();
    }

    @Override
    public Hardware addHardware(Hardware hardware) {
        return hardwareRepository.save(hardware);
    }
}
