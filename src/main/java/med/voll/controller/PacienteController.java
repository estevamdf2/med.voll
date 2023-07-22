package med.voll.controller;

import med.voll.paciente.DadosCadastraisPaciente;
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
