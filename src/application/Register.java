package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * 注册
 * 
 * @author donm
 */
final class Register {
	final static void register() {
		final Stage primaryStage = new Stage();
		variable.myPicture = "file:image/0.png";
		Hyperlink photo = new Hyperlink("修改头像", Constant.registerImage);
		photo.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				Alert position = new Alert(Alert.AlertType.CONFIRMATION);
				position.setHeaderText("");
				position.setGraphic(null);
				Pane pane = new Pane();
				TextField textField = new TextField();
				textField.setText("file:image/0.png");
				textField.setPrefWidth(300.0);
				pane.getChildren().add(textField);
				position.getDialogPane().getChildren().add(pane);
				position.showAndWait().ifPresent(response -> {
					if (response == ButtonType.OK) {
						variable.myPicture = textField.getText();
						photo.setGraphic(new ImageView(new Image(variable.myPicture)));
						position.close();
					}
				});
			}
		});
		HBox hBox = new HBox(photo);
		hBox.setAlignment(Pos.TOP_CENTER);
		TextField username = new TextField();
		username.setPromptText("用户名");
		PasswordField password = new PasswordField();
		password.setPromptText("密码");
		PasswordField passAgain = new PasswordField();
		passAgain.setPromptText("确认密码");
		Hyperlink register = new Hyperlink("", Constant.register);
		register.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				if (!password.getText().equals(passAgain.getText())) {
					variable.infoHint("密码不一致");
				}
				else {
					try {
						Connection connection = C3p0.getConnection();
						PreparedStatement preparedStatement = connection.prepareStatement("SELECT registeruser(?,?,?)");
						preparedStatement.setString(1, username.getText());
						preparedStatement.setString(2, password.getText());
						preparedStatement.setString(3, variable.myPicture);
						ResultSet resultSet = preparedStatement.executeQuery();
						while (resultSet.next()) {
							String info=resultSet.getString(1);
							if (info.contains("成功")) {
								variable.username = username.getText();
								primaryStage.close();
							}
							variable.infoHint(info);
						}
						connection.close();

					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			}
		});
		Scene scene = new Scene(new VBox(hBox, username, password, passAgain, register));
		scene.getStylesheets().add(Register.class.getResource("application.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.show();
	}
}

