import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class SalesCharts extends JFrame {

    public SalesCharts(String title) {
        super(title);

        // Create charts from database
        JFreeChart lineChart = createSalesLineChart();
        JFreeChart barChart = createBestSellersBarChart();
        JFreeChart pieChart = createMonthlySalesPieChart();

        // Chart panels
        ChartPanel lineChartPanel = new ChartPanel(lineChart);
        ChartPanel barChartPanel = new ChartPanel(barChart);
        ChartPanel pieChartPanel = new ChartPanel(pieChart);

        // Layout for the charts
        JPanel chartPanel = new JPanel(new GridLayout(2, 2));
        chartPanel.add(lineChartPanel);
        chartPanel.add(barChartPanel);
        chartPanel.add(pieChartPanel);

        // Add to JFrame
        setContentPane(chartPanel);
    }

    // ===================== LINE CHART: SALES OVER TIME =====================
    private JFreeChart createSalesLineChart() {
        XYSeries salesSeries = new XYSeries("Sales");

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/your_database", "username", "password");
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT week, sales FROM sales_data ORDER BY week")) {

            while (rs.next()) {
                salesSeries.add(rs.getInt("week"), rs.getInt("sales"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        XYSeriesCollection dataset = new XYSeriesCollection(salesSeries);
        JFreeChart chart = ChartFactory.createXYLineChart(
                "Weekly Sales", "Week", "Sales", dataset,
                PlotOrientation.VERTICAL, true, true, false);

        XYPlot plot = chart.getXYPlot();
        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
        renderer.setSeriesPaint(0, Color.BLUE);
        plot.setRenderer(renderer);

        return chart;
    }

    // ===================== BAR CHART: BEST-SELLING PRODUCTS =====================
    private JFreeChart createBestSellersBarChart() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/your_database", "username", "password");
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT product_name, total_sales FROM products ORDER BY total_sales DESC LIMIT 5")) {

            while (rs.next()) {
                dataset.addValue(rs.getInt("total_sales"), "Best Sellers", rs.getString("product_name"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        JFreeChart chart = ChartFactory.createBarChart(
                "Best-Selling Products", "Product", "Sales", dataset,
                PlotOrientation.VERTICAL, true, true, false);

        CategoryPlot plot = chart.getCategoryPlot();
        BarRenderer renderer = (BarRenderer) plot.getRenderer();
        renderer.setSeriesPaint(0, Color.RED);

        return chart;
    }

    // ===================== PIE CHART: SALES DISTRIBUTION =====================
    private JFreeChart createMonthlySalesPieChart() {
        DefaultPieDataset dataset = new DefaultPieDataset();

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/your_database", "username", "password");
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT month, total_sales FROM monthly_sales")) {

            while (rs.next()) {
                dataset.setValue(rs.getString("month"), rs.getInt("total_sales"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        JFreeChart chart = ChartFactory.createPieChart(
                "Monthly Sales", dataset, true, true, false);

        PiePlot plot = (PiePlot) chart.getPlot();
        plot.setSectionPaint("January", Color.YELLOW);
        plot.setSectionPaint("February", Color.GREEN);
        plot.setSectionPaint("March", Color.ORANGE);
        plot.setSectionPaint("April", Color.PINK);
        plot.setSectionPaint("May", Color.CYAN);

        return chart;
    }

    // ===================== MAIN METHOD =====================
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            SalesCharts example = new SalesCharts("Sales Analysis Charts");
            example.setSize(1000, 800);
            example.setLocationRelativeTo(null);
            example.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            example.setVisible(true);
        });
    }
}
