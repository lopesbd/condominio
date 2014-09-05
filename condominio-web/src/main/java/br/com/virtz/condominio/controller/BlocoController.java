package br.com.virtz.condominio.controller;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import br.com.virtz.condominio.entity.Bloco;
import br.com.virtz.condominio.service.IBlocoService;
import br.com.virtz.condominio.util.MessageHelper;

@ManagedBean
@ViewScoped
public class BlocoController {

	@EJB
	private IBlocoService blocoService;
	
	@Inject
	private MessageHelper message;
	
	private Bloco bloco;
	private Bloco blocoSelecionado;
	private List<Bloco> blocos;
	
	@PostConstruct
	public void init(){
		bloco = new Bloco();
		blocoSelecionado = null;
		blocos = listarTodos(); 
	}
	
	public void salvar(){
		try {
			blocoService.salvar(bloco);
			message.addInfo("O bloco "+bloco.getNome()+" foi salvo com sucesso!"); 

			blocos = listarTodos(); 
			bloco = new Bloco();
		} catch (Exception e) {
			message.addError("Ocorreu um erro ao salvar o bloco");
		}
	}
	
	public void remover(){
		
		if(blocoSelecionado == null){
			message.addError("Nenhum bloco foi selecionado para ser removido.");
		}
		
		blocoService.remover(blocoSelecionado.getId());
		blocos = listarTodos(); 
		
		message.addInfo("O bloco "+blocoSelecionado.getNome()+" foi removido com sucesso"); 
		blocoSelecionado = null;
	}
	
	public List<Bloco> listarTodos(){
		return blocoService.recuperarTodos();
	}
	

	// GETTERS E SETTERS
	public Bloco getBloco() {
		return bloco;
	}

	public void setBloco(Bloco bloco) {
		this.bloco = bloco;
	}

	public Bloco getBlocoSelecionado() {
		return blocoSelecionado;
	}

	public void setBlocoSelecionado(Bloco blocoSelecionado) {
		this.blocoSelecionado = blocoSelecionado;
	}

	public List<Bloco> getBlocos() {
		return blocos;
	}

	public void setBlocos(List<Bloco> blocos) {
		this.blocos = blocos;
	}	
	
}
