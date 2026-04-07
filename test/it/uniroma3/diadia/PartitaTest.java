package it.uniroma3.diadia;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import it.uniroma3.diadia.ambienti.Stanza;

public class PartitaTest {

private Partita partita;

@BeforeEach
public void setUp() {
this.partita = new Partita();
}

@Test
public void testVinta_InizioPartita() {
	assertFalse(this.partita.vinta());
}

@Test
public void testVinta_Vittoria() {
	Stanza biblioteca = this.partita.getStanzaCorrente().getStanzaAdiacente("nord");
	this.partita.setStanzaCorrente(biblioteca);
	assertTrue(this.partita.vinta());
}

@Test
public void testVinta_StanzaSbagliata() {
	Stanza aulaN11 = this.partita.getStanzaCorrente().getStanzaAdiacente("est");
	this.partita.setStanzaCorrente(aulaN11);
	assertFalse(this.partita.vinta());
}

@Test
public void testIsFinita_InizioPartita() {
	assertFalse(this.partita.isFinita());
}

@Test
public void testIsFinita_SenzaCfu() {
		this.partita.getGiocatore().setCfu(0);
		assertTrue(this.partita.isFinita());
	}

@Test
public void testIsFinita_Abbandono() {
		this.partita.setFinita();
		assertTrue(this.partita.isFinita());
 }
}