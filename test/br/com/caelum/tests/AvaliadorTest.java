package br.com.caelum.tests;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import br.com.caelum.leilao.dominio.Avaliador;
import br.com.caelum.leilao.dominio.Lance;
import br.com.caelum.leilao.dominio.Leilao;
import br.com.caelum.leilao.dominio.Usuario;

public class AvaliadorTest {

	private final double DELTA = 0.00001;


	@Test
	public void calculaMedia() {
		Usuario joao = new Usuario("João");
		Usuario jose = new Usuario("Jose");
		Usuario maria = new Usuario("Maria");

		Leilao leilao = new Leilao("PC GAMER ULTIMA GERAÇÃO");
		leilao.propoe(new Lance(joao, 10000));
		leilao.propoe(new Lance(maria, 12000));
		leilao.propoe(new Lance(jose, 8000));

		Avaliador avaliador = new Avaliador();
		avaliador.avalia(leilao);

		double mediaEsperada = 10000;

		assertEquals(mediaEsperada, avaliador.getMedia(), DELTA);
	}

	@Test
	public void calculaMaiorEMenorLance() {
		Usuario joao = new Usuario("João");
		Usuario jose = new Usuario("Jose");
		Usuario maria = new Usuario("Maria");

		Leilao leilao = new Leilao("PC GAMER ULTIMA GERAÇÃO");
		leilao.propoe(new Lance(joao, 10000));
		leilao.propoe(new Lance(maria, 12000));
		leilao.propoe(new Lance(jose, 8000));

		Avaliador avaliador = new Avaliador();
		avaliador.avalia(leilao);

		double maiorEsperado = 12000;
		double menorEsperado = 8000;

		assertEquals(maiorEsperado, avaliador.getMaiorLance(), DELTA );
		assertEquals(menorEsperado, avaliador.getMenorLance(), DELTA );
	}

	@Test
	public void avaliaApenasUmLance() {
		Usuario joao = new Usuario("João");

		Leilao leilao = new Leilao("NOTEBOOK HOME CELERON 3A GERAÇÃO");
		leilao.propoe(new Lance(joao, 1500));

		Avaliador avaliador = new Avaliador();
		avaliador.avalia(leilao);

		double maiorEsperado = 1500;
		double menorEsperado = 1500;

		assertEquals(maiorEsperado, avaliador.getMaiorLance(), DELTA);
		assertEquals(menorEsperado, avaliador.getMenorLance(), DELTA);
	}

	@Test
	public void avaliaLancesEmOrdemRandomicas() {
		Usuario joao = new Usuario("João");
		Usuario maria= new Usuario("Maria");
		Usuario fulano = new Usuario("Fulano");

		Leilao leilao = new Leilao("PLAYSTATION 2 - USADO");
		leilao.propoe(new Lance(joao, 120));
		leilao.propoe(new Lance(maria, 450));
		leilao.propoe(new Lance(fulano, 200));
		leilao.propoe(new Lance(fulano, 700));
		leilao.propoe(new Lance(maria, 630));
		leilao.propoe(new Lance(maria, 230));

		Avaliador avaliador = new Avaliador();
		avaliador.avalia(leilao);

		double maiorEsperado = 700;
		double menorEsperado = 120;

		assertEquals(maiorEsperado, avaliador.getMaiorLance(), DELTA);
		assertEquals(menorEsperado, avaliador.getMenorLance(), DELTA);
	}

	@Test
	public void avaliaLancesEmOrdemDecrescente () {
		Usuario joao = new Usuario("João");
		Usuario maria= new Usuario("Maria");

		Leilao leilao = new Leilao("DVD - RESTAURADO");
		leilao.propoe(new Lance(joao, 400));
		leilao.propoe(new Lance(maria, 300));
		leilao.propoe(new Lance(joao, 200));
		leilao.propoe(new Lance(maria, 100));

		Avaliador avaliador = new Avaliador();
		avaliador.avalia(leilao);

		double maiorEsperado = 400;
		double menorEsperado = 100;

		assertEquals(maiorEsperado, avaliador.getMaiorLance(), DELTA);
		assertEquals(menorEsperado, avaliador.getMenorLance(), DELTA);
	}

	@Test
	public void avaliaOsTresMaioresLances() {
		Usuario joao = new Usuario("João");
		Usuario maria = new Usuario("Maria");
		Usuario jorge = new Usuario("Jorge");

		Leilao leilao =  new Leilao("UNIDADE CORONACOIN");
		leilao.propoe(new Lance(joao, 1.0));
		leilao.propoe(new Lance(maria, 2.0));
		leilao.propoe(new Lance(jorge, 2.5));
		leilao.propoe(new Lance(joao, 3.0));
		leilao.propoe(new Lance(jorge, 4.0));

		Avaliador avaliador = new Avaliador();
		avaliador.avalia(leilao);

		List<Lance> lancesEsperados = avaliador.getTresMaiores();

		int quantidadeLancesEsperado = 3;
		double maiorLanceEsperado = 4.0;
		double segundoMaiorLanceEsperado = 3.0;
		double terceiroMaiorLanceEsperado = 2.5;

		assertEquals(quantidadeLancesEsperado, lancesEsperados.size(), DELTA);
		assertEquals(maiorLanceEsperado, lancesEsperados.get(0).getValor(),DELTA);
		assertEquals(segundoMaiorLanceEsperado , lancesEsperados.get(1).getValor(),DELTA);
		assertEquals(terceiroMaiorLanceEsperado , lancesEsperados.get(2).getValor(),DELTA);
	}

	@Test
	public void avaliaApenasDoisLances() {
		Usuario gustavo = new Usuario("Gustavo");
		Usuario ciclano= new Usuario("Ciclano");

		Leilao leilao = new Leilao("CELULAR - NOVO");
		leilao.propoe(new Lance(ciclano, 200.0));
		leilao.propoe(new Lance(gustavo, 1500.0));

		Avaliador avaliador = new Avaliador();
		avaliador.avalia(leilao);

		List<Lance> maiores = avaliador.getTresMaiores();

		int quantidadeLancesEsperado = 2;
		double maiorLance = 1500.0;
		double segundoMaiorLance = 200.0;

		assertEquals(quantidadeLancesEsperado, maiores.size(), DELTA);
		assertEquals(maiorLance, maiores.get(0).getValor(), DELTA);
		assertEquals(segundoMaiorLance, maiores.get(1).getValor(), DELTA);
	}

	@Test
	public void avaliaSemLances() {
		Leilao leilao = new Leilao("CAVALO DE RAÇA");
		Avaliador avaliador = new Avaliador();
		avaliador.avalia(leilao);

		List<Lance> maiores = avaliador.getTresMaiores();

		int quantidadeLances = 0;

		assertEquals(quantidadeLances, maiores.size() ,DELTA);
	}

}
