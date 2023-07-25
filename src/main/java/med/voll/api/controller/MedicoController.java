package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.medicos.DadosCadastraisMedico;
import med.voll.api.medicos.Medico;
import med.voll.api.medicos.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/medicos")
public class MedicoController {

    @Autowired(required = true)
    private MedicoRepository repository;
    @PostMapping
    public void cadastrar(@RequestBody @Valid DadosCadastraisMedico dadosMedico){
        repository.save(new Medico(dadosMedico));
//        System.out.println(dadosMedico);
    }
}
