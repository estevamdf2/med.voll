package med.voll.api.domain.consulta;

import med.voll.api.domain.ValidacaoException;
import med.voll.api.domain.consulta.validacoes.ValidadorAgendamentoDeConsulta;
import med.voll.api.domain.consulta.validacoes.ValidadorCancelamentoDeConsulta;
import med.voll.api.domain.medicos.Medico;
import med.voll.api.domain.medicos.MedicoRepository;
import med.voll.api.domain.paciente.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AgendaDeConsultas {

    @Autowired
    private ConsultaRepository consultaRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private List<ValidadorAgendamentoDeConsulta> validadores;

    @Autowired
    private List<ValidadorCancelamentoDeConsulta> validadoresCancelamento;

    public DadosDetalhamentoConsulta agendar(DadosAgendamentoConsulta dados){
        if(!pacienteRepository.existsById(dados.idPaciente())){
            throw new ValidacaoException("Id do paciente informado não existe!");
        }

        if(dados.idMedico() !=null && !medicoRepository.existsById(dados.idMedico())){
            throw new ValidacaoException("Id do médico informado não existe!");
        }

        validadores.forEach(v -> v.validar(dados));

        var paciente = pacienteRepository.getReferenceById(dados.idPaciente());
        var medico = escolherMedico(dados);

        if(medico == null){
            throw new ValidacaoException("Não existe médico disponível nessa data!");
        }

        var consulta = new Consulta(null, medico, paciente, dados.data());
        consultaRepository.save(consulta);

        return new DadosDetalhamentoConsulta(consulta);
    }

    private Medico escolherMedico(DadosAgendamentoConsulta dados) {
        if (dados.idMedico() !=null){
            return medicoRepository.getReferenceById(dados.idMedico());
        }
        if(dados.especialidade() ==null){
            throw new ValidacaoException("Especialidade é obrigatória quando médico não for escolhido.");
        }
        return medicoRepository.escolherMedicoAleatorioLivreNaData(dados.especialidade(), dados.data());
    }

    public DadosCancelamentoConsulta cancelar(DadosCancelamentoConsulta dados) {
        pacienteExiste(dados.idPaciente());
        medicoExiste(dados.idMedico());

        validadoresCancelamento.forEach(v -> v.validar(dados));

        var paciente = pacienteRepository.getReferenceById(dados.idPaciente());
        var medico = medicoRepository.findById(dados.idMedico()).get();

        var consulta = new Consulta(dados.id(), medico, paciente, dados.data());
        consultaRepository.delete(consulta);

        return new DadosCancelamentoConsulta(consulta, dados.dataCancelamento(), dados.motivo());
    }

    private void pacienteExiste(Long idPaciente) {
        if(!pacienteRepository.existsById(idPaciente)){
            throw new ValidacaoException("Id do paciente informado não existe!");
        }
    }

    private void medicoExiste(Long idMedico){
        if(idMedico !=null && !medicoRepository.existsById(idMedico)){
            throw new ValidacaoException("Id do médico informado não existe!");
        }
    }

}
