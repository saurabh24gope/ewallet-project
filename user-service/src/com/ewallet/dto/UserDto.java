package com.ewallet.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {

    private Long id;
    @NotNull
    @Size(max=255)
    private  String name;
    @NotNull
    @Size(max=255)
    private String email;
    @NotNull
    @Size(max=255)
    private String phone;
    @NotNull
    @Size(max=255)
    private String kycNumber;
}
