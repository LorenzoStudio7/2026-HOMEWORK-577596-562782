package it.uniroma3.diadia;

import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;

/**
 * Classe principale di diadia, un semplice gioco di ruolo ambientato al dia.
 * Per giocare crea un'istanza di questa classe e invoca il letodo gioca
 *
 * Questa e' la classe principale crea e istanzia tutte le altre
 *
 * @author docente di POO (da un'idea di Michael Kolling and David J. Barnes)
 * 
 * @version base
 */

public class DiaDia {

	static final private String MESSAGGIO_BENVENUTO = ""
			+ "Ti trovi nell'Universita', ma oggi e' diversa dal solito...\n"
			+ "Meglio andare al piu' presto in biblioteca a studiare. Ma dov'e'?\n"
			+ "I locali sono popolati da strani personaggi, " + "alcuni amici, altri... chissa!\n"
			+ "Ci sono attrezzi che potrebbero servirti nell'impresa:\n"
			+ "puoi raccoglierli, usarli, posarli quando ti sembrano inutili\n"
			+ "o regalarli se pensi che possano ingraziarti qualcuno.\n\n"
			+ "Per conoscere le istruzioni usa il comando 'aiuto'.";

	static final private String[] elencoComandi = { "vai", "aiuto", "fine", "prendi", "posa" };

	private Partita partita;
	private IOConsole console;

	public DiaDia(IOConsole console) {
		this.partita = new Partita();
		this.console=console;
	}

	public void gioca() {
		String istruzione;
		console.mostraMessaggio(MESSAGGIO_BENVENUTO);

		do
			istruzione=this.console.leggiRiga();
		while (!processaIstruzione(istruzione));
	}

	/**
	 * Processa una istruzione
	 *
	 * @return true se l'istruzione e' eseguita e il gioco continua, false
	 *         altrimenti
	 */
	private boolean processaIstruzione(String istruzione) {
		Comando comandoDaEseguire = new Comando(istruzione);
        if(comandoDaEseguire.getNome()==null)return false;
        
		if(comandoDaEseguire.getNome().equals("fine")) {
			this.fine();
			return true;
		} else if (comandoDaEseguire.getNome().equals("vai"))
			this.vai(comandoDaEseguire.getParametro());
		else if (comandoDaEseguire.getNome().equals("aiuto"))
			this.aiuto();
		else if(comandoDaEseguire.getNome().equals("prendi"))
			this.prendi(comandoDaEseguire.getParametro());
		else if(comandoDaEseguire.getNome().equals("posa"))
			this.prendi(comandoDaEseguire.getParametro());
		else
			console.mostraMessaggio("Comando sconosciuto");
		if (this.partita.vinta()) {
			console.mostraMessaggio("Hai vinto!");
			return true;
		} else
			return false;
	}

	// implementazioni dei comandi dell'utente:

	/**
	 * Stampa informazioni di aiuto.
	 */
	private void aiuto() {
		for (int i = 0; i < elencoComandi.length; i++)
			console.mostraMessaggio(elencoComandi[i] + " ");
		console.mostraMessaggio("");
	}

	/**
	 * Cerca di andare in una direzione. Se c'e' una stanza ci entra e ne stampa il
	 * nome, altrimenti stampa un messaggio di errore
	 */
	private void vai(String direzione) {
		if (direzione == null)
			console.mostraMessaggio("Dove vuoi andare ?");
		Stanza prossimaStanza = null;
		prossimaStanza = this.partita.getStanzaCorrente().getStanzaAdiacente(direzione);
		if (prossimaStanza == null)
			console.mostraMessaggio("Direzione inesistente");
		else {
			this.partita.setStanzaCorrente(prossimaStanza);
			int cfu = this.partita.getGiocatore().getCfu();
			this.partita.getGiocatore().setCfu(--cfu);
		}
		console.mostraMessaggio(partita.getStanzaCorrente().getDescrizione());
	}
	
	/** gli attrezzi presi vengono rimossi dalla stanza e aggiunti alla borsa
	 * 
	*/
	public void prendi(String nomeAttrezzo) {
		if(nomeAttrezzo==null) {
			console.mostraMessaggio("Inserisci l'attrezzo che vuoi prendere!");
		}
		Attrezzo a=this.partita.getStanzaCorrente().getAttrezzo(nomeAttrezzo);
		if(a==null) 
			console.mostraMessaggio("L'attrezzo non è nella stanza!");
		//a=attrezzo da prendere
		else {
			//giocatore prende attrezzo e lo mette nella borsa
			if(this.partita.getGiocatore().prendereAttrezzo(a)) {
				this.partita.getStanzaCorrente().removeAttrezzo(a);
				console.mostraMessaggio("attrezzo inserito nella borsa!");
			}
			else 
				console.mostraMessaggio("borsa piena!");
				
		}
	}
	
/**
 * gli attrezzi posati vengono rimossi dalla borsa e aggiunti alla stanza
 */
 
	public void posa (String nomeAttrezzo) {
		
		if(nomeAttrezzo==null) {
			console.mostraMessaggio("Inserisci l'attrezzo che vuoi posare!");
		}
		Attrezzo a=this.partita.getGiocatore().getBorsa().getAttrezzo(nomeAttrezzo);
		if(a==null) 
			console.mostraMessaggio("L'attrezzo non è nella borsa!");
		//a=attrezzo da posare
		else {
			if(this.partita.getStanzaCorrente().addAttrezzo(a))
				this.partita.getGiocatore().posareAttrezzo(a);
			console.mostraMessaggio("attrezzo inserito nella stanza!");
		}
	}

	/**
	 * Comando "Fine".
	 */
	private void fine() {
		console.mostraMessaggio("Grazie di aver giocato!"); // si desidera smettere
	}

	public static void main(String[] argc) {
		IOConsole console=new IOConsole();
		DiaDia gioco = new DiaDia(console);
		gioco.gioca();
		
	}
}