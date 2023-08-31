package med.voll.api.domain.consulta.validacoes;

import med.voll.api.domain.consulta.DadosCancelamentoConsulta;
import org.springframework.stereotype.Repository;

@Repository
public interface ValidadorCancelamentoDeConsulta {

    void validar(DadosCancelamentoConsulta dados);
}
