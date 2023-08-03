package med.voll.api.paciente;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PacienteRepository extends JpaRepository<Paciente, Long> {
    /**
     * Recurso padrão do Spring data de nomenclatura.
     * Ao criar um método seguindo este padrão ele consegue
     * montar a query com o canteúdo desejado.
     *
     * Neste caso ele irá montar a query da coluna Ativo que e
     * um boolean retornando somente os registros ativos.
     * @param pageable
     * @return
     */
    Page<Paciente> findAllByAtivoTrue(Pageable pageable);
}
