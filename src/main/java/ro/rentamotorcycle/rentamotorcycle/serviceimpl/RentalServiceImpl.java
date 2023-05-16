package ro.rentamotorcycle.rentamotorcycle.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import ro.rentamotorcycle.rentamotorcycle.dto.RentalDto;
import ro.rentamotorcycle.rentamotorcycle.entities.RentalEntity;
import ro.rentamotorcycle.rentamotorcycle.exception.ResourceNotFoundException;
import ro.rentamotorcycle.rentamotorcycle.mapper.RentalMapper;
import ro.rentamotorcycle.rentamotorcycle.repositories.RentalRepository;
import ro.rentamotorcycle.rentamotorcycle.service.RentalService;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
@Service
public class RentalServiceImpl implements RentalService {
    @Autowired
    private final RentalRepository rentalRepository;
    @Autowired
    private final RentalMapper rentalMapper;
    @Autowired
    public RentalServiceImpl(RentalRepository rentalRepository, RentalMapper rentalMapper) {
        this.rentalRepository = rentalRepository;
        this.rentalMapper = rentalMapper;
    }

    @Override
    public RentalEntity createRental(RentalEntity rentalEntity) {
        return rentalRepository.save(rentalEntity);
    }

    @Override
    public RentalEntity getRentalById(Integer id) {
        return rentalRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Rental not found with id " + id));
    }

    @Override
    public RentalEntity updateRental(int id, RentalEntity rentalEntity) {
        Optional<RentalEntity> optionalRental = rentalRepository.findById(id);
        if(optionalRental.isPresent()){
            RentalEntity existingRental = optionalRental.get();
            existingRental.setUser(rentalEntity.getUser());
            existingRental.setMotorcycle(rentalEntity.getMotorcycle());
            existingRental.setPickupDate(rentalEntity.getPickupDate());
            existingRental.setDropoffDate(rentalEntity.getDropoffDate());
            existingRental.setPickupLocation(rentalEntity.getPickupLocation());
            existingRental.setDropoffLocation(rentalEntity.getDropoffLocation());
            return rentalRepository.save(existingRental);
        }else{
            return null;
        }
    }

    @Override
    public void deleteRental(Integer id) {
        rentalRepository.deleteById(id);
    }

    @Override
    public List<RentalEntity> getRentals() {
        return rentalRepository.findAll();
    }

    @Override
    @Scheduled(fixedRate = 86400000)
    public void updateRentalStatus() {
        List<RentalEntity> rentals = rentalRepository.findAll();
        LocalDate currentTime = LocalDate.now();
        for (RentalEntity rental : rentals) {
            LocalDate pickupTime = rental.getPickupDate();
            LocalDate dropoffDate = rental.getDropoffDate();
            if (currentTime.isAfter(pickupTime) && currentTime.isBefore(dropoffDate)) {
                rental.getMotorcycle().setIsAvailable(false);
                rentalRepository.save(rental);
            }
        }

    }

    @Override
    public RentalDto createRentalWithLocations(RentalDto rentalDto) {
        RentalEntity rentalEntity = rentalMapper.toEntity(rentalDto);
        rentalEntity.setPickupLocation(rentalDto.getPickupLocation());
        rentalEntity.setDropoffLocation(rentalDto.getDropoffLocation());
        RentalEntity savedRentalEntity = rentalRepository.save(rentalEntity);
        return rentalMapper.toDto(savedRentalEntity);
    }


}
