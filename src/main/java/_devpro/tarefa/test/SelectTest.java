package _devpro.tarefa.test;

import _devpro.tarefa.model.Tarefa;
import _devpro.tarefa.repository.TarefaRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(5)
@RequiredArgsConstructor
public class SelectTest implements StartupDatabaseTest {

	private static final Logger LOGGER = LoggerFactory.getLogger(SelectTest.class);

	private final TarefaRepository tarefaRepository;

	@Override
	public String name() {
		return "select.test";
	}

	@Override
	public void run(TestExecutionContext context) {
		List<Tarefa> tarefas = tarefaRepository.findAll();
		LOGGER.info("[select.test] Total de tarefas encontradas={}", tarefas.size());
	}
}
