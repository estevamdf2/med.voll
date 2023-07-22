package med.voll.medicos;

import med.voll.endereco.DadosEndereco;

public record DadosCadastraisMedico(String nome, String email, String telefone, String crm, Especialidade especialidade, DadosEndereco endereco) {
}
