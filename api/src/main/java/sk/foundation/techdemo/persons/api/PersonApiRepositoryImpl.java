package sk.foundation.techdemo.persons.api;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.From;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import sk.foundation.techdemo.infrastructure.api.PagedRequestDTO;
import sk.foundation.techdemo.infrastructure.db.IdentifiableEntity_;
import sk.foundation.techdemo.persons.Person;
import sk.foundation.techdemo.persons.Person_;

public class PersonApiRepositoryImpl implements PersonApiRepository {

	@PersistenceContext
	private EntityManager em;

	@Override
	public List<PersonListItemResponseDTO> listPersons(
			PersonFilter filter,
			PagedRequestDTO paging,
			PersonSortRequestDTO sorting) {

		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<PersonListItemResponseDTO> cq = cb.createQuery(PersonListItemResponseDTO.class);
		Root<Person> person = cq.from(Person.class);

		List<Predicate> wherePredicates = new ArrayList<>();
		addWherePredicatesFromFilter(filter, cb, person, wherePredicates);

		setOrderBy(sorting, cb, cq, person);

		cq.select(
				cb.construct(
						PersonListItemResponseDTO.class,
						person.get(IdentifiableEntity_.ID),
						person.get(Person_.FIRST_NAME),
						person.get(Person_.LAST_NAME),
						person.get(Person_.EMAIL)));
		cq.where(wherePredicates.toArray(new Predicate[0]));

		TypedQuery<PersonListItemResponseDTO> typedQuery = em.createQuery(cq);
		if (paging.getPageSize() != null) {
			typedQuery.setMaxResults(paging.getPageSize());
			typedQuery.setFirstResult(paging.getPageStart() * paging.getPageSize());
		} else {
			typedQuery.setFirstResult(paging.getPageStart());
		}

		return typedQuery.getResultList();
	}

	private void setOrderBy(PersonSortRequestDTO sorting, CriteriaBuilder cb, CriteriaQuery<?> cq, From<?, ?> person) {
		List<Expression<?>> sortByPath = new ArrayList<>(2);
		if (sorting == null || sorting.getSortBy() == null) {
			return;
		}

		switch (sorting.getSortBy()) {
			case ID -> sortByPath.add(person.get(Person_.ID));
			case FIRST_NAME -> sortByPath.add(person.get(Person_.FIRST_NAME));
			case LAST_NAME -> sortByPath.add(person.get(Person_.LAST_NAME));
			case EMAIL -> sortByPath.add(person.get(Person_.EMAIL));
			default -> {
				// sort by NAME is default
				sortByPath.add(person.get(Person_.LAST_NAME));
				sortByPath.add(person.get(Person_.FIRST_NAME));
			}
		}

		sortByPath.add(person.get(IdentifiableEntity_.ID));

		List<Order> orderBy = new ArrayList<>();
		if (sorting.isSortDesc()) {
			for (Expression<?> path : sortByPath) {
				orderBy.add(cb.desc(path));
			}
		} else {
			for (Expression<?> path : sortByPath) {
				orderBy.add(cb.asc(path));
			}
		}
		cq.orderBy(orderBy);
	}

	@Override
	public Long getTotalPersons(PersonFilter filter) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Long> cq = cb.createQuery(Long.class);
		Root<Person> person = cq.from(Person.class);
		List<Predicate> wherePredicates = new ArrayList<>();
		addWherePredicatesFromFilter(filter, cb, person, wherePredicates);
		cq.select(cb.count(person.get(IdentifiableEntity_.ID)));
		cq.where(wherePredicates.toArray(new Predicate[0]));
		return em.createQuery(cq).getSingleResult();
	}

	private void addWherePredicatesFromFilter(
			PersonFilter filter,
			CriteriaBuilder cb,
			Path<?> person,
			List<Predicate> wherePredicates) {
		if (filter == null) {
			return;
		}

		if (filter.getName() != null) {
			wherePredicates.add(
					cb.like(
							cb.concat(cb.concat(person.get(Person_.LAST_NAME), " "), person.get(Person_.FIRST_NAME)),
							"%" + filter.getName() + "%"));
		}

		if (filter.getEmail() != null) {
			wherePredicates.add(cb.like(person.get(Person_.EMAIL), filter.getEmail()));
		}

	}

}
