package _devpro.tarefa.test;

import _devpro.tarefa.model.Tarefa;
import _devpro.tarefa.repository.TarefaRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(2)
@RequiredArgsConstructor
public class CreateTest implements StartupDatabaseTest {

	private static final Logger LOGGER = LoggerFactory.getLogger(CreateTest.class);

	private final TarefaRepository tarefaRepository;

	@Override
	public String name() {
		return "create.test";
	}

	@Override
	public void run(TestExecutionContext context) {
		String nome = "TESTE_STARTUP_" + System.currentTimeMillis();
		Tarefa novaTarefa = Tarefa.builder()
				.nome(nome)
				.description("Registro criado pelo create.test")
				.isCompleted(false)
				.build();

		Tarefa saved = tarefaRepository.save(novaTarefa);
		context.setCreatedTaskId(saved.getId());
		context.setCreatedTaskName(saved.getNome());

		LOGGER.info("[create.test] Inserido id={}, nome={}", saved.getId(), saved.getNome());
	}
}
