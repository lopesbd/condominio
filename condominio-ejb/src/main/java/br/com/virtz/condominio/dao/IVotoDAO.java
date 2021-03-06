package br.com.virtz.condominio.dao;

import javax.ejb.Local;

import br.com.virtz.condominio.entidades.Usuario;
import br.com.virtz.condominio.entidades.Votacao;
import br.com.virtz.condominio.entidades.Voto;

@Local
public interface IVotoDAO extends CrudDAO<Voto> {
	
	public Long totalVotos(Votacao votacao);
	public Voto recuperarPorUsuario(Votacao votacao, Usuario usuario);
	
}
