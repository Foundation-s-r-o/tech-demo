package sk.foundation.techdemo.persons.api;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;

import com.fasterxml.jackson.databind.ObjectMapper;

import sk.foundation.techdemo.BaseIT;

class PersonControllerIT extends BaseIT {

	@Autowired
	ObjectMapper objectMapper;

	@Test
	@Sql({ "/sql/clearAll.sql" })
	void getPersonDetail_notFound() throws Exception {
		mockMvc.perform(get("/api/persons/{id}", 1).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isNotFound());
	}

	@Test
	@Sql({ "/sql/clearAll.sql", "/sql/data.sql" })	
	void getPersonDetail_success() throws Exception {
		mockMvc.perform(get("/api/persons/{id}", 1).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.id").value((1L)))
				.andExpect(jsonPath("$.firstName").value("Admin"))
				.andExpect(jsonPath("$.lastName").value("Admin"))
				.andExpect(jsonPath("$.email").value("admin@tech-demo.sk"));
	}

	@Test
	@Sql({ "/sql/clearAll.sql", "/sql/data.sql" })	
	void getPersonList_success() throws Exception {
		mockMvc.perform(
				get("/api/persons").accept(MediaType.APPLICATION_JSON)
						.param("sortBy", "NAME")
						.param("sortDesc", "false"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.totalElements").value((1L)))
				.andExpect(jsonPath("$.elements[0].id").value((1L)))
				.andExpect(jsonPath("$.elements[0].firstName").value("Admin"))
				.andExpect(jsonPath("$.elements[0].lastName").value("Admin"))
				.andExpect(jsonPath("$.elements[0].email").value("admin@tech-demo.sk"));
	}

	@Test
	@Sql({ "/sql/clearAll.sql", "/sql/data.sql" })	
	void createPerson_success() throws Exception {
		PersonModifyRequestDTO dto = new PersonModifyRequestDTO();
		dto.setFirstName("fn");
		dto.setLastName("ln");
		dto.setEmail("fn@ln.com");
		String content = objectMapper.writer().writeValueAsString(dto);
		mockMvc.perform(
				post("/api/persons").accept(MediaType.APPLICATION_JSON)
						.contentType(MediaType.APPLICATION_JSON)
						.content(content))
				.andExpect(status().isCreated());
	}

	@Test
	@Sql({ "/sql/clearAll.sql", "/sql/data.sql" })	
	void createPerson_conflictingEmail() throws Exception {
		PersonModifyRequestDTO dto = new PersonModifyRequestDTO();
		dto.setFirstName("fn");
		dto.setLastName("ln");
		dto.setEmail("admin@tech-demo.sk");
		String content = objectMapper.writer().writeValueAsString(dto);
		mockMvc.perform(
				post("/api/persons").accept(MediaType.APPLICATION_JSON)
						.contentType(MediaType.APPLICATION_JSON)
						.content(content))
				.andExpect(status().is(409));
	}

}
