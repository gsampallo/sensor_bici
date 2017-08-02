import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import java.awt.*;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.dial.*;
import org.jfree.data.general.DefaultValueDataset;
import org.jfree.ui.GradientPaintTransformType;
import org.jfree.ui.StandardGradientPaintTransformer;

public class IndicadorFrame extends JFrame implements ActionListener {

	DefaultValueDataset dataset1,dataset2;

	public IndicadorFrame() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.getContentPane().add(getContenido());
		this.setResizable(false);

		pack();
		setVisible(true);
	}

	private JPanel getContenido() {
		JPanel panel = new JPanel(new BorderLayout());

		dataset1 = new DefaultValueDataset(10D);
        dataset2 = new DefaultValueDataset(50D);

        DialPlot dialplot = new DialPlot();

        dialplot.setView(0.0D, 0.0D, 1.0D, 1.0D);
        dialplot.setDataset(0, dataset1);
        dialplot.setDataset(1, dataset2);

        StandardDialFrame standarddialframe = new StandardDialFrame();
        standarddialframe.setBackgroundPaint(Color.lightGray);
        standarddialframe.setForegroundPaint(Color.darkGray);
        dialplot.setDialFrame(standarddialframe);

        GradientPaint gradientpaint = new GradientPaint(new Point(), new Color(255, 255, 255), new Point(), new Color(170, 170, 220));
        DialBackground dialbackground = new DialBackground(gradientpaint);

        dialbackground.setGradientPaintTransformer(new StandardGradientPaintTransformer(GradientPaintTransformType.VERTICAL));
        dialplot.setBackground(dialbackground);

        DialTextAnnotation dialtextannotation = new DialTextAnnotation("Velocidad");
        dialtextannotation.setFont(new Font("Dialog", 1, 14));
        dialtextannotation.setRadius(0.69999999999999996D);
        dialplot.addLayer(dialtextannotation);

        DialValueIndicator dialvalueindicator = new DialValueIndicator(0);
        dialvalueindicator.setFont(new Font("Dialog", 0, 10));
        dialvalueindicator.setOutlinePaint(Color.darkGray);
        dialvalueindicator.setRadius(0.59999999999999998D);
        dialvalueindicator.setAngle(-103D);
        dialplot.addLayer(dialvalueindicator);

        DialValueIndicator dialvalueindicator1 = new DialValueIndicator(1);
        dialvalueindicator1.setFont(new Font("Dialog", 0, 10));
        dialvalueindicator1.setOutlinePaint(Color.red);
        dialvalueindicator1.setRadius(0.59999999999999998D);
        dialvalueindicator1.setAngle(-77D);
        dialplot.addLayer(dialvalueindicator1);

        StandardDialScale standarddialscale = new StandardDialScale(0D, 60D, -120D, -300D, 10D, 4);
        standarddialscale.setTickRadius(0.88D);
        standarddialscale.setTickLabelOffset(0.14999999999999999D);
        standarddialscale.setTickLabelFont(new Font("Dialog", 0, 14));
        dialplot.addScale(0, standarddialscale);


        StandardDialScale standarddialscale1 = new StandardDialScale(0.0D, 60D, -120D, -300D, 10D, 4);
        standarddialscale1.setTickRadius(0.5D);
        standarddialscale1.setTickLabelOffset(0.14999999999999999D);
        standarddialscale1.setTickLabelFont(new Font("Dialog", 0, 10));
        standarddialscale1.setMajorTickPaint(Color.red);
        standarddialscale1.setMinorTickPaint(Color.red);
        dialplot.addScale(1, standarddialscale1);

        dialplot.mapDatasetToScale(1, 1);

        StandardDialRange standarddialrange = new StandardDialRange(90D, 100D, Color.blue);
        standarddialrange.setScaleIndex(1);
        standarddialrange.setInnerRadius(0.58999999999999997D);
        standarddialrange.setOuterRadius(0.58999999999999997D);
        dialplot.addLayer(standarddialrange);

        org.jfree.chart.plot.dial.DialPointer.Pin pin = new org.jfree.chart.plot.dial.DialPointer.Pin(1);
        pin.setRadius(0.55000000000000004D);
        dialplot.addPointer(pin);

        org.jfree.chart.plot.dial.DialPointer.Pointer pointer = new org.jfree.chart.plot.dial.DialPointer.Pointer(0);
        dialplot.addPointer(pointer);

        DialCap dialcap = new DialCap();
        dialcap.setRadius(0.10000000000000001D);
        dialplot.setCap(dialcap);

        JFreeChart jfreechart = new JFreeChart(dialplot);

        ChartPanel chartpanel = new ChartPanel(jfreechart);
        chartpanel.setPreferredSize(new Dimension(400, 400));

        panel.add(chartpanel);

		return panel;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub

	}

	public void setValor(int valor) {
		dataset1.setValue(new Integer(valor));
	}

}
