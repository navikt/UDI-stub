package no.nav.registre.core.provider.rs;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import no.nav.registre.core.database.model.Person;
import no.nav.registre.core.exception.CouldNotCreatePersonException;
import no.nav.registre.core.exception.NotFoundException;
import no.nav.registre.core.service.PersonService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Validated
@Slf4j
@RestController
@RequestMapping("/api/v1/person")
@RequiredArgsConstructor
public class PersonController {

	private final PersonService personService;

	@PostMapping
	public ResponseEntity<PersonControllerResponse> opprettPerson(@Valid @RequestBody Person person) {
		Person createdPerson = personService.opprettPerson(person)
				.orElseThrow(() -> new CouldNotCreatePersonException(String.format("Kunne ikke opprette person med fnr:%s", person.getFnr())));
		return ResponseEntity.status(HttpStatus.CREATED).body(new PersonControllerResponse(createdPerson));
	}

	@GetMapping
	public ResponseEntity<Person> finnPerson(@RequestHeader(name = "Nav-Personident") String fnr) {
		return ResponseEntity.ok(personService.finnPerson(fnr));
	}

	@DeleteMapping
	public ResponseEntity<PersonControllerResponse> deletePerson(@RequestHeader(name = "Nav-Personident") String fnr) {
		try {
			personService.deletePerson(fnr);
			return ResponseEntity.status(HttpStatus.OK).body(new PersonControllerResponse());
		} catch (NotFoundException e) {
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(new PersonControllerResponse(String.format("Kunne ikke slette person med fnr:%s, da personen ikke ble funnet", fnr)));
		}
	}
}
