package br.com.caelum.leilao.dominio;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Leilao {

	private String descricao;
	private List<Lance> lances;

	public Leilao(String descricao) {
		this.descricao = descricao;
		this.lances = new ArrayList<Lance>();
	}

	public void propoe(Lance lance) {
		if(this.lances.isEmpty() || !ehMesmoUsuario(lance, ultimoLanceDado()) 
				&& quantidadeLances(lance.getUsuario()) < 5) {
			lances.add(lance);
		}
	}

	private int quantidadeLances(Usuario usuario) {
		int totalLances = 0;
		for (Lance lance : this.lances) {
			if(lance.getUsuario().equals(usuario)) totalLances ++;
		}
		return totalLances;
	}

	private boolean ehMesmoUsuario(Lance lanceAtual ,Lance ultimoLance) {
		return lanceAtual.getUsuario().equals(ultimoLance.getUsuario());
	}

	private Lance ultimoLanceDado() {
		return this.lances.get(this.lances.size() - 1);
	}

	public String getDescricao() {
		return descricao;
	}

	public List<Lance> getLances() {
		return Collections.unmodifiableList(this.lances);
	}



}
