package bessa.morangon.rafael.clinica.configuration.security;

import lombok.Data;
@Data
public class TokenDto {
    private String token;
    private String tipo = "Bearer";

    public TokenDto(String token){
        this.token = token;
    }

}
