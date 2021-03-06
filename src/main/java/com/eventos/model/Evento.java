package com.eventos.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Evento implements Serializable {
	
	private static final long seralVersionUID = 1L;
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long codigo;
	
	
	private String nome;
	
	private String local;
	
	private String data;
	
	private String horario;
	
	
	//relacinamento
	@OneToMany
	private  List<Convidado> convidado;
	
		
	
	public Evento() {
	
	}
	
	
	
	
	public long getCodigo() {
		return codigo;
	}




	public void setCodigo(long codigo) {
		this.codigo = codigo;
	}




	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getLocal() {
		return local;
	}
	public void setLocal(String local) {
		this.local = local;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public String getHorario() {
		return horario;
	}
	public void setHorario(String horario) {
		this.horario = horario;
	}
	
	
	
	

}
