package med.voll.api.domain.consulta;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import med.voll.api.domain.medicos.Especialidade;

import java.time.LocalDateTime;

public record DadosDetalhamentoConsulta(Long id, Long idMedico, @NotNull Long idPaciente, @NotNull @Future LocalDateTime data) {
    public DadosDetalhamentoConsulta(Consulta consulta) {
        this(consulta.getId(),  consulta.getPaciente().getId(), consulta.getMedico().getId(), consulta.getData());
    }
}
