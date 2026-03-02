package com.dealer.dto;

import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DealerRequestDTO {

    @NotBlank
    private String dealerName;

    @NotBlank
    private String address;

    private String contactNumber;

    @Email
    private String email;
}