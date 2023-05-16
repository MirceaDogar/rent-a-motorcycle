package ro.rentamotorcycle.rentamotorcycle.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegistrationRequest {
    private String fullName;
    private String phoneNumber;
    private String email;
    private String password;
}
