package chart;
import java.awt.Font;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.StandardChartTheme;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;

public class ChartUtil {
	/**
	 * �������ݼ���
	 * 
	 * @return CategoryDataset����
	 */
	public static CategoryDataset createDataSet() {
		// ʵ����DefaultCategoryDataset����
		DefaultCategoryDataset dataSet = new DefaultCategoryDataset();
		// �����ݼ������������
		dataSet.addValue(500, "ͼ������", "��ѧ");
		dataSet.addValue(100, "ͼ������", "ҽҩ");
		dataSet.addValue(400, "ͼ������", "��ͯ");
		dataSet.addValue(900, "ͼ������", "��е");
		dataSet.addValue(200, "ͼ������", "����");

		return dataSet;
	}

	/**
	 *����JFreeChart����
	 * 
	 * @return JFreeChart����
	 */
	public static JFreeChart createChart() {
		StandardChartTheme standardChartTheme = new StandardChartTheme("CN"); // ����������ʽ
		standardChartTheme.setExtraLargeFont(new Font("����", Font.BOLD, 20)); // ���ñ�������
		standardChartTheme.setRegularFont(new Font("����", Font.PLAIN, 15)); // ����ͼ��������
		standardChartTheme.setLargeFont(new Font("����", Font.PLAIN, 15)); // �������������
		ChartFactory.setChartTheme(standardChartTheme); // ����������ʽ
		// ͨ��ChartFactory����JFreeChart
		JFreeChart chart = ChartFactory.createBarChart3D("һ��ͼ������ͳ��", // ͼ�����
				"ͼ�����", // �������
				"����������", // �������
				createDataSet(), // ���ݼ���
				PlotOrientation.VERTICAL, // ͼ����
				false, // �Ƿ���ʾͼ����ʶ
				false, // �Ƿ���ʾtooltips
				false); // �Ƿ�֧�ֳ�����
		return chart;
	}

}