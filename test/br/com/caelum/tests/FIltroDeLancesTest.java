package br.com.caelum.tests;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import br.com.caelum.leilao.dominio.FiltroDeLances;
import br.com.caelum.leilao.dominio.Lance;
import br.com.caelum.leilao.dominio.Usuario;

public class FIltroDeLancesTest {

	private final double DELTA = 0.00001;

	@Test
	public void deveSelecionarLancesEntre1000E3000() {
		Usuario joao = new Usuario("Joao");

		FiltroDeLances filtro = new FiltroDeLances();
		List<Lance> resultado = filtro.filtra(Arrays.asList(
				new Lance(joao,2000), 
				new Lance(joao,1000), 
				new Lance(joao,3000), 
				new Lance(joao, 800)));

		assertEquals(1, resultado.size());
		assertEquals(2000, resultado.get(0).getValor(), DELTA);
	}

	@Test
	public void deveSelecionarLancesEntre500E700() {
		Usuario joao = new Usuario("Joao");

		FiltroDeLances filtro = new FiltroDeLances();
		List<Lance> resultado = filtro.filtra(Arrays.asList(
				new Lance(joao,600), 
				new Lance(joao,500), 
				new Lance(joao,700), 
				new Lance(joao, 800)));

		assertEquals(1, resultado.size());
		assertEquals(600, resultado.get(0).getValor(), DELTA);
	}

	@Test
	public void deveSelecionarLancesMaioresQue5000() {
		Usuario joao = new Usuario("Joao");

		FiltroDeLances filtro = new FiltroDeLances();
		List<Lance> resultado = filtro.filtra(Arrays.asList(
				new Lance(joao, 8000.0),
				new Lance(joao,6700.0)));

		int tamanhoEsperado = 2;
		int maiorEsperado = 8000;
		int segundoMaiorEsperado = 6700;
		
		assertEquals(tamanhoEsperado, resultado.size(), DELTA);
		 assertEquals(maiorEsperado, resultado.get(0).getValor(), DELTA);
		 assertEquals(segundoMaiorEsperado, resultado.get(1).getValor(), DELTA);
	}

	@Test
	public void deveEliminarLancesMenoresQue500() {
		Usuario joao = new Usuario("Joao");

		FiltroDeLances filtro = new FiltroDeLances();
		List<Lance> resultado = filtro.filtra(Arrays.asList(
				new Lance(joao, 80.0),
				new Lance(joao,67.0)));
		
		int tamanhoEsperado = 0;
		
		assertEquals(tamanhoEsperado, resultado.size(), DELTA);
	}
	
	@Test
	public void deveEliminarLancesEntre700E1000() {
		Usuario joao = new Usuario("Joao");

		FiltroDeLances filtro = new FiltroDeLances();
		List<Lance> resultado = filtro.filtra(Arrays.asList(
				new Lance(joao, 700.0),
				new Lance(joao, 750.0),
				new Lance(joao,1000.0)));
		
		int tamanhoEsperado = 0;
		assertEquals(tamanhoEsperado, resultado.size(), DELTA);
	}
	
	
	@Test
	public void deveEliminarLancesEntre3000E5000() {
		Usuario joao = new Usuario("Joao");
		
		FiltroDeLances filtro = new FiltroDeLances();
		List<Lance> resultado = filtro.filtra(Arrays.asList(
				new Lance(joao, 3000.0),
				new Lance(joao, 4750.0),
				new Lance(joao,5000.0)));
		
		int tamanhoEsperado = 0;
		
		assertEquals(tamanhoEsperado, resultado.size(), DELTA);
	}
}
