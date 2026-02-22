package _devpro.tarefa.controller;

import _devpro.tarefa.dto.TarefaRequest;
import _devpro.tarefa.model.Tarefa;
import _devpro.tarefa.service.TarefaService;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/tarefas")
@RequiredArgsConstructor
public class TarefaController {

	private final TarefaService tarefaService;

	@GetMapping
	public List<Tarefa> listar(@RequestParam(required = false) String nome) {
		if (nome == null || nome.isBlank()) {
			return tarefaService.listarTodas();
		}
		return tarefaService.buscarPorNome(nome);
	}

	@GetMapping("/{id}")
	public Tarefa buscarPorId(@PathVariable Long id) {
		return tarefaService.buscarPorId(id);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Tarefa criar(@Valid @RequestBody TarefaRequest request) {
		return tarefaService.criar(request);
	}

	@PutMapping("/{id}")
	public Tarefa atualizar(@PathVariable Long id, @Valid @RequestBody TarefaRequest request) {
		return tarefaService.atualizar(id, request);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long id) {
		tarefaService.remover(id);
	}
}
