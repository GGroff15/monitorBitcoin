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
		TrayIcon trayIcon = new TrayIcon(image, "Bitcoin Watcher Notif", popup);
		try {
			tray.add(trayIcon);
		} catch (AWTException e) {
			e.printStackTrace();
		}
		return trayIcon;
	}
	
	public TrayIcon criarTrayIcon(SystemTray tray, Image image) {
		TrayIcon trayIcon = new TrayIcon(image, "Bitcoin Watcher Notif");
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
	
	public void exibirNotificacao(String titulo, StringBuilder stringBuilder, TrayIcon trayIcon) {
		trayIcon.displayMessage(titulo, stringBuilder.toString(), MessageType.INFO);
	}
	
	public boolean suportaNotificacao() {
		return SystemTray.isSupported();
	}
}
