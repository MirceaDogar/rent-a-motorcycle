package ro.rentamotorcycle.rentamotorcycle.controllers;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ro.rentamotorcycle.rentamotorcycle.dto.AccessoryDto;
import ro.rentamotorcycle.rentamotorcycle.entities.AccessoryEntity;
import ro.rentamotorcycle.rentamotorcycle.mapper.AccessoryMapper;
import ro.rentamotorcycle.rentamotorcycle.service.AccessoryService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/accessories")
public class AccessoryController {
    @Autowired
    private final AccessoryService accessoryService;
    @Autowired
    private final AccessoryMapper accessoryMapper;

    public AccessoryController(AccessoryService accessoryService, AccessoryMapper accessoryMapper) {
        this.accessoryService = accessoryService;
        this.accessoryMapper = accessoryMapper;
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    @ApiOperation(value = "Create accessory.")
    public AccessoryDto createAccessory(@RequestBody AccessoryDto accessoryDto) {
        AccessoryEntity accessoryEntity = accessoryMapper.toEntity(accessoryDto);
        AccessoryEntity savedAccessoryEntity = accessoryService.createAccessory(accessoryEntity);
        return accessoryMapper.toDto(savedAccessoryEntity);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Get accessory by Id.")
    public AccessoryDto getAccessoryById(@PathVariable int id) {
        AccessoryEntity accessoryEntity = accessoryService.getAccessoryById(id);
        return accessoryMapper.toDto(accessoryEntity);
    }

    @GetMapping
    @ApiOperation(value = "Get all accessories.")
    public List<AccessoryDto> getAllAccessories() {
        List<AccessoryEntity> accessoryEntities = accessoryService.getAccessories();
        return accessoryEntities.stream()
                .map(accessoryMapper::toDto)
                .collect(Collectors.toList());
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @ApiOperation(value = "Update accessory.")
    public AccessoryDto updateAccessory(@PathVariable int id, @RequestBody AccessoryDto accessoryDto) {
        AccessoryEntity accessoryEntity = accessoryMapper.toEntity(accessoryDto);
        AccessoryEntity updatedAccessoryEntity = accessoryService.updateAccessory(id, accessoryEntity);
        return accessoryMapper.toDto(updatedAccessoryEntity);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @ApiOperation(value = "Delete accessory.")
    public void deleteAccessory(@PathVariable int id) {
        accessoryService.deleteAccessory(id);
    }
}