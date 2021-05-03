package groff.monitorBitcoin.model;

import java.awt.Dimension;
import java.util.List;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

import groff.monitorBitcoin.model.entity.DadosGraficoVO;

public class Graficos {

	public JFreeChart criarGraficoLinhas(String tituloGrafico, String tituloEixoX, String tituloEixoY,
			CategoryDataset dataset, boolean ativarLegendas) {

		return ChartFactory.createLineChart(tituloGrafico, tituloEixoX, tituloEixoY, dataset,
				PlotOrientation.HORIZONTAL, ativarLegendas, true, false);
	}
	
	public ChartPanel criarChartPanel(JFreeChart chart, Dimension preferredSize) {
		
		ChartPanel chartPanel = new ChartPanel(chart);
		chartPanel.setPreferredSize(preferredSize);
		
		return chartPanel;
	}
	
	public DefaultCategoryDataset criarBaseDados(List<DadosGraficoVO> listaDadosGrafico ) {
		
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		
		for (DadosGraficoVO dadosGraficoVO : listaDadosGrafico) {
			dataset.addValue(dadosGraficoVO.getValor(), dadosGraficoVO.getChaveLinha(), dadosGraficoVO.getChaveColuna());			
		}
		
		return dataset;
	}
	
	public void inserirValorGraficoLinha() {
		
	}

}
