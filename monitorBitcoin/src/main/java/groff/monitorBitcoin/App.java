package groff.monitorBitcoin;

import groff.monitorBitcoin.controller.impl.InicialController;

public class App {
	
	public static void main(String[] args) {

		InicialController inicialController = InicialController.getInstance();
		inicialController.iniciarTela();
	
	}
}