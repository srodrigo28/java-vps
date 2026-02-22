package _devpro.tarefa.test;

import java.sql.Connection;
import javax.sql.DataSource;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(1)
@RequiredArgsConstructor
public class ConnTest implements StartupDatabaseTest {

	private static final Logger LOGGER = LoggerFactory.getLogger(ConnTest.class);

	private final DataSource dataSource;

	@Override
	public String name() {
		return "conn.test";
	}

	@Override
	public void run(TestExecutionContext context) throws Exception {
		try (Connection connection = dataSource.getConnection()) {
			LOGGER.info("[conn.test] Conexao OK -> url={}, user={}",
					connection.getMetaData().getURL(),
					connection.getMetaData().getUserName());
		}
	}
}
