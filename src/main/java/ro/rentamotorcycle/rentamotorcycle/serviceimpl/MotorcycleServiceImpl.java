package ro.rentamotorcycle.rentamotorcycle.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import ro.rentamotorcycle.rentamotorcycle.dto.MotorcycleDto;
import ro.rentamotorcycle.rentamotorcycle.entities.MotorcycleEntity;
import ro.rentamotorcycle.rentamotorcycle.entities.RatingEntity;
import ro.rentamotorcycle.rentamotorcycle.entities.Role;
import ro.rentamotorcycle.rentamotorcycle.exception.ForbiddenException;
import ro.rentamotorcycle.rentamotorcycle.exception.ResourceNotFoundException;
import ro.rentamotorcycle.rentamotorcycle.mapper.MotorcycleMapper;
import ro.rentamotorcycle.rentamotorcycle.repositories.MotorcycleRepository;
import ro.rentamotorcycle.rentamotorcycle.repositories.RatingRepository;
import ro.rentamotorcycle.rentamotorcycle.service.MotorcycleService;
import org.springframework.security.core.GrantedAuthority;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MotorcycleServiceImpl implements MotorcycleService {

    @Autowired
    private final MotorcycleRepository motorcycleRepository;
    @Autowired
    private final MotorcycleMapper motorcycleMapper;
    @Autowired
    private final RatingRepository ratingRepository;
    @Autowired
    public MotorcycleServiceImpl(MotorcycleRepository motorcycleRepository, RatingRepository ratingRepository,MotorcycleMapper motorcycleMapper) {
        this.motorcycleRepository = motorcycleRepository;
        this.ratingRepository = ratingRepository;
        this.motorcycleMapper = motorcycleMapper;
    }


    @Override
    public MotorcycleEntity createMotorcycle(MotorcycleEntity motorcycleEntity) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        if (!user.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList().contains(Role.ADMIN.name())) {
            throw new ForbiddenException("Only admins can create motorcycles");
        }
       return motorcycleRepository.save(motorcycleEntity);
    }

    @Override
    public MotorcycleEntity getMotorcycleById(Integer id) {
        return motorcycleRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Motorcycle not found with id " + id));
    }
    @Override
    public MotorcycleEntity updateMotorcycle(int id, MotorcycleEntity motorcycleEntity) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        if (!user.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList().contains(Role.ADMIN.name())) {
            throw new ForbiddenException("Only admins can update motorcycles");
        }
        Optional<MotorcycleEntity> optionalMotorcycle = motorcycleRepository.findById(id);
        if(optionalMotorcycle.isPresent()){
            MotorcycleEntity existingMotorcycle = optionalMotorcycle.get();
            existingMotorcycle.setMake(motorcycleEntity.getMake());
            existingMotorcycle.setModel(motorcycleEntity.getModel());
            existingMotorcycle.setYear(motorcycleEntity.getYear());
            existingMotorcycle.setColor(motorcycleEntity.getColor());
            return motorcycleRepository.save(existingMotorcycle);
        }else{
            return null;
        }
    }

    @Override
    public void deleteMotorcycle(Integer id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        if (!user.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList().contains(Role.ADMIN.name())) {
            throw new ForbiddenException("Only admins can delete motorcycles");
        }
        motorcycleRepository.deleteById(id);
    }

    @Override
    public List<MotorcycleEntity> getMotorcycles() {
        return motorcycleRepository.findAll();
    }

    @Override
    public List<MotorcycleEntity> getAvailableMotorcycles(Date pickupTime, Date dropOffTime) {
        return motorcycleRepository.findAllByNotReserved(pickupTime, dropOffTime);
    }

    @Override
    public double getAverageRating(Integer motorcycle) {
        List<RatingEntity> ratingEntities = ratingRepository.findByMotorcycleId(motorcycle);
        if (ratingEntities.isEmpty()) {
            return 0.0;
        }

        double sum = 0.0;
        for (RatingEntity rating : ratingEntities) {
            sum += rating.getRating();
        }

        return sum / ratingEntities.size();
    }

    @Override
    public List<MotorcycleDto> filterMotorcycles(String make, String model, String color, int year) {
        List<MotorcycleEntity> motorcycleEntities;
        if (make != null && model != null && color != null) {
            motorcycleEntities = motorcycleRepository.findByMakeAndModelAndColorAndYear(make, model, color, year);
        } else if (make != null && model != null) {
            motorcycleEntities = motorcycleRepository.findByMakeAndModelAndYear(make, model, year);
        } else if (make != null && color != null) {
            motorcycleEntities = motorcycleRepository.findByMakeAndColorAndYear(make, color, year);
        } else {
            motorcycleEntities = motorcycleRepository.findByYear(year);
        }
        return motorcycleMapper.toDtoList(motorcycleEntities);
    }
}

