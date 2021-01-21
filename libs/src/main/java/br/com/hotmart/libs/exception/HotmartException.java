/*
 * © 2019 Claro S/A - Copyright - Todos os direitos reservados.
 */
package br.com.hotmart.libs.exception;

/**
 * Classe responsável por definir controle de erros da gravação da triagem de oficio no banco.
 *
 * @author Engineering - jabes
 * @since 18 de dez de 2019 11:10:56
 * @version 0.1 - Criação da classe.
 */
public class HotmartException extends RuntimeException {

	/**
	 * Atributo serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Construtor da classe TriagemOficioException
	 */
	public HotmartException() {
		super();
	}

	/**
	 * Construtor da classe TriagemOficioException
	 *
	 * @param message
	 */
	public HotmartException(String message) {
		super(message);
	}

	/**
	 * Construtor da classe TriagemOficioException
	 *
	 * @param message
	 * @param cause
	 */
	public HotmartException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * Construtor da classe TriagemOficioException
	 *
	 * @param cause
	 */
	public HotmartException(Throwable cause) {
		super(cause);
	}

}
