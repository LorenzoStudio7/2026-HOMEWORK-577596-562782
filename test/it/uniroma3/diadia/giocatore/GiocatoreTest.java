package it.uniroma3.diadia.giocatore;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class GiocatoreTest {

private Giocatore giocatore;

@BeforeEach
public void setUp() {
		this.giocatore = new Giocatore();
}

@Test
public void testCfuIniziali() {
	assertEquals(20, this.giocatore.getCfu());
}

@Test
public void testPrendereAttrezzo() {
	Attrezzo macete = new Attrezzo("macete", 5);
	assertTrue(this.giocatore.prendereAttrezzo(macete));
	assertTrue(this.giocatore.getBorsa().hasAttrezzo("macete"));
}

@Test
public void testPosareAttrezzo() {
	Attrezzo macete = new Attrezzo("macete", 5);
	this.giocatore.prendereAttrezzo(macete);
	assertEquals(macete, this.giocatore.posareAttrezzo(macete));
 }
}