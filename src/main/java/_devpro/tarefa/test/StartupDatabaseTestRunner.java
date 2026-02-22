package _devpro.tarefa.test;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(20)
@RequiredArgsConstructor
@ConditionalOnProperty(name = "app.startup-tests.enabled", havingValue = "true", matchIfMissing = true)
public class StartupDatabaseTestRunner implements CommandLineRunner {

	private static final Logger LOGGER = LoggerFactory.getLogger(StartupDatabaseTestRunner.class);

	private final List<StartupDatabaseTest> startupDatabaseTests;

	@Override
	public void run(String... args) throws Exception {
		LOGGER.info("========== INICIO DOS TESTES DE BANCO ==========");
		TestExecutionContext context = new TestExecutionContext();

		for (StartupDatabaseTest startupDatabaseTest : startupDatabaseTests) {
			LOGGER.info("Executando {}", startupDatabaseTest.name());
			startupDatabaseTest.run(context);
		}

		LOGGER.info("========== FIM DOS TESTES DE BANCO ==========");
	}
}
