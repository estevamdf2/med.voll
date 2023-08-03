package med.voll.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.medicos.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/medicos")
public class MedicoController {

    @Autowired(required = true)
    private MedicoRepository repository;
    @PostMapping
    @Transactional
    public void cadastrar(@RequestBody @Valid DadosCadastraisMedico dadosMedico){
        repository.save(new Medico(dadosMedico));

    }
    @GetMapping
    public Page<DadosListagemMedicos> listar(@PageableDefault(size = 10, sort = {"nome"}) Pageable pageable){
        return repository.findAllByAtivoTrue(pageable).map(DadosListagemMedicos::new);
    }
    @PutMapping
    @Transactional
    public ResponseEntity atualizar(@RequestBody @Valid DadosAtualizacaoMedico dados){
        var medico = repository.getReferenceById(dados.id());
        medico.atualizarInformacoes(dados);

        return ResponseEntity.ok(new DadosDetalhamentoMedico(medico));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity excluir(@PathVariable Long id){
        var medico = repository.getReferenceById(id);
        medico.excluir();
        return ResponseEntity.noContent().build();
    }
}
