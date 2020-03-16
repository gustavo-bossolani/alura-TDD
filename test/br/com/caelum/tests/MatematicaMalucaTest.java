package br.com.caelum.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;

import br.com.caelum.leilao.dominio.MatematicaMaluca;

public class MatematicaMalucaTest {
	
	@Test
	public void multiplicaNumerosMaioresQue30() {
		MatematicaMaluca math = new MatematicaMaluca();
		assertEquals(50*4, math.contaMaluca(50));
	}
	
	@Test
	public void multiplicaNumerosMenoresQue30MaioresQue10() {
		MatematicaMaluca math = new MatematicaMaluca();
		assertEquals(17*3, math.contaMaluca(17));
	}
	
	@Test
	public void multiplicaNumerosMenoresQue10() {
		MatematicaMaluca math = new MatematicaMaluca();
		assertEquals(5*2, math.contaMaluca(5));
	}
	
}
