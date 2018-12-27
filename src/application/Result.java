package application;

import java.util.Map;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.MapValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.StringConverter;
/**
 * 
 * @author donm
 *
 */
@SuppressWarnings({ "rawtypes", "unchecked" })
final class Result {
	final static void operate() {
		final Stage stage=new Stage();
		Scene scene = new Scene(new Group());
		ScrollPane scrollPane = new ScrollPane();
		stage.setTitle("搜索结果");
		TableColumn<Map, String> id = new TableColumn<>("书号");
		TableColumn<Map, String> name = new TableColumn<>("书名");
		TableColumn<Map, String> author = new TableColumn<>("作者");
		TableColumn<Map, String> place = new TableColumn<>("出版社");
		TableColumn<Map, String> appear = new TableColumn<>("出版日");
		TableColumn<Map, String> type = new TableColumn<>("类型");
		TableColumn<Map, String> isBorrowed = new TableColumn<>("是否被借");
		id.setCellValueFactory(new MapValueFactory("书号"));
		id.setMinWidth(130);
		name.setCellValueFactory(new MapValueFactory("书名"));
		name.setMinWidth(130);
		author.setCellValueFactory(new MapValueFactory("作者"));
		author.setMinWidth(130);
		appear.setCellValueFactory(new MapValueFactory("出版社"));
		appear.setMinWidth(130);
		place.setCellValueFactory(new MapValueFactory("出版日"));
		place.setMinWidth(130);
		type.setCellValueFactory(new MapValueFactory("类型"));
		type.setMinWidth(130);
		isBorrowed.setCellValueFactory(new MapValueFactory("是否被借"));
		isBorrowed.setMinWidth(130);
		TableView tableView = new TableView<>(Operation.allData);
		tableView.getSelectionModel().setCellSelectionEnabled(true);
		tableView.getColumns().setAll(id,name,author,appear,place,type,isBorrowed);
		Callback<TableColumn<Map, String>, TableCell<Map, String>> cellFactoryForMap = (
				TableColumn<Map, String> p) -> new TextFieldTableCell(new StringConverter() {
					@Override
					public String toString(Object t) {
						return t.toString();
					}

					@Override
					public Object fromString(String string) {
						return string;
					}
				});
		id.setCellFactory(cellFactoryForMap);
		name.setCellFactory(cellFactoryForMap);
		author.setCellFactory(cellFactoryForMap);
		appear.setCellFactory(cellFactoryForMap);
		place.setCellFactory(cellFactoryForMap);
		type.setCellFactory(cellFactoryForMap);
		isBorrowed.setCellFactory(cellFactoryForMap);
		final VBox vbox = new VBox();
		vbox.setSpacing(5);
		vbox.setPadding(new Insets(10, 0, 0, 10));
		vbox.getChildren().addAll(tableView);
		scrollPane.setContent(vbox);
		((Group) scene.getRoot()).getChildren().addAll(scrollPane);
		scene.getStylesheets().add(Result.class.getResource("application.css").toExternalForm());
		stage.setScene(scene);
		stage.getIcons().add(Constant.result);
		stage.show();
	}
}
@SuppressWarnings({ "rawtypes", "unchecked" })
class BorrowSituation {
	final static void operate() {
		final Stage stage=new Stage();
		Scene scene = new Scene(new Group());
		ScrollPane scrollPane = new ScrollPane();
		stage.setTitle("搜索结果");
		TableColumn<Map, String> id = new TableColumn<>("书号");
		TableColumn<Map, String> name = new TableColumn<>("书名");
		TableColumn<Map, String> reader = new TableColumn<>("借阅人");
		TableColumn<Map, String> borrowtime = new TableColumn<>("借阅时间");
		id.setCellValueFactory(new MapValueFactory("书号"));
		id.setMinWidth(130);
		name.setCellValueFactory(new MapValueFactory("书名"));
		name.setMinWidth(130);
		reader.setCellValueFactory(new MapValueFactory("借阅人"));
		reader.setMinWidth(130);
		borrowtime.setCellValueFactory(new MapValueFactory("借阅时间"));
		borrowtime.setMinWidth(130);
		TableView tableView = new TableView<>(Operation.allData);
		tableView.getSelectionModel().setCellSelectionEnabled(true);
		tableView.getColumns().setAll(id,name,reader,borrowtime);
		Callback<TableColumn<Map, String>, TableCell<Map, String>> cellFactoryForMap = (
				TableColumn<Map, String> p) -> new TextFieldTableCell(new StringConverter() {
					@Override
					public String toString(Object t) {
						return t.toString();
					}

					@Override
					public Object fromString(String string) {
						return string;
					}
				});
		id.setCellFactory(cellFactoryForMap);
		name.setCellFactory(cellFactoryForMap);
		reader.setCellFactory(cellFactoryForMap);
		borrowtime.setCellFactory(cellFactoryForMap);
		final VBox vbox = new VBox();
		vbox.setSpacing(5);
		vbox.setPadding(new Insets(10, 0, 0, 10));
		vbox.getChildren().addAll(tableView);
		scrollPane.setContent(vbox);
		((Group) scene.getRoot()).getChildren().addAll(scrollPane);
		scene.getStylesheets().add(Result.class.getResource("application.css").toExternalForm());
		stage.setScene(scene);
		stage.getIcons().add(new Image("file:images/result.png"));
		stage.show();
	}
}
@SuppressWarnings({ "rawtypes", "unchecked" })
class timeOutBooks {
	final static void operate(String title) {
		final Stage stage=new Stage();
		Scene scene = new Scene(new Group());
		ScrollPane scrollPane = new ScrollPane();
		stage.setTitle(title);
		TableColumn<Map, String> id = new TableColumn<>("书号");
		TableColumn<Map, String> name = new TableColumn<>("书名");
		TableColumn<Map, String> reader=new TableColumn<>("借阅人");;
		TableColumn<Map, String> borrowtime = new TableColumn<>("借阅时间");
		TableColumn<Map, String> outDays = new TableColumn<>("超出余额");
		id.setCellValueFactory(new MapValueFactory("书号"));
		id.setMinWidth(130);
		name.setCellValueFactory(new MapValueFactory("书名"));
		name.setMinWidth(130);
		reader.setCellValueFactory(new MapValueFactory("借阅人"));
		reader.setMinWidth(130);
		borrowtime.setCellValueFactory(new MapValueFactory("借阅时间"));
		borrowtime.setMinWidth(130);
		outDays.setCellValueFactory(new MapValueFactory("超出余额"));
		outDays.setMinWidth(130);
		TableView tableView = new TableView<>(Operation.allData);
		tableView.getSelectionModel().setCellSelectionEnabled(true);
		tableView.getColumns().setAll(id,name,reader,borrowtime,outDays);
		Callback<TableColumn<Map, String>, TableCell<Map, String>> cellFactoryForMap = (
				TableColumn<Map, String> p) -> new TextFieldTableCell(new StringConverter() {
					@Override
					public String toString(Object t) {
						return t.toString();
					}

					@Override
					public Object fromString(String string) {
						return string;
					}
				});
		id.setCellFactory(cellFactoryForMap);
		name.setCellFactory(cellFactoryForMap);
		reader.setCellFactory(cellFactoryForMap);
		borrowtime.setCellFactory(cellFactoryForMap);
		outDays.setCellFactory(cellFactoryForMap);
		final VBox vbox = new VBox();
		vbox.setSpacing(5);
		vbox.setPadding(new Insets(10, 0, 0, 10));
		vbox.getChildren().addAll(tableView);
		scrollPane.setContent(vbox);
		((Group) scene.getRoot()).getChildren().addAll(scrollPane);
		scene.getStylesheets().add(Result.class.getResource("application.css").toExternalForm());
		stage.setScene(scene);
		stage.getIcons().add(new Image("file:images/result.png"));
		stage.show();
	}
}
@SuppressWarnings({ "rawtypes", "unchecked" })
class OwnTimeOutBooks {
	final static void operate(String title) {
		final Stage stage=new Stage();
		Scene scene = new Scene(new Group());
		ScrollPane scrollPane = new ScrollPane();
		stage.setTitle(title);
		TableColumn<Map, String> id = new TableColumn<>("书号");
		TableColumn<Map, String> name = new TableColumn<>("书名");
		TableColumn<Map, String> borrowtime = new TableColumn<>("借阅时间");
		TableColumn<Map, String> outDays = new TableColumn<>("超出余额");
		id.setCellValueFactory(new MapValueFactory("书号"));
		id.setMinWidth(130);
		name.setCellValueFactory(new MapValueFactory("书名"));
		name.setMinWidth(130);
		borrowtime.setCellValueFactory(new MapValueFactory("借阅时间"));
		borrowtime.setMinWidth(130);
		outDays.setCellValueFactory(new MapValueFactory("超出余额"));
		outDays.setMinWidth(130);
		TableView tableView = new TableView<>(Operation.allData);
		tableView.getSelectionModel().setCellSelectionEnabled(true);
		tableView.getColumns().setAll(id,name,borrowtime,outDays);
		Callback<TableColumn<Map, String>, TableCell<Map, String>> cellFactoryForMap = (
				TableColumn<Map, String> p) -> new TextFieldTableCell(new StringConverter() {
					@Override
					public String toString(Object t) {
						return t.toString();
					}

					@Override
					public Object fromString(String string) {
						return string;
					}
				});
		id.setCellFactory(cellFactoryForMap);
		name.setCellFactory(cellFactoryForMap);
		borrowtime.setCellFactory(cellFactoryForMap);
		outDays.setCellFactory(cellFactoryForMap);
		final VBox vbox = new VBox();
		vbox.setSpacing(5);
		vbox.setPadding(new Insets(10, 0, 0, 10));
		vbox.getChildren().addAll(tableView);
		scrollPane.setContent(vbox);
		((Group) scene.getRoot()).getChildren().addAll(scrollPane);
		scene.getStylesheets().add(Result.class.getResource("application.css").toExternalForm());
		stage.setScene(scene);
		stage.getIcons().add(new Image("file:images/result.png"));
		stage.show();
	}
}
@SuppressWarnings({ "rawtypes", "unchecked" })
final class ReturnedSituation {
	final static void operate() {
		final Stage stage=new Stage();
		Scene scene = new Scene(new Group());
		ScrollPane scrollPane = new ScrollPane();
		stage.setTitle("搜索结果");
		TableColumn<Map, String> id = new TableColumn<>("书号");
		TableColumn<Map, String> name = new TableColumn<>("书名");
		TableColumn<Map, String> reader = new TableColumn<>("借阅人");
		TableColumn<Map, String> borrowtime = new TableColumn<>("归还时间");
		id.setCellValueFactory(new MapValueFactory("书号"));
		id.setMinWidth(130);
		name.setCellValueFactory(new MapValueFactory("书名"));
		name.setMinWidth(130);
		reader.setCellValueFactory(new MapValueFactory("借阅人"));
		reader.setMinWidth(130);
		borrowtime.setCellValueFactory(new MapValueFactory("归还时间"));
		borrowtime.setMinWidth(130);
		TableView tableView = new TableView<>(Operation.allData);
		tableView.getSelectionModel().setCellSelectionEnabled(true);
		tableView.getColumns().setAll(id,name,reader,borrowtime);
		Callback<TableColumn<Map, String>, TableCell<Map, String>> cellFactoryForMap = (
				TableColumn<Map, String> p) -> new TextFieldTableCell(new StringConverter() {
					@Override
					public String toString(Object t) {
						return t.toString();
					}

					@Override
					public Object fromString(String string) {
						return string;
					}
				});
		id.setCellFactory(cellFactoryForMap);
		name.setCellFactory(cellFactoryForMap);
		reader.setCellFactory(cellFactoryForMap);
		borrowtime.setCellFactory(cellFactoryForMap);
		final VBox vbox = new VBox();
		vbox.setSpacing(5);
		vbox.setPadding(new Insets(10, 0, 0, 10));
		vbox.getChildren().addAll(tableView);
		scrollPane.setContent(vbox);
		((Group) scene.getRoot()).getChildren().addAll(scrollPane);
		scene.getStylesheets().add(Result.class.getResource("application.css").toExternalForm());
		stage.setScene(scene);
		stage.getIcons().add(new Image("file:images/result.png"));
		stage.show();
	}
}
@SuppressWarnings({ "rawtypes", "unchecked" })
final class OwnBorrowSituation {
	final static void operate() {
		final Stage stage=new Stage();
		Scene scene = new Scene(new Group());
		ScrollPane scrollPane = new ScrollPane();
		stage.setTitle("我的借阅");
		TableColumn<Map, String> id = new TableColumn<>("书号");
		TableColumn<Map, String> name = new TableColumn<>("书名");
		TableColumn<Map, String> reader = new TableColumn<>("已借天数");
		TableColumn<Map, String> borrowtime = new TableColumn<>("可借天数");
		id.setCellValueFactory(new MapValueFactory("书号"));
		id.setMinWidth(130);
		name.setCellValueFactory(new MapValueFactory("书名"));
		name.setMinWidth(130);
		reader.setCellValueFactory(new MapValueFactory("已借天数"));
		reader.setMinWidth(130);
		borrowtime.setCellValueFactory(new MapValueFactory("可借天数"));
		borrowtime.setMinWidth(130);
		TableView tableView = new TableView<>(Operation.allData);
		tableView.getSelectionModel().setCellSelectionEnabled(true);
		tableView.getColumns().setAll(id,name,reader,borrowtime);
		Callback<TableColumn<Map, String>, TableCell<Map, String>> cellFactoryForMap = (
				TableColumn<Map, String> p) -> new TextFieldTableCell(new StringConverter() {
					@Override
					public String toString(Object t) {
						return t.toString();
					}

					@Override
					public Object fromString(String string) {
						return string;
					}
				});
		id.setCellFactory(cellFactoryForMap);
		name.setCellFactory(cellFactoryForMap);
		reader.setCellFactory(cellFactoryForMap);
		borrowtime.setCellFactory(cellFactoryForMap);
		final VBox vbox = new VBox();
		vbox.setSpacing(5);
		vbox.setPadding(new Insets(10, 0, 0, 10));
		vbox.getChildren().addAll(tableView);
		scrollPane.setContent(vbox);
		((Group) scene.getRoot()).getChildren().addAll(scrollPane);
		scene.getStylesheets().add(Result.class.getResource("application.css").toExternalForm());
		stage.setScene(scene);
		stage.getIcons().add(new Image("file:images/result.png"));
		stage.show();
	}
}
@SuppressWarnings({ "rawtypes", "unchecked" })
final class OwnReservedSituation {
	final static void operate(String title,String key) {
		final Stage stage = new Stage();
		Scene scene = new Scene(new Group());
		ScrollPane scrollPane = new ScrollPane();
		stage.setTitle(title);
		TableColumn<Map, String> id = new TableColumn<>("书号");
		TableColumn<Map, String> name = new TableColumn<>("书名");
		TableColumn<Map, String> reader = new TableColumn<>(key);
		id.setCellValueFactory(new MapValueFactory("书号"));
		id.setMinWidth(130);
		name.setCellValueFactory(new MapValueFactory("书名"));
		name.setMinWidth(130);
		reader.setCellValueFactory(new MapValueFactory(key));
		reader.setMinWidth(130);
		TableView tableView = new TableView<>(Operation.allData);
		tableView.getSelectionModel().setCellSelectionEnabled(true);
		tableView.getColumns().setAll(id,name,reader);
		Callback<TableColumn<Map, String>, TableCell<Map, String>> cellFactoryForMap = (
				TableColumn<Map, String> p) -> new TextFieldTableCell(new StringConverter() {
					@Override
					public String toString(Object t) {
						return t.toString();
					}

					@Override
					public Object fromString(String string) {
						return string;
					}
				});
		id.setCellFactory(cellFactoryForMap);
		name.setCellFactory(cellFactoryForMap);
		reader.setCellFactory(cellFactoryForMap);
		final VBox vbox = new VBox();
		vbox.setSpacing(5);
		vbox.setPadding(new Insets(10, 0, 0, 10));
		vbox.getChildren().addAll(tableView);
		scrollPane.setContent(vbox);
		((Group) scene.getRoot()).getChildren().addAll(scrollPane);
		scene.getStylesheets().add(Result.class.getResource("application.css").toExternalForm());
		stage.setScene(scene);
		stage.getIcons().add(new Image("file:images/result.png"));
		stage.show();
	}
}