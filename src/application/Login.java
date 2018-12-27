package application;

import java.sql.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * 登录/注册
 * 
 * @author donm
 *
 */
final class Login {
	final static void Operate() {
		final Stage primaryStage=new Stage();
		primaryStage.setTitle("登录入口");
		TextField usernameField = new TextField();
		usernameField.setPromptText("用户名");
		PasswordField passwordField = new PasswordField();
		passwordField.setPromptText("密码");
		Hyperlink hyperlink=new Hyperlink("注册帐号");
		hyperlink.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				try {
					Register.register();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
		});
		Hyperlink hyperlink2=new Hyperlink("忘记密码");
		hyperlink2.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				Alert forget = new Alert(AlertType.INFORMATION);
				forget.setGraphic(null);
				forget.setHeaderText("");
				Pane pane = new Pane();
				TextField textField=new TextField();
				textField.setPromptText("请输入新密码");
				pane.getChildren().add(textField);
				forget.getDialogPane().getChildren().add(pane);
				forget.showAndWait().ifPresent(response -> {
				     if (response == ButtonType.OK) {
				         try {
							Connection connection=C3p0.getConnection();
							PreparedStatement preparedStatement=connection.prepareStatement("SELECT changepassword(?,?)");
							preparedStatement.setString(1, usernameField.getText());
							preparedStatement.setString(2, textField.getText());
							ResultSet resultSet=preparedStatement.executeQuery();
							while(resultSet.next()) {
								String info=resultSet.getString(1);
								variable.infoHint(info);
								if(info.equals("成功")) {
									passwordField.setText(textField.getText());
								}
							}
							connection.close();
						} catch (SQLException e) {
							e.printStackTrace();
						}
				         
				     }
				 });
			}
		});
		HBox hBox = new HBox();
		hBox.getChildren().addAll(Constant.labelUser, usernameField,hyperlink);
		HBox hBox2 = new HBox();
		hBox2.getChildren().addAll(Constant.labelPassword, passwordField,hyperlink2);
		Hyperlink login=new Hyperlink("",Constant.login);
		login.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				try {
					Connection connection = C3p0.getConnection();
					PreparedStatement preparedStatement=connection.prepareStatement("SELECT checkreader(?,?)");
					preparedStatement.setString(1, usernameField.getText());
					preparedStatement.setString(2, passwordField.getText());
					ResultSet resultSet=preparedStatement.executeQuery();
					while(resultSet.next()) {
						String string=resultSet.getString(1);
						if(string.contains("错误")||string.contains("不存在")) {
							variable.infoHint(string);
						}
						else {
							if(string.charAt(0)=='f') {
								variable.myPicture=string;
							}
							else {
								variable.myPicture=string.substring(3);
								Main.tab6.setDisable(false);
							}
							variable.infoHint("登录成功");
							primaryStage.close();
							variable.isLogin=true;
							variable.username=usernameField.getText();
							Main.login.setGraphic(new ImageView(new Image(variable.myPicture)));
							Main.login.setText(variable.username);
							Main.userExit.setVisible(true);
						}
					}
					connection.close();
					
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
		});
		VBox vBox = new VBox();
		vBox.getChildren().add(0, Constant.labelHead);
		vBox.getChildren().add(1, hBox);
		vBox.getChildren().add(2, hBox2);
		vBox.getChildren().add(3, login);
		GridPane gridPane = new GridPane();
		gridPane.getChildren().add(vBox);
		Scene scene = new Scene(gridPane);
		scene.getStylesheets().add(Login.class.getResource("application.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	final static void doLogin() {
		
	}
}
