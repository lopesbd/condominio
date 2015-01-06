package br.com.virtz.condominio.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import br.com.virtz.condominio.controller.beanview.VotacaoView;
import br.com.virtz.condominio.controller.util.UtilTipoVotacao;
import br.com.virtz.condominio.entidades.OpcaoVotacao;
import br.com.virtz.condominio.entidades.Usuario;
import br.com.virtz.condominio.entidades.Votacao;
import br.com.virtz.condominio.entidades.Voto;
import br.com.virtz.condominio.geral.ParametroSistemaLookup;
import br.com.virtz.condominio.service.IVotacaoService;
import br.com.virtz.condominio.session.SessaoUsuario;
import br.com.virtz.condominio.util.MessageHelper;

@ManagedBean
@ViewScoped
public class VotacaoController {
	
	@EJB
	private IVotacaoService votacaoService;
	
	@Inject
	private SessaoUsuario sessao;
	
	@Inject
	private ParametroSistemaLookup parametroLookup; 
	
	@Inject
	private MessageHelper message;
	
	
	private Votacao votacao;
	private List<Votacao> votacoes;
	private List<VotacaoView> votacoesView;
	private String tipoVotacaoSelecionado;
	private UtilTipoVotacao utilTipoVotacao;
		
	@PostConstruct
	public void init(){
		Usuario usuario = sessao.getUsuarioLogado();
		utilTipoVotacao = new UtilTipoVotacao();
		votacoesView = new ArrayList<VotacaoView>();
		
		votacoes = votacaoService.recuperarTodasAtivas(usuario.getCondominio());
		if(votacoes != null && !votacoes.isEmpty()){
			for(Votacao v : votacoes){
				VotacaoView vv = new VotacaoView(v);
				vv.getUtil().tratarTipoVotacaoExibicao(v.getTipoVotacao());
				votacoesView.add(vv);
			}
		}
	}
	
	public void votar(VotacaoView votacaoView){
		Usuario usuario =  sessao.getUsuarioLogado();
		UtilTipoVotacao util = votacaoView.getUtil();
		
		Voto voto = new Voto();
		voto.setVotacao(votacaoView.getVotacao());
		voto.setDataVotacao(new Date());
		voto.setUsuario(usuario);
		
		if(util.isData()){
			voto.setData(util.getValorData());
		} else if(util.isMoeda()){
			voto.setMoeda(util.getValorMoeda());
		} else if(util.isNumerico()){
			voto.setNumero(util.getValorNumerico());
		} else if(util.isSimNao()){
			voto.setSim(util.isValorSimNao());
		} else if(util.isOpcoes()){
			OpcaoVotacao opcao = votacaoService.recuperarOpcao(util.getIdValorOpcao());
			voto.setOpcao(opcao);
		}  
		
		votacaoService.votar(voto);
	}

	
	/* GETTERS E SETTERS*/
		
	public Votacao getVotacao() {
		return votacao;
	}
	
	public void setVotacao(Votacao votacao) {
		this.votacao = votacao;
	}

	public List<String> getTiposVotacao() {
		return null;
	}

	public String getTipoVotacaoSelecionado() {
		return tipoVotacaoSelecionado;
	}

	public void setTipoVotacaoSelecionado(String tipoVotacaoSelecionado) {
		this.tipoVotacaoSelecionado = tipoVotacaoSelecionado;
	}

	/*public boolean isSimNao(Votacao votacao) {
		utilTipoVotacao.tratarTipoVotacaoExibicao(votacao.getTipoVotacao());
		return utilTipoVotacao.isSimNao();
	}

	public boolean isData(Votacao votacao) {
		utilTipoVotacao.tratarTipoVotacaoExibicao(votacao.getTipoVotacao());
		return utilTipoVotacao.isData();
	}

	public boolean isMoeda(Votacao votacao) {
		utilTipoVotacao.tratarTipoVotacaoExibicao(votacao.getTipoVotacao());
		return utilTipoVotacao.isMoeda();
	}

	public boolean isNumerico(Votacao votacao) {
		utilTipoVotacao.tratarTipoVotacaoExibicao(votacao.getTipoVotacao());
		return utilTipoVotacao.isNumerico();
	}

	public boolean isOpcoes(Votacao votacao) {
		utilTipoVotacao.tratarTipoVotacaoExibicao(votacao.getTipoVotacao());
		return utilTipoVotacao.isOpcoes();
	}*/


	public List<Votacao> getVotacoes() {
		return votacoes;
	}

	public void setVotacoes(List<Votacao> votacoes) {
		this.votacoes = votacoes;
	}

	public List<VotacaoView> getVotacoesView() {
		return votacoesView;
	}

	public void setVotacoesView(List<VotacaoView> votacoesView) {
		this.votacoesView = votacoesView;
	}
	
	
}

