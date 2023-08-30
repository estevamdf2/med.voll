package med.voll.api.domain.paciente;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

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

    @Query("""
            select p.ativo from Paciente p
            where
            p.id = :id
            """)
    Boolean findAtivoById(Long id);
}
