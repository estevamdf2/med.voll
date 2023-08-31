package med.voll.api.domain.consulta.validacoes;

import med.voll.api.domain.ValidacaoException;
import med.voll.api.domain.consulta.DadosCancelamentoConsulta;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component
public class ValidadorAntecedenciaParaCancelamento implements ValidadorCancelamentoDeConsulta {
    @Override
    public void validar(DadosCancelamentoConsulta dados) {
        var dataConsulta = dados.data();
        var dataCancelamento = dados.dataCancelamento();

        var validaData = dataCancelamento.isBefore(dataConsulta);

        Duration duracao = Duration.between(dataCancelamento, dataConsulta);
        Long diferencaHoras = duracao.toHours();

        if(!validaData){
            throw new ValidacaoException("Uma consulta somente poderá ser cancelada com antecedência mínima de 24 horas.");
        }

        if(validaData && diferencaHoras < 24){
                throw new ValidacaoException("Uma consulta somente poderá ser cancelada com antecedência mínima de 24 horas.");
        }

    }
}
