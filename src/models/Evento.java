package models;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Evento {

	private int cod;
	private String nome;
	private Calendar calendario;
	private Float valorEntrada;

	public Evento() {
		super();
	}

	public Evento(String nome, Calendar calendario, Float valorEntrada) {
		super();
		this.nome = nome;
		this.calendario = calendario;
		this.valorEntrada = valorEntrada;
	}

	public Evento(int cod, String nome, Calendar calendario, Float valorEntrada) {
		super();
		this.cod = cod;
		this.nome = nome;
		this.calendario = calendario;
		this.valorEntrada = valorEntrada;
	}

	@Override
	public String toString() {
		return "Evento [nome evento = " + nome + ", calendario = " + calendarioFormatado() + ", valorEntrada = R$ " + valorEntrada
				+ "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((calendario == null) ? 0 : calendario.hashCode());
		result = prime * result + cod;
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((valorEntrada == null) ? 0 : valorEntrada.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Evento other = (Evento) obj;
		if (calendario == null) {
			if (other.calendario != null)
				return false;
		} else if (!calendario.equals(other.calendario))
			return false;
		if (cod != other.cod)
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (valorEntrada == null) {
			if (other.valorEntrada != null)
				return false;
		} else if (!valorEntrada.equals(other.valorEntrada))
			return false;
		return true;
	}

	public int getCod() {
		return cod;
	}

	public void setCod(int cod) {
		this.cod = cod;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Calendar getCalendario() {
		return calendario;
	}

	public String getCalendarioString() {
		return calendario.get(Calendar.YEAR) + "-" 
			 + calendario.get(Calendar.MONTH) + "-"
			 + calendario.get(Calendar.DATE);
	}

	public void setCalendario(Calendar calendario) {
		this.calendario = calendario;
	}

	public Float getValorEntrada() {
		return valorEntrada;
	}

	public void setValorEntrada(Float valorEntrada) {
		this.valorEntrada = valorEntrada;
	}

	public String calendarioFormatado() {
		return calendario.get(Calendar.DATE) + "/" 
			 + calendario.get(Calendar.MONTH) + "/"
			 + calendario.get(Calendar.YEAR);
	}
}
