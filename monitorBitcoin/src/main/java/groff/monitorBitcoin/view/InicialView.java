package groff.monitorBitcoin.view;

import java.awt.Frame;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Text;
import org.jfree.chart.JFreeChart;

import groff.monitorBitcoin.controller.impl.InicialController;

public class InicialView {

	private InicialController controller;
	private Composite compositeTabela;
	private Composite compositeControle;
	private Display display;
	private Shell shell;
	private Label text;
	private Table tabela;
	private Frame frameGrafico;
	private JFreeChart grafico;
	private Button btnIniciar;
	private Composite compositeValores;
	private Text textMaximo;
	private Text textMinimo;
	private Text textIntervalo;

	private boolean executarTreadParalela = false;
	private Combo comboMoedas;

	public InicialView() {
		this.controller = InicialController.getInstance();
	}

	/**
	 * @wbp.parser.entryPoint
	 */
	public void exibirTela() {
		criarTela();

		criarControles();

		criarTabela();

		controlarExibicaoTela();
	}

	private void controlarExibicaoTela() {
		shell.open();

		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		display.close();
	}

	private void criarTela() {
		display = Display.getDefault();
		shell = new Shell(display);
		shell.setSize(500, 600);
		shell.setLayout(new RowLayout(SWT.VERTICAL));
	}

	private void criarControles() {
		compositeControle = new Composite(shell, SWT.NONE);
		compositeControle.setLayoutData(new RowData(476, 70));
		compositeControle.setLayout(new RowLayout(SWT.HORIZONTAL));

		criarBotaoIniciarParar();

		compositeValores = new Composite(compositeControle, SWT.NONE);
		compositeValores.setLayoutData(new RowData(180, 63));

		criarCampoValorMaximo();

		criarCampoValorMinimo();

		Composite compositeParametrosAPI = new Composite(compositeControle, SWT.NONE);
		compositeParametrosAPI.setLayoutData(new RowData(227, 63));

		criarComboMoedas(compositeParametrosAPI);

		criarCampoIntervalo(compositeParametrosAPI);
	}

	private void criarCampoIntervalo(Composite compositeParametrosAPI) {
		Label lblIntervalo = new Label(compositeParametrosAPI, SWT.NONE);
		lblIntervalo.setBounds(10, 10, 130, 15);
		lblIntervalo.setText("Intervalo notificação (s)");

		textIntervalo = new Text(compositeParametrosAPI, SWT.BORDER);
		textIntervalo.setBounds(146, 3, 71, 21);
		textIntervalo.setText("60");
	}

	private void criarComboMoedas(Composite compositeParametrosAPI) {
		Label labelMoeda = new Label(compositeParametrosAPI, SWT.NONE);
		labelMoeda.setBounds(10, 38, 46, 15);
		labelMoeda.setText("Moeda");

		comboMoedas = new Combo(compositeParametrosAPI, SWT.NONE);
		comboMoedas.setBounds(61, 31, 156, 23);
		comboMoedas.setItems("USD", "GPB", "EUR");
		comboMoedas.select(0);
	}

	private void criarCampoValorMinimo() {
		Label lblMinimo = new Label(compositeValores, SWT.NONE);
		lblMinimo.setBounds(10, 35, 76, 15);
		lblMinimo.setText("Valor Minimo");

		textMinimo = new Text(compositeValores, SWT.BORDER);
		textMinimo.setBounds(92, 32, 76, 21);
	}

	private void criarCampoValorMaximo() {
		Label lblMaximo = new Label(compositeValores, SWT.NONE);
		lblMaximo.setBounds(10, 10, 76, 15);
		lblMaximo.setText("Valor maximo");

		textMaximo = new Text(compositeValores, SWT.BORDER);
		textMaximo.setBounds(92, 7, 76, 21);
	}

	private void criarBotaoIniciarParar() {
		btnIniciar = new Button(compositeControle, SWT.NONE);
		btnIniciar.setLayoutData(new RowData(55, 63));
		btnIniciar.setText("Iniciar");
		btnIniciar.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				controlarThreadParalela();
			}
		});
	}

	private void criarTabela() {
		compositeTabela = new Composite(shell, SWT.NONE);
		compositeTabela.setLayoutData(new RowData(477, 481));
		compositeTabela.setLayout(new GridLayout());

		tabela = new Table(compositeTabela, SWT.MULTI | SWT.BORDER | SWT.FULL_SELECTION);
		tabela.setLinesVisible(true);
		tabela.setHeaderVisible(true);

		GridData gridData = new GridData(SWT.FILL, SWT.FILL, true, true);
		gridData.heightHint = 200;

		tabela.setLayoutData(gridData);

		String[] titulosColunas = { "Data", "Hora", "Moeda", "Valor", "Tendencia" };
		for (String tituloColuna : titulosColunas) {
			TableColumn coluna = new TableColumn(tabela, SWT.NONE);
			coluna.setText(tituloColuna);
		}
	}

	private void controlarThreadParalela() {
		controller.controlarThreadAPI(this);
	}

	public Display getDisplay() {
		return display;
	}

	public void setDisplay(Display display) {
		this.display = display;
	}

	public Shell getShell() {
		return shell;
	}

	public void setShell(Shell shell) {
		this.shell = shell;
	}

	public Label getText() {
		return text;
	}

	public void setText(Label text) {
		this.text = text;
	}

	public Composite getCompositeTabela() {
		return compositeTabela;
	}

	public void setCompositeTabela(Composite colunaEsquerda) {
		this.compositeTabela = colunaEsquerda;
	}

	public Table getTabela() {
		return tabela;
	}

	public void setTabela(Table tabela) {
		this.tabela = tabela;
	}

	public Frame getFrameGrafico() {
		return frameGrafico;
	}

	public void setFrameGrafico(Frame frameGrafico) {
		this.frameGrafico = frameGrafico;
	}

	public JFreeChart getGrafico() {
		return grafico;
	}

	public void setGrafico(JFreeChart grafico) {
		this.grafico = grafico;
	}

	public Composite getCompositeControle() {
		return compositeControle;
	}

	public void setCompositeControle(Composite compositeControle) {
		this.compositeControle = compositeControle;
	}

	public boolean isExecutarTreadParalela() {
		return executarTreadParalela;
	}

	public void setExecutarTreadParalela(boolean executarTreadParalela) {
		this.executarTreadParalela = executarTreadParalela;
	}

	public InicialController getController() {
		return controller;
	}

	public void setController(InicialController controller) {
		this.controller = controller;
	}

	public Button getBtnIniciar() {
		return btnIniciar;
	}

	public void setBtnIniciar(Button btnIniciar) {
		this.btnIniciar = btnIniciar;
	}

	public Composite getCompositeValores() {
		return compositeValores;
	}

	public void setCompositeValores(Composite compositeValores) {
		this.compositeValores = compositeValores;
	}

	public Text getTextMaximo() {
		return textMaximo;
	}

	public void setTextMaximo(Text textMaximo) {
		this.textMaximo = textMaximo;
	}

	public Text getTextMinimo() {
		return textMinimo;
	}

	public void setTextMinimo(Text textMinimo) {
		this.textMinimo = textMinimo;
	}

	public Text getTextIntervalo() {
		return textIntervalo;
	}

	public void setTextIntervalo(Text textIntervalo) {
		this.textIntervalo = textIntervalo;
	}

	public Combo getComboMoedas() {
		return comboMoedas;
	}

	public void setComboMoedas(Combo comboMoedas) {
		this.comboMoedas = comboMoedas;
	}
}
