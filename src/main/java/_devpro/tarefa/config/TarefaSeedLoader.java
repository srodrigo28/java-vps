package _devpro.tarefa.config;

import _devpro.tarefa.model.Tarefa;
import _devpro.tarefa.repository.TarefaRepository;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import tools.jackson.databind.ObjectMapper;

@Component
@Order(10)
@RequiredArgsConstructor
public class TarefaSeedLoader implements CommandLineRunner {

	private static final String CLASSPATH_SEED_FILE = "seed/banco.json";
	private static final Path LOCAL_SEED_FILE = Path.of("tarefas", "banco.json");

	private final TarefaRepository tarefaRepository;
	private final ObjectMapper objectMapper;

	@Override
	public void run(String... args) {
		if (tarefaRepository.count() > 0) {
			return;
		}

		try (InputStream seedStream = openSeedStream()) {
			if (seedStream == null) {
				return;
			}

			BancoSeed bancoSeed = objectMapper.readValue(seedStream, BancoSeed.class);
			if (bancoSeed == null || bancoSeed.getTasks() == null || bancoSeed.getTasks().isEmpty()) {
				return;
			}

			List<Tarefa> tarefas = bancoSeed.getTasks().stream()
					.map(item -> Tarefa.builder()
							.nome(item.getNome())
							.description(item.getDescription())
							.isCompleted(Boolean.TRUE.equals(item.getIsCompleted()))
							.build())
					.toList();

			tarefaRepository.saveAll(tarefas);
		} catch (IOException ex) {
			throw new IllegalStateException("Erro ao carregar seed de tarefas", ex);
		}
	}

	private InputStream openSeedStream() throws IOException {
		ClassPathResource classPathResource = new ClassPathResource(CLASSPATH_SEED_FILE);
		if (classPathResource.exists()) {
			return classPathResource.getInputStream();
		}

		if (Files.exists(LOCAL_SEED_FILE)) {
			return Files.newInputStream(LOCAL_SEED_FILE);
		}

		return null;
	}

	@Data
	public static class BancoSeed {
		private List<TarefaSeed> tasks;
	}

	@Data
	public static class TarefaSeed {
		private Long id;
		private String nome;
		private String description;
		private Boolean isCompleted;
	}
}
