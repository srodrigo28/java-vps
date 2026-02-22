package _devpro.tarefa.test;

import _devpro.tarefa.repository.TarefaRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(4)
@RequiredArgsConstructor
public class DeleteTest implements StartupDatabaseTest {

	private static final Logger LOGGER = LoggerFactory.getLogger(DeleteTest.class);

	private final TarefaRepository tarefaRepository;

	@Override
	public String name() {
		return "delete.test";
	}

	@Override
	public void run(TestExecutionContext context) {
		Long id = context.getCreatedTaskId();
		if (id == null) {
			throw new IllegalStateException("[delete.test] create.test nao gerou id para deletar");
		}

		tarefaRepository.deleteById(id);
		context.setCreatedTaskId(null);

		LOGGER.info("[delete.test] Deletado id={}", id);
	}
}
