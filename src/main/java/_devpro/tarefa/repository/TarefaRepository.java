package _devpro.tarefa.repository;

import _devpro.tarefa.model.Tarefa;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TarefaRepository extends JpaRepository<Tarefa, Long> {

	List<Tarefa> findByNomeContainingIgnoreCase(String nome);
}
