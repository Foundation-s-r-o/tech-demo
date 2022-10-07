package sk.foundation.techdemo.persons.api;

import java.util.List;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;

import org.hibernate.exception.ConstraintViolationException;
import org.slf4j.Logger;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import sk.foundation.techdemo.infrastructure.api.ConflictException;
import sk.foundation.techdemo.infrastructure.api.PagedRequestDTO;
import sk.foundation.techdemo.infrastructure.api.PagedResultResponseDTO;
import sk.foundation.techdemo.persons.Person;
import sk.foundation.techdemo.persons.PersonRepository;
import sk.foundation.techdemo.persons.PersonService;

@Service
@RequiredArgsConstructor
public class PersonApiServiceImpl implements PersonApiService {

	private final PersonRepository personRepository;
	private final PersonService personService;
	private final Logger log;

	@Override
	@Transactional(readOnly = true)
	public PagedResultResponseDTO<PersonListItemResponseDTO> listPersons(
			PersonFilter filter,
			PagedRequestDTO paging,
			PersonSortRequestDTO sorting) {
		List<PersonListItemResponseDTO> elements = personRepository.listPersons(filter, paging, sorting);
		Long totalCount = personRepository.getTotalPersons(filter);
		return new PagedResultResponseDTO<>(totalCount, elements);
	}

	@Override
	@Transactional(readOnly = true)
	public PersonDetailResponseDTO getPerson(Long id) {
		PersonDetailResponseDTO dto = personRepository.getPersonDetail(id);
		if (dto == null) {
			throw new EntityNotFoundException("Person with id=" + id + " not found");
		} else {
			return dto;
		}
	}

	@Override
	public IdResponseDTO<Long> create(@Valid PersonModifyRequestDTO dto) {
		Person person = new Person();

		updateFromDTO(dto, person);

		try {
			personService.save(person);
		} catch (DataIntegrityViolationException e) {
			if (e.getCause() instanceof ConstraintViolationException cve) {
				if (cve.getConstraintName().contains(Person.UK_EMAIL)) {
					throw new ConflictException("Person with same email already exists", e);
				}
			}
			log.error("Error while creating person", e);
			throw e;
		}

		return new IdResponseDTO<>(person.getId());
	}

	@Override
	public void update(Long id, @Valid PersonModifyRequestDTO dto) {
		Person person = personRepository.getReferenceById(id);

		updateFromDTO(dto, person);

		try {
			personService.save(person);
		} catch (DataIntegrityViolationException e) {
			if (e.getCause() instanceof ConstraintViolationException cve) {
				if (cve.getConstraintName().contains(Person.UK_EMAIL)) {
					throw new ConflictException("Person with same email already exists", e);
				}
			}
			log.error("Error while creating person", e);
			throw e;
		}
	}

	private void updateFromDTO(PersonModifyRequestDTO dto, Person person) {
		person.setFirstName(dto.getFirstName());
		person.setLastName(dto.getLastName());
		person.setEmail(dto.getEmail());
		person.setAddress(dto.getAddress());
		person.setState(dto.getState());
		person.setPhoneNumber(dto.getPhoneNumber());
	}

	@Override
	public void delete(Long id) {
		personService.deleteById(id);
	}

}
