package it.uniroma3.diadia.giocatore;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class Giocatore {
	static final private int CFU_INIZIALI = 20;
	private Borsa borsa;
	private int cfu;
	
	public Giocatore() {
		this.cfu = CFU_INIZIALI;
		this.borsa=new Borsa();
	}
	
	public int getCfu() {
		return this.cfu;
	}

	public void setCfu(int cfu) {
		this.cfu = cfu;		
	}	
	
	public Borsa getBorsa() {
		return this.borsa;
	}
	
	public void setBorsa(Borsa borsa) {
		this.borsa=borsa;
	}
	
	public boolean prendereAttrezzo(Attrezzo attrezzo) {
		return this.borsa.addAttrezzo(attrezzo);
		
	}
	
	public Attrezzo posareAttrezzo(Attrezzo attrezzo) {
		return this.borsa.removeAttrezzo(attrezzo.getNome());
		
	}
}
