package br.com.caelum.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.Test;

import br.com.caelum.leilao.dominio.Lance;
import br.com.caelum.leilao.dominio.Leilao;
import br.com.caelum.leilao.dominio.Usuario;

public class LeilaoTest {

	private final double DELTA = 0.00001;
	
	@Test
	public void naoDeveAceitar2LancesEmSequenciaDoMesmoUsuario() {
		Usuario fulano = new Usuario("Fulano");

		Leilao leilao = new Leilao("BERMUDA JEANS");

		leilao.propoe(new Lance(fulano, 2000));
		leilao.propoe(new Lance(fulano, 3000));
		leilao.propoe(new Lance(fulano, 5000));

		int quantidadeLances = 1;

		assertEquals(quantidadeLances, leilao.getLances().size());
	}

	@Test
	public void naoDeveAceitar5LancesDeUmMesmoUsuario() {
		Usuario fulano = new Usuario("Fulano");
		Usuario xibil = new Usuario("Xibil");

		Leilao leilao = new Leilao("BERMUDÃO JEANS");

		leilao.propoe(new Lance(fulano, 2000));
		leilao.propoe(new Lance(xibil, 4000));
		
		leilao.propoe(new Lance(fulano, 5000));
		leilao.propoe(new Lance(xibil, 6000));
		
		leilao.propoe(new Lance(fulano, 7000));
		leilao.propoe(new Lance(xibil, 8000));
		
		leilao.propoe(new Lance(fulano, 9000));
		leilao.propoe(new Lance(xibil, 10000));
		
		leilao.propoe(new Lance(fulano,11000));
		leilao.propoe(new Lance(xibil, 12000));
		
		leilao.propoe(new Lance(fulano, 13000));

		List<Lance> lances = leilao.getLances();
		int quantidadeLances = 10;
		double valorPrimeiroLance = 2000;
		double valorUltimoLance = 12000;
		
		assertEquals(quantidadeLances, leilao.getLances().size());
		assertEquals(valorPrimeiroLance, lances.get(0).getValor(), DELTA);
		assertEquals(valorUltimoLance, lances.get(lances.size() - 1).getValor(), DELTA);
	}

}
