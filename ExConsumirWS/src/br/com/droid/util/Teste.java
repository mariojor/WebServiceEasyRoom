package br.com.droid.util;


public class Teste {

	private static String REGEX = "[^0123456789]";

	public static String removerCaracteresDeNumeros(String valor) {

		if (valor != null) {
			return valor.replaceAll(REGEX, "");
		} else {
			return "";
		}
	}

	// public static void main(String[] args) {
	// String INPUT =
	// "askjdsa3423432dAÇSJD=544333sad|TESTE~çç-(*&¨%$#6557899%@";
	//
	// String removidoNumeros=Teste.removerCaracteresDeNumeros(INPUT);
	//
	// System.out.println(removidoNumeros);
	//
	// }

}
