package med.voll.api.controller;

import med.voll.api.paciente.DadosCadastraisPaciente;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {
    @GetMapping
    public String listar(){
        return "listagem";
    }

    @PostMapping
    public void cadastrar(@RequestBody DadosCadastraisPaciente dadosPaciente){
        System.out.println(dadosPaciente);
    }
}
