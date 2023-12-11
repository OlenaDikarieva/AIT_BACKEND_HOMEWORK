package org.demointernetshop.services;

import lombok.RequiredArgsConstructor;
import org.demointernetshop.dto.product.ManufacturerDto;
import org.demointernetshop.entity.Manufacturer;
import org.demointernetshop.repository.ManufacturerRepository;
import org.demointernetshop.services.utils.Converters;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ManufacturerService {

    private final ManufacturerRepository manufacturerRepository;

    public List<ManufacturerDto> getAllManufacturer() {
        List<Manufacturer> manufacturers = manufacturerRepository.findAll();
        return manufacturers.stream()
                .map(Converters::manufacturerToDto)
                .collect(Collectors.toList());
    }
}