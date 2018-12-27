package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
/**
 * 
 * @author donm
 *
 */
final class Situation{
	static int novel;
	static int computer;
	static int magazine;
	static int history;
	static int other;
	final static void operate() throws SQLException{
		Connection connection=C3p0.getConnection();
		PreparedStatement preparedStatement=connection.prepareStatement("CALL scantype");
		ResultSet resultSet=preparedStatement.executeQuery();
		while(resultSet.next()) {
			int cnt=resultSet.getInt(1);
			String string=resultSet.getString(2);
			switch (string) {
			case "其他":
				other=cnt;
				break;
			case "历史":
				history=cnt;
				break;
			case "小说":
				novel=cnt;
				break;
			case "杂志":
				magazine=cnt;
				break;
			default:
				computer=cnt;
				break;
			}
		}
		connection.close();
		final Stage stage=new Stage();
		int sum=computer+magazine+novel+history+other;
		int a=(int) Math.floor(computer*1.0/sum*100);
		int b=(int) Math.floor(novel*1.0/sum*100);
		int c=(int) Math.floor(magazine*1.0/sum*100);
		int d=(int) Math.floor(history*1.0/sum*100);
		Scene scene = new Scene(new Group());
		ObservableList<PieChart.Data> pieChartData =
                FXCollections.observableArrayList(
                new PieChart.Data("计算机", a),
                new PieChart.Data("小说", b),
                new PieChart.Data("杂志", c),
		        new PieChart.Data("历史", d),
		        new PieChart.Data("其他", 100-a-b-c-d));
        final PieChart chart = new PieChart(pieChartData);
        final Label caption = new Label("");
        caption.setTextFill(Color.DARKORANGE);
        caption.setStyle("-fx-font: 24 arial;");
        for (final PieChart.Data data : chart.getData()) {
            data.getNode().addEventHandler(MouseEvent.MOUSE_PRESSED,
                new EventHandler<MouseEvent>() {
                    @Override public void handle(MouseEvent e) {
                        caption.setTranslateX(e.getSceneX());
                        caption.setTranslateY(e.getSceneY());
                        caption.setText(String.valueOf(data.getPieValue()) + "%");
                     }
                });
        }
		((Group) scene.getRoot()).getChildren().addAll(chart,caption);
		stage.setScene(scene);
		scene.getStylesheets().add(Login.class.getResource("application.css").toExternalForm());
		stage.show();
	}
}