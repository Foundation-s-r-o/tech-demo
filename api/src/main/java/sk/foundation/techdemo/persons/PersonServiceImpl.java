package sk.foundation.techdemo.persons;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PersonServiceImpl implements PersonService {

	private final PersonRepository personRepository;

	@Override
	@Transactional
	public void save(Person person) {
		personRepository.save(person);
	}

	@Override
	@Transactional
	public void deleteById(Long id) {
		personRepository.deleteById(id);
	}
}
