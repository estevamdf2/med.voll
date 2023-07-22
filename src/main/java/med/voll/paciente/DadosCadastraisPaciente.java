package med.voll.paciente;

import med.voll.endereco.DadosEndereco;

public record DadosCadastraisPaciente(String nome, String email, String telefone, String cpf, DadosEndereco endereco) {
}
