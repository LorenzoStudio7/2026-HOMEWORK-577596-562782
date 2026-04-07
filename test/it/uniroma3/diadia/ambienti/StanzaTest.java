package it.uniroma3.diadia.ambienti;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class StanzaTest {
	
@Test
void testLeStanzeVuoteNonHannoAttrezzi() {
	assertFalse(new Stanza("Vuota").hasAttrezzo("martello"));
}
	
@Test
void testStanzaConUnAttrezzo_trovato() {
	Stanza piena = new Stanza("piena");
	assertFalse(piena.hasAttrezzo("martello"));
	piena.addAttrezzo(new Attrezzo("martello",10));
	assertTrue(piena.hasAttrezzo("martello"));
}
	
@Test
void testStanzaConUnAttrezzo_mancante() {
	Stanza piena = new Stanza("piena");
	piena.addAttrezzo(new Attrezzo("martello",10));
	assertFalse(piena.hasAttrezzo("introvabile"));
}
	
@Test
void testMassimo10Attrezzi() {
	Stanza piena = new Stanza("piena");
	for(int i = 0; i < 10; i++) {
		assertTrue(piena.addAttrezzo(new Attrezzo("attrezzo_"+i,i)));
	}
	assertFalse(piena.addAttrezzo(new Attrezzo("attrezzoEscluso",0)));
}

@Test
void testGetAttrezzo_presente() {
	Stanza stanza = new Stanza("stanza");
	Attrezzo spada = new Attrezzo("spada", 5);
	stanza.addAttrezzo(spada);
	assertEquals(spada, stanza.getAttrezzo("spada"));
}

@Test
void testGetAttrezzo_assente() {
	Stanza stanza = new Stanza("stanza");
	assertNull(stanza.getAttrezzo("scudo"));
}

@Test
void testGetAttrezzo_nomeNull() {
	Stanza stanza = new Stanza("stanza");
	assertNull(stanza.getAttrezzo(null));
}

@Test
void testGetStanzaAdiacente_esistente() {
	Stanza stanza = new Stanza("partenza");
	Stanza nord = new Stanza("nord");
	stanza.impostaStanzaAdiacente("nord", nord);
	assertEquals(nord, stanza.getStanzaAdiacente("nord"));
}

@Test
void testGetStanzaAdiacente_nonEsistente() {
	Stanza stanza = new Stanza("partenza");
	assertNull(stanza.getStanzaAdiacente("sud"));
}

@Test
void testImpostaStanzaAdiacente_sovrascrive() {
	Stanza stanza = new Stanza("partenza");
	Stanza s1 = new Stanza("vecchia");
	Stanza s2 = new Stanza("nuova");
	stanza.impostaStanzaAdiacente("est", s1);
	stanza.impostaStanzaAdiacente("est", s2);
	assertEquals(s2, stanza.getStanzaAdiacente("est"));
 }
}