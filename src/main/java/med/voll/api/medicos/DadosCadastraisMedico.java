package med.voll.api.medicos;

import med.voll.api.endereco.DadosEndereco;

public record DadosCadastraisMedico(String nome, String email, String telefone, String crm, Especialidade especialidade, DadosEndereco endereco) {
}
