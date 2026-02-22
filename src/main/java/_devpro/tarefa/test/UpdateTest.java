package _devpro.tarefa.test;

import _devpro.tarefa.model.Tarefa;
import _devpro.tarefa.repository.TarefaRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(3)
@RequiredArgsConstructor
public class UpdateTest implements StartupDatabaseTest {

	private static final Logger LOGGER = LoggerFactory.getLogger(UpdateTest.class);

	private final TarefaRepository tarefaRepository;

	@Override
	public String name() {
		return "update.test";
	}

	@Override
	public void run(TestExecutionContext context) {
		Long id = context.getCreatedTaskId();
		if (id == null) {
			throw new IllegalStateException("[update.test] create.test nao gerou id para atualizar");
		}

		Tarefa tarefa = tarefaRepository.findById(id)
				.orElseThrow(() -> new IllegalStateException("[update.test] tarefa nao encontrada para id=" + id));

		tarefa.setDescription("Registro atualizado pelo update.test");
		tarefa.setIsCompleted(true);
		tarefaRepository.save(tarefa);

		LOGGER.info("[update.test] Atualizado id={}, isCompleted={}", tarefa.getId(), tarefa.getIsCompleted());
	}
}
