package groff.monitorBitcoin.model;

import java.awt.AWTException;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.TrayIcon.MessageType;
import java.awt.event.ActionListener;

import groff.monitorBitcoin.model.entity.Constantes;

public class Notificacao {
	
	public SystemTray criarSystemTray() {
		SystemTray tray = SystemTray.getSystemTray();
		return tray;
	}
	
	public Image criarImagemNotificacao(String caminho) {
		Image image = Toolkit.getDefaultToolkit().createImage(caminho);
		return image;
	}
	
	public TrayIcon criarTrayIcon(SystemTray tray, Image image, PopupMenu popup) {
		TrayIcon trayIcon = new TrayIcon(image, Constantes.BITCOIN_WATCHER_NOTIF, popup);
		try {
			tray.add(trayIcon);
		} catch (AWTException e) {
			e.printStackTrace();
		}
		return trayIcon;
	}
	
	public TrayIcon criarTrayIcon(SystemTray tray, Image image) {
		TrayIcon trayIcon = new TrayIcon(image, Constantes.BITCOIN_WATCHER_NOTIF);
		try {
			tray.add(trayIcon);
		} catch (AWTException e) {
			e.printStackTrace();
		}
		return trayIcon;
	}
	
	public PopupMenu criarMenuPopup(String nomeMenu) {
		return new PopupMenu(nomeMenu);
	}
	
	public MenuItem criarItemMenu(String nome) {
		return new MenuItem(nome);
	}
	
	public void adicionarEventoItemMenu(MenuItem itemMenu, ActionListener evento) {
		itemMenu.addActionListener(evento);
	}
	
	public void adicionarItemMenu(PopupMenu menu, MenuItem item) {
		menu.add(item);
	}
	
	public void adicionarSeparadorItemMenu(PopupMenu menu) {
		menu.addSeparator();
	}

	public void setToolTip(TrayIcon trayIcon) {
		trayIcon.setToolTip("Bitcoin Watcher");
	}

	public void setAutoDimensionamento(TrayIcon trayIcon, boolean autoDimensionamento) {
		trayIcon.setImageAutoSize(autoDimensionamento);
	}
	
	public void exibirNotificacao(String titulo, String stringBuilder, TrayIcon trayIcon, MessageType tipoMensagem) {
		trayIcon.displayMessage(titulo, stringBuilder, tipoMensagem);
	}
	
	public boolean suportaNotificacao() {
		return SystemTray.isSupported();
	}
}
