package br.com.virtz.condominio.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import br.com.virtz.condominio.constantes.EnumTipoUsuario;
import br.com.virtz.condominio.entidades.Usuario;

@Stateless
public class UsuarioDAO extends DAO<Usuario> implements IUsuarioDAO {

	@Override
	public Usuario recuperarUsuarioCompleto(Long id) {
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT u FROM Usuario u ")
		.append(" LEFT JOIN FETCH u.condominio c ")
		.append(" LEFT JOIN FETCH c.areasComuns areas ")
		.append(" WHERE u.id = :idUsuario ");
		
		Query qry = getEntityManager().createQuery(sb.toString());
		qry.setParameter("idUsuario", id);
		List<Usuario> usuarios = qry.getResultList();
		if(usuarios != null && !usuarios.isEmpty()){
			return usuarios.get(0);
		}
		return null;
	}
	

	@Override
	public List<Usuario> recuperarTodos(Long idCondominio) {
		Query qry = getEntityManager().createNamedQuery("Usuario.recuperarPorCondominio");
		qry.setParameter("idCondominio", idCondominio);
		return qry.getResultList();
	}
	
	@Override
	public void alterarStatus(Long idUsuario, EnumTipoUsuario tipoUsuario) {
		StringBuilder sb = new StringBuilder();
		sb.append("UPDATE Usuario u ")
		.append(" SET u.tipoUsuario = :tipoUsuairo ")
		.append(" WHERE u.id = :idUsuario ");
		
		Query qry = getEntityManager().createQuery(sb.toString());
		
		qry.setParameter("idUsuario", idUsuario);
		qry.setParameter("tipoUsuairo", tipoUsuario);
		
		qry.executeUpdate();
	}


	@Override
	public Usuario recuperarUsuarioPorEmail(String email) {
		Query qry = getEntityManager().createNamedQuery("Usuario.recuperarPorEmail");
		qry.setParameter("email", email);
		List<Usuario> usuarios = qry.getResultList();
		if(usuarios != null && !usuarios.isEmpty()){
			return usuarios.get(0);
		}
		return null;
	}
	
}
