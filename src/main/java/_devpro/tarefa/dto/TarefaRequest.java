package _devpro.tarefa.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TarefaRequest {

	@NotBlank(message = "nome e obrigatorio")
	private String nome;

	@NotBlank(message = "description e obrigatoria")
	private String description;

	@NotNull(message = "isCompleted e obrigatorio")
	private Boolean isCompleted;
}
