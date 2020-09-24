package dao.interfaces;

import java.util.List;
/*
 * Aqui eu defini o padrao do dao, todos os daos são obrigados a ter esses metodos, usei generics para tal, padrao dao de certo TIPO
 */
public abstract interface IPadraoDAO<T> {

	public void inserir(T obj);
	
	public void deletar(int id);
	
	public void editar(T obj);
	
	public List<T> buscarId(int id);
	
	public List<T> listar();

}
