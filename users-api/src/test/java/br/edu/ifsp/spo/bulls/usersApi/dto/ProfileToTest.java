package br.edu.ifsp.spo.bulls.usersApi.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import br.edu.ifsp.spo.bulls.usersApi.domain.User;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class ProfileToTest {

	@Test
	void testToString() {
		
		ProfileTO profile = new ProfileTO(1, "nome completo", "pais", "sao paulo", "SP", "10/10/1998", new User());
		assertEquals("ProfileTO(id=1, fullName=nome completo, country=pais, city=sao paulo, state=SP, birthDate=10/10/1998, user=User(userName=null, email=null, password=null, token=null, creationDate=null, verified=null))", profile.toString());
	}
	
	@Test
	void testEquals() {
		ProfileTO profile = new ProfileTO(1, "nome completo", "pais", "sao paulo", "SP", "10/10/1998", new User());
		ProfileTO profile1 = new ProfileTO(1, "nome completo", "pais", "sao paulo", "SP", "10/10/1998", new User());
		
		boolean result = profile.equals(profile1);
		
		assertTrue(result);
	}
	
	@Test
	void testNotEquals() {
		ProfileTO profile = new ProfileTO(1, "nome completo", "pais", "sao paulo", "SP", "10/10/1998", new User());
		ProfileTO profile1 = new ProfileTO(1, "nome completo 2", "pais", "sao paulo", "SP", "10/10/1998", new User());
		
		boolean result = profile.equals(profile1);
		
		assertFalse(result);
	}
	
	
	@Test
	void testHashCode() {
		ProfileTO profile = new ProfileTO(1, "nome completo", "pais", "sao paulo", "SP", "10/10/1998", new User());
		ProfileTO profile1 = new ProfileTO(1, "nome completo", "pais", "sao paulo", "SP", "10/10/1998", new User());
		

		assertEquals(profile.hashCode(),profile1.hashCode());
	}
}
