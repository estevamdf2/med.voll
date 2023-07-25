package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.paciente.DadosCadastraisPaciente;
import med.voll.api.paciente.DadosListagemPaciente;
import med.voll.api.paciente.Paciente;
import med.voll.api.paciente.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {

    @Autowired
    private PacienteRepository repository;
    @GetMapping
    public List<DadosListagemPaciente> listar(){
        System.out.println("lista ae");
        return repository.findAll().stream().map(DadosListagemPaciente::new).toList();

    }

    @PostMapping
    public void cadastrar(@RequestBody @Valid DadosCadastraisPaciente dadosPaciente){
        repository.save(new Paciente(dadosPaciente));
    }
}
