package it.uniroma3.diadia.ambienti;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class LabirintoTest {

private Labirinto labirinto;

@BeforeEach
public void setUp() {
	this.labirinto = new Labirinto();
}

@Test
public void testGetStanzaIniziale() {
	assertEquals("Atrio", this.labirinto.getStanzaIniziale().getNome());
}

@Test
public void testGetStanzaVincente() {
	assertEquals("Biblioteca", this.labirinto.getStanzaVincente().getNome());
 }
}