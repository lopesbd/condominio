package br.com.virtz.condominio.entidades;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
public class Apartamento extends Entidade implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="idBloco", nullable=false)
	private Bloco bloco;
	
	@Column
	private Integer andar;
	
	@Column
	private String numero;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Bloco getBloco() {
		return bloco;
	}

	public void setBloco(Bloco bloco) {
		this.bloco = bloco;
	}

	public Integer getAndar() {
		return andar;
	}

	public void setAndar(Integer andar) {
		this.andar = andar;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}
	
	public String getNomeExibicao(){
		return this.numero+"  [Andar: "+this.andar+"]";
	}

	@Override
	public boolean equals(Object obj) {
		if(obj == null){
			return false;
		}
		
		if (!(obj instanceof Entidade))
			return false;

		if (obj == this)
			return true;

		if(this.getId() != null){
			return this.getId().equals(((Apartamento)obj).getId());
		}
		
		if(this.getNumero() != null){
			return this.getNumero().equals(((Apartamento)obj).getNumero());
		}
		
		return true;
	}
	
	@Override
	public int hashCode() {
	    int hash = 7;
	    
	    if(this.getId() != null){
	    	 hash = 23 * hash + (this.getId()!= null ? this.getId().hashCode() : 0);
		} else if(this.getNumero() != null){
			 hash = 23 * hash + (this.getNumero()!= null ? this.getNumero().hashCode() : 0);
		}
		
	    return hash;
	}
}