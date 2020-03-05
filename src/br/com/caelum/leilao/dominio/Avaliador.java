package br.com.caelum.leilao.dominio;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Avaliador {

	private double maiorDeTodos  = Double.NEGATIVE_INFINITY;
	private double menorDeTodos  = Double.POSITIVE_INFINITY;
	private double media  = 0;
	private List<Lance> maiores;


	public void avalia(Leilao leilao) {
		double total = 0;
		for(Lance lance : leilao.getLances()) {
			if(lance.getValor() > maiorDeTodos) maiorDeTodos = lance.getValor();
			if(lance.getValor() < menorDeTodos) menorDeTodos = lance.getValor();
			total += lance.getValor();
		}
		calculaMedia(total, leilao.getLances().size());
		recuperaMaiores(leilao);
	}
	
	public void recuperaMaiores(Leilao leilao){
		
		List<Lance> lances = new ArrayList<>(leilao.getLances());
		Collections.sort(lances, Comparator.comparing(Lance :: getValor).reversed());
		this.maiores  = lances.subList(0, leilao.getLances().size() > 3 ? 3 : leilao.getLances().size());
		
//		this.maiores.stream().sorted(Comparator.comparing(Lance :: getValor)).collect(Collectors.toList());
	}
	
	public double calculaMedia(double total, int dividendo) {
		if(total >=1 && dividendo >= 1) {
			this.media = total / dividendo;
		}
		return media;
	}
	
	public double getMaiorLance() {
		return maiorDeTodos;
	}
	public double getMenorLance() {
		return menorDeTodos;
	}
	public double getMedia() {
		return media;
	}
	
	public List<Lance> getTresMaiores() {
		return maiores;
	}
}