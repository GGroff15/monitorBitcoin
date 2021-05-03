package groff.monitorBitcoin.controller.impl;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.TableItem;

import groff.monitorBitcoin.Principal;
import groff.monitorBitcoin.controller.IController;
import groff.monitorBitcoin.model.entity.Constantes;
import groff.monitorBitcoin.model.entity.DadosBitcoinVO;
import groff.monitorBitcoin.model.service.NotificacaoService;
import groff.monitorBitcoin.util.Utils;
import groff.monitorBitcoin.view.InicialView;

public class InicialController implements IController {

	private static volatile InicialController instance = null;

	private InicialView inicialView;
	Principal principal = new Principal();
	private Timer timer;
	
	private InicialController() {
	}
	
	public static InicialController getInstance() {
		
		InicialController result = instance;
		if (result != null) {
			return result;
		}
		
		synchronized (InicialController.class) {
			if (instance == null) {
				instance = new InicialController();
			}
			return instance;			
		}
	}

	public void iniciarTela() {
		NotificacaoService notificacaoService = new NotificacaoService();
		notificacaoService.criarIconeBandeja();
		this.inicialView = new InicialView();
		inicialView.exibirTela();
	}

	public void executarChamadasApiBitcoin(InicialView view) {
		this.inicialView = view;
		
		int intervaloSegundos = Integer.valueOf(this.inicialView.getTextIntervalo().getText());
		int intervaloMilisegundos = intervaloSegundos * 1000;

		timer = new Timer();
		timer.schedule(new TimerTask() {

			@Override
			public void run() {
				System.out.println("Executando thread paralela " + new Date());
				System.out.println("Chamada API");
				inicialView.getDisplay().asyncExec(new Runnable() {
					@Override
					public void run() {
						
						int indiceItemSelecionado = inicialView.getComboMoedas().getSelectionIndex();
						String moeda = inicialView.getComboMoedas().getItem(indiceItemSelecionado);
						float valorMaximo = Float.valueOf(inicialView.getTextMaximo().getText());
						float valorMinimo = Float.valueOf(inicialView.getTextMinimo().getText());

						DadosBitcoinVO dadosBitcoin = principal.iniciar(moeda, valorMaximo, valorMinimo);
						inserirItemLista(inicialView, dadosBitcoin);

					}
				});					
				System.out.println("Fim execucao thread paralela");
			}
		}, 0, intervaloMilisegundos);
	}

	public void inserirItemLista(InicialView view, DadosBitcoinVO dadosBitcoin) {
		this.inicialView = view;

		TableItem itemTabela = new TableItem(inicialView.getTabela(), SWT.NONE);
		itemTabela.setText(Constantes.INDICE_COLUNA_DATA, Utils.formatarDiaMesAno(dadosBitcoin.getData()));
		itemTabela.setText(Constantes.INDICE_COLUNA_HORA, Utils.formatarHoraMinutoSegundo(dadosBitcoin.getData()));
		itemTabela.setText(Constantes.INDICE_COLUNA_MOEDA, dadosBitcoin.getMoeda());
		itemTabela.setText(Constantes.INDICE_COLUNA_VALOR, Utils.formatarFloatDuasCasasDecimais(dadosBitcoin.getValor()));
		itemTabela.setText(Constantes.INDICE_COLUNA_TENDENCIA, dadosBitcoin.getTendencia());

		for (int i = 0; i < 5; i++) {
			view.getTabela().getColumn(i).pack();
		}

		view.getShell().layout();
	}

	public void encerrarThreadParalela() {
		this.timer.cancel();
		this.timer.purge();
	}
	
	public void controlarThreadAPI(InicialView view) {
		this.inicialView = view;
		
		if (inicialView.isExecutarTreadParalela()) {
			inicialView.setExecutarTreadParalela(false);
			inicialView.getBtnIniciar().setText("Iniciar");
			encerrarThreadParalela();
		} else {
			inicialView.setExecutarTreadParalela(true);
			inicialView.getBtnIniciar().setText("Parar");
			executarChamadasApiBitcoin(view);
		}
	}

	public InicialView getInicialView() {
		return inicialView;
	}

	public void setInicialView(InicialView view) {
		this.inicialView = view;
	}

	public void exbirTela() {
		this.inicialView.getShell();
	}
}
