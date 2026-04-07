package it.uniroma3.diadia.giocatore;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class BorsaTest {

	private Borsa borsa;
	private Attrezzo piccone;

@BeforeEach
public void setUp() {
	this.borsa = new Borsa();
	this.piccone = new Attrezzo("piccone", 2);
}

@Test
public void testAddAttrezzo_Base() {
	assertTrue(this.borsa.addAttrezzo(this.piccone));
	assertEquals(2, this.borsa.getPeso());
}

@Test
public void testAddAttrezzo_TroppoPesante() {
	Attrezzo pala = new Attrezzo("pala", 15);
	assertFalse(this.borsa.addAttrezzo(pala));
}

@Test
public void testRemoveAttrezzo_Presente() {
	this.borsa.addAttrezzo(this.piccone);
	assertEquals(this.piccone, this.borsa.removeAttrezzo("piccone"));
	assertTrue(this.borsa.isEmpty());
 }
}