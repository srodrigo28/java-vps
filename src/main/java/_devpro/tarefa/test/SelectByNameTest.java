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
@Order(6)
@RequiredArgsConstructor
public class SelectByNameTest implements StartupDatabaseTest {

	private static final Logger LOGGER = LoggerFactory.getLogger(SelectByNameTest.class);

	private final TarefaRepository tarefaRepository;

	@Override
	public String name() {
		return "select-by-name.test";
	}

	@Override
	public void run(TestExecutionContext context) {
		String nomeParaBusca = "Revisao";
		List<Tarefa> tarefas = tarefaRepository.findByNomeContainingIgnoreCase(nomeParaBusca);
		LOGGER.info("[select-by-name.test] Nome='{}' -> encontrados={}", nomeParaBusca, tarefas.size());
	}
}
