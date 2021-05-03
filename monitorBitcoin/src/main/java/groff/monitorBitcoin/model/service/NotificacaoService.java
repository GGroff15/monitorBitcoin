 package groff.monitorBitcoin.model.service;

import java.awt.Image;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.TrayIcon;

import groff.monitorBitcoin.model.Listeners;
import groff.monitorBitcoin.model.Notificacao;
import groff.monitorBitcoin.model.entity.Constantes;
import groff.monitorBitcoin.model.entity.DadosBitcoinVO;
import groff.monitorBitcoin.util.Utils;

public class NotificacaoService {
	private static final String RESOURCES_BITCOIN_LOGO_PNG = "resources/bitcoin-logo.png";
	Notificacao notificacao = new Notificacao();

	public void exibirNotificacao(String titulo, DadosBitcoinVO dadosBitcoin) {

		StringBuilder stringBuilder = montaMensagemNotificacao(dadosBitcoin);

		if (notificacao.suportaNotificacao()) {
			SystemTray tray = notificacao.criarSystemTray();
			Image image = notificacao.criarImagemNotificacao(RESOURCES_BITCOIN_LOGO_PNG);
			
			TrayIcon trayIcon = notificacao.criarTrayIcon(tray, image);
			notificacao.setAutoDimensionamento(trayIcon, true);
			notificacao.setToolTip(trayIcon);
			notificacao.exibirNotificacao(titulo, stringBuilder, trayIcon);
		} else {
			System.err.println("System tray not supported!");
		}
	}
	
	public void criarIconeBandeja() {
		
		if (notificacao.suportaNotificacao()) {
			
			Listeners evento = new Listeners();
			
			SystemTray tray = notificacao.criarSystemTray();
			Image image = notificacao.criarImagemNotificacao(RESOURCES_BITCOIN_LOGO_PNG);
			
			PopupMenu popup = notificacao.criarMenuPopup("Opções");
			//MenuItem abrir = notificacao.criarItemMenu("Abrir");
			//notificacao.adicionarEventoItemMenu(abrir, evento.exibirListener());
			//notificacao.adicionarItemMenu(popup, abrir);
			
			//notificacao.adicionarSeparadorItemMenu(popup);
			
			MenuItem encerrar = notificacao.criarItemMenu("Encerrar");
			notificacao.adicionarEventoItemMenu(encerrar, evento.sairListener());
			notificacao.adicionarItemMenu(popup, encerrar);
			
			TrayIcon trayIcon = notificacao.criarTrayIcon(tray, image, popup);
			notificacao.setAutoDimensionamento(trayIcon, true);
			notificacao.setToolTip(trayIcon);
		} else {
			System.err.println("System tray not supported!");
		}
		
	}
	
	public StringBuilder montaMensagemNotificacao(DadosBitcoinVO dadosBitcoin) {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(Constantes.DATA);
		stringBuilder.append(Utils.formatarDiaMesAno(dadosBitcoin.getData()));
		stringBuilder.append(Constantes.ESPACO);
		stringBuilder.append(Utils.formatarHoraMinutoSegundo(dadosBitcoin.getData()));
		stringBuilder.append(Constantes.QUEBRA_LINHA);
		stringBuilder.append(Constantes.VALOR);
		stringBuilder.append(dadosBitcoin.getMoeda());
		stringBuilder.append(Constantes.ESPACO);
		stringBuilder.append(Utils.formatarFloatDuasCasasDecimais(dadosBitcoin.getValor()));
		stringBuilder.append(Constantes.QUEBRA_LINHA);
		stringBuilder.append(Constantes.TENDENCIA);
		stringBuilder.append(dadosBitcoin.getTendencia());
		return stringBuilder;
	}
}
