package br.com.virtz.condominio.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import org.primefaces.event.RowEditEvent;

import br.com.virtz.condominio.entity.Bloco;
import br.com.virtz.condominio.entity.Condominio;
import br.com.virtz.condominio.entity.Usuario;
import br.com.virtz.condominio.service.ICondominioService;
import br.com.virtz.condominio.session.SessaoUsuario;
import br.com.virtz.condominio.util.MessageHelper;
import br.com.virtz.condominio.util.NavigationPage;

@ManagedBean
@ViewScoped
public class CondominioController {
	
	@EJB
	private ICondominioService condominioService;
	
	@Inject
	private SessaoUsuario sessao;
	
	@Inject
	private MessageHelper message;
	
	private List<Bloco> blocos;
	private Condominio condominio;
	private Usuario usuario;
	private Integer quantidadeBlocos = null;
	
	@PostConstruct
	public void init(){
		usuario = sessao.getUsuarioLogado();
		condominio = usuario.getCondominio();
		
		blocos = listarTodosBlcoso();
		if(blocos != null && blocos.size() > 0){
			NavigationPage.redirectToPage("/condominio/condominioEdicao.faces");
		}
		
		if(blocos == null || blocos.isEmpty()){
			quantidadeBlocos = 1;
		}
	}
	
	public boolean mostrarCadastrosBlocos(){
		if(blocos == null || blocos.isEmpty()){
			return Boolean.TRUE;
		}
		return Boolean.FALSE;
	}
	
	
	public List<Bloco> listarTodosBlcoso(){
		return condominioService.recuperarTodosBlocos();
	}
	
	public void sugerirBlocos(){
		blocos = new ArrayList<Bloco>();
		for(int i=0; i<quantidadeBlocos; i++){
			Bloco b = new Bloco();
			b.setNome("Bloco "+(i+1));
			b.setQuantidadeAndares(4);
			b.setCondominio(condominio);
			blocos.add(b);
		}		
	}
	

	public void salvarBlocos() throws Exception{
		for(Bloco bloco : blocos){
			try {
				condominioService.salvarBloco(bloco);
			} catch (Exception e) {
				throw new Exception("Ocorreu um erro ao salvar o(s) bloco(s). Favor acesse o menu novamente e repita o processo.");
			}
		}
	}
	
	public void editarCondominio() throws Exception{
		NavigationPage.redirectToPage("condominioEdicao.faces");
	}
	
	
	public void onRowEdit(RowEditEvent event) {
        message.addInfo("Bloco editado "+ ((Bloco) event.getObject()).getId());
    }
     
    public void onRowCancel(RowEditEvent event) {
    	message.addInfo("Edição cancelada!");
    }
    
    
	// GETTERS E SETTERS
	
	public List<Bloco> getBlocos() {
		return blocos;
	}

	public void setBlocos(List<Bloco> blocos) {
		this.blocos = blocos;
	}

	public String getNomeCondominio() {
		return condominio.getNome();
	}

	public Integer getQuantidadeBlocos() {
		return quantidadeBlocos;
	}

	public void setQuantidadeBlocos(Integer quantidadeBlocos) {
		this.quantidadeBlocos = quantidadeBlocos;
	}
		
}
