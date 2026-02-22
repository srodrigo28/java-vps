package _devpro.tarefa.service;

import _devpro.tarefa.dto.TarefaRequest;
import _devpro.tarefa.exception.ResourceNotFoundException;
import _devpro.tarefa.model.Tarefa;
import _devpro.tarefa.repository.TarefaRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TarefaService {

	private final TarefaRepository tarefaRepository;

	@Transactional(readOnly = true)
	public List<Tarefa> listarTodas() {
		return tarefaRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
	}

	@Transactional(readOnly = true)
	public List<Tarefa> buscarPorNome(String nome) {
		return tarefaRepository.findByNomeContainingIgnoreCase(nome);
	}

	@Transactional(readOnly = true)
	public Tarefa buscarPorId(Long id) {
		return tarefaRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Tarefa nao encontrada para id: " + id));
	}

	@Transactional
	public Tarefa criar(TarefaRequest request) {
		Tarefa tarefa = Tarefa.builder()
				.nome(request.getNome())
				.description(request.getDescription())
				.isCompleted(Boolean.TRUE.equals(request.getIsCompleted()))
				.build();
		return tarefaRepository.save(tarefa);
	}

	@Transactional
	public Tarefa atualizar(Long id, TarefaRequest request) {
		Tarefa tarefa = buscarPorId(id);
		tarefa.setNome(request.getNome());
		tarefa.setDescription(request.getDescription());
		tarefa.setIsCompleted(Boolean.TRUE.equals(request.getIsCompleted()));
		return tarefaRepository.save(tarefa);
	}

	@Transactional
	public void remover(Long id) {
		Tarefa tarefa = buscarPorId(id);
		tarefaRepository.delete(tarefa);
	}
}
