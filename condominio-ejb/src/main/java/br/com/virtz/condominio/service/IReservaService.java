package br.com.virtz.condominio.service;

import java.util.List;

import javax.ejb.Local;

import br.com.virtz.condominio.entidades.AreaComum;
import br.com.virtz.condominio.entidades.Reserva;

@Local
public interface IReservaService {
	public Reserva salvar(Reserva reserva) throws Exception;
	public void remover(Long id);
	public List<Reserva> recuperarTodos();
	public List<Reserva> recuperar(AreaComum area);
	
}
