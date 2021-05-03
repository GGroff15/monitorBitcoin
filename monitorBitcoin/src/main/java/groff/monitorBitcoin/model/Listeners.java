package groff.monitorBitcoin.model;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import groff.monitorBitcoin.controller.impl.InicialController;

public class Listeners {

	public ActionListener sairListener() {
		return new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				System.exit(0);
				
			}
		};
		
	}
	
	public ActionListener exibirListener() {
		return new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				System.out.println("Abrindo tela...");
				InicialController controller = InicialController.getInstance();
				controller.exbirTela();
				
			}
		};
		
	}
	
	public MouseListener mouseLister() {
		return new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent arg0) {
				
			}
			
			@Override
			public void mousePressed(MouseEvent arg0) {

//				System.out.println("Reexibir tela");
//				InicialController controller = InicialController.getInstance();
//				controller.exbirTela();
				
			}
			
			@Override
			public void mouseExited(MouseEvent arg0) {
				
			}
			
			@Override
			public void mouseEntered(MouseEvent arg0) {
				
			}
			
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
			}
		};
	}
}
