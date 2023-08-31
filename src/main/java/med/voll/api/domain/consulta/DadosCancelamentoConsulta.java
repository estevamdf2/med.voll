package med.voll.api.domain.consulta;

import jakarta.validation.constraints.NotNull;
import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;

public record DadosCancelamentoConsulta(Long id, @NotNull Long idPaciente, @NotNull Long idMedico, @NotNull
                                        LocalDateTime data, LocalDateTime dataCancelamento, @NotNull String motivo) {

    public DadosCancelamentoConsulta(Consulta consulta, LocalDateTime dataCancelamento, String motivoCancelamento){
        this(consulta.getId(), consulta.getPaciente().getId(), consulta.getMedico().getId(), consulta.getData(), dataCancelamento , motivoCancelamento);
    }
}
