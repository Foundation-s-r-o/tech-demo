package sk.foundation.techdemo.infrastructure.api;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PagedRequestDTO {

	@Min(0)
	private int pageStart = 0;

	@Min(1)
	@Max(200)
	private Integer pageSize = 15;

}
