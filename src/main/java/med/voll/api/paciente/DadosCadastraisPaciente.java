package med.voll.api.paciente;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import med.voll.api.endereco.DadosEndereco;
import org.hibernate.validator.constraints.br.CPF;

public record DadosCadastraisPaciente(
        @NotBlank
        String nome,
        @NotBlank
        @Email
        String email,
        @NotBlank
        @Pattern(regexp = "\\d{10,11}")
        String telefone,
        @NotBlank
        @CPF
        String cpf,
        @NotNull
        @Valid
        DadosEndereco endereco) {
}
