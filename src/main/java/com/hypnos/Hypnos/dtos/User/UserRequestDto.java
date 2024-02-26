package com.hypnos.Hypnos.dtos.User;
//Contiene los datos que el usuario envía al servidor como parte de una solicitud.
import com.hypnos.Hypnos.models.user.Role;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@NoArgsConstructor(force = true)
@RequiredArgsConstructor
@Builder
public class UserRequestDto {
    private final String firstname;
    private final String lastname;
    private final String email;
    private final String password;
    private final Role role;
}
