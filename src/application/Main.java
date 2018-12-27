package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
/**
 * 主界面
 * @author donm
 *
 */
public class Main extends Application {
	final String[] weekDays = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
	final static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	final Hyperlink contactLink=new Hyperlink("联系我们",Constant.contact);
	final Hyperlink problemLink=new Hyperlink("常见问题",Constant.problem);
	final Hyperlink ruleLink=new Hyperlink("规章制度",Constant.rule);
	boolean flag = true;
	boolean isBrower=true;
	int amount,num;
	boolean isflag=true;
	static Hyperlink login;
	static Hyperlink userExit;
	static HBox head;
	static Tab tab6;
	int index=0;
	Map<Integer,Element> map=new HashMap<>();
	Map<Integer,NewBook> map2=new HashMap<>();
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public void start(Stage primaryStage) throws Exception {
		variable.init();
		TabPane tabPane = new TabPane();
		/**
		 * 主页
		 */
		final Tab tab =new Tab("主页");
		tab.setGraphic(Constant.first);
		tab.setClosable(false);
		Label label = new Label();
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {

			@Override
			public void run() {
				Platform.runLater(new Runnable() {

					@Override
					public void run() {
						label.setGraphic(Constant.imgs[index++]);
						index%=9;
					}
				});
			}
		}, 0, 3000);
		Calendar cal = Calendar.getInstance();
		final Text text = new Text();
		text.setFill(Color.RED);
		text.setFont(Font.font(null, FontWeight.BOLD, 30));
		Timer timer2 = new Timer();
		timer2.schedule(new TimerTask() {
			@Override
			public void run() {
				Platform.runLater(new Runnable() {
					@Override
					public void run() {
						Date date=new Date();
						cal.setTime(date);
						text.setText(sdf.format(date)+"  "+weekDays[(cal.get(Calendar.DAY_OF_WEEK)-1+7)%7]);
					}
				});
			}
		}, 0, 1000);
		VBox vBox = new VBox();
		contactLink.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				Constant.display();	
			}
		});
		
		ruleLink.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				Constant.toUrl(Constant.RULE);
			}
		});
		
		problemLink.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				Constant.toUrl(Constant.PROBLEM);
			}
		});
		vBox.getChildren().addAll(label,new HBox(text,contactLink,ruleLink,problemLink));
		tab.setContent(vBox);
		/**
		 * 借阅排行榜
		 */
		final Tab tab2 = new Tab("榜单");
		if(isflag) {
			rank();
			newBook();
			isflag=false;
		}
		tab2.setGraphic(Constant.rank);
		tab2.setClosable(false);
		Pagination rankPa = new Pagination();
		double kk=Math.ceil(amount*1.0/2);
		rankPa = new Pagination((int)kk, 0);
		rankPa.setPageFactory((Integer pageIndex) -> createPage(pageIndex));
		tab2.setContent(rankPa);
		/**
		 * 新书上架
		 */
		final Tab tab3 = new Tab("新书");
		tab3.setGraphic(Constant.newBook);
		tab3.setClosable(false);
		Pagination newBookPa = new Pagination();
		double kkk=Math.ceil(num*1.0/2);
		newBookPa = new Pagination((int)kkk, 0);
		newBookPa.setPageFactory((Integer pageIndex) -> createPage2(pageIndex));
		tab3.setContent(newBookPa);
		/**
		 * 检索
		 */
		final Tab tab4 = new Tab("搜索");
		tab4.setGraphic(Constant.search);
		tab4.setClosable(false);
		ChoiceBox searchType = new ChoiceBox(FXCollections.observableArrayList(
			    "书名","书号", "作者","分类","出版社","出版日")
			);
		searchType.setBackground(Background.EMPTY);
		searchType.setValue("书名");
		TextField keyWord=new TextField();
		keyWord.setOnKeyPressed(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {
				if (event.getCode() == KeyCode.ENTER) {
					if (searchType.getValue().equals("书号")) {
						if (Operation.checkPut(keyWord.getText())) {
							Operation.searchBook((String) searchType.getValue(), keyWord.getText());
						}
					} else {
						Operation.searchBook((String) searchType.getValue(), keyWord.getText());
					}
				}
			}
		});
		searchType.setOnAction(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				keyWord.setText(null);
				String key=(String) searchType.getValue();
				String info=null;
				switch (key) {
				case "书名":
					info="请输入书名";
					break;
				case "书号":
					info="请输入书号";
					break;
				case "作者":
					info="请输入作者";
					break;
				case "分类":
					info="请输入计算机/历史/小说/其他";
					break;
				case "出版社":
					info="请输入出版社";
					break;
				default:
					info="请输入出版时间";
					break;
				}
				keyWord.setPromptText(info+",按enter键执行");
			}
		});
		keyWord.setPromptText("请输入书名,按enter键执行");
		keyWord.setPrefWidth(315.0);
		HBox hBox=new HBox();
		hBox.getChildren().addAll(searchType,keyWord);
		ScrollPane scrollPane = new ScrollPane();
		scrollPane.setMaxWidth(313);
		scrollPane.setMinWidth(313);
		TextField lookField=new TextField();
		lookField.setPromptText("请输入书号,按enter键执行");
		TitledPane look=new TitledPane("图书详细信息",lookField);
		look.setGraphic(Constant.look);
		look.setMaxWidth(313);
		look.setMinWidth(313);
		lookField.setOnKeyPressed(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {
				if(event.getCode()==KeyCode.ENTER) {
					if (Operation.checkPut(lookField.getText())) {
						try {
							Connection connection = C3p0.getConnection();
							String sql = "SELECT bookname,author,bookplace,booktime,description,image FROM warehouse WHERE id=?";
							PreparedStatement preparedStatement = connection.prepareStatement(sql);
							preparedStatement.setInt(1, Integer.parseInt(lookField.getText()));
							ResultSet resultSet = preparedStatement.executeQuery();
							if (!resultSet.next()) {
								variable.infoHint("图书不存在");
							} else {
								VBox con = new VBox();
								Label text = new Label("书名:" + resultSet.getString(1));
								Label text2 = new Label("作者:" + resultSet.getString(2));
								Label text3 = new Label("出版社:" + resultSet.getString(3));
								Label text4 = new Label("出版日:" + resultSet.getString(4));
								Label text5 = new Label("简介:" + resultSet.getString(5));
								con.getChildren().addAll(new ImageView(new Image(resultSet.getString(6))), text, text2,
										text3, text4, text5);
								look.setContent(con);
							}
						} catch (SQLException e) {
							e.printStackTrace();
						}
					}
				}
			}
		});
		look.setOnMouseClicked(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				lookField.setText("");
				look.setContent(lookField);
				
			}
		});
		scrollPane.setContent(look);
		final WebView browser = new WebView();
        final WebEngine webEngine = browser.getEngine();
		Hyperlink link=new Hyperlink("",Constant.jd);
		link.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				if(isBrower) {
					webEngine.load("https://www.jd.com/");
					browser.setVisible(true);
				}
				else {
					browser.setVisible(false);
				}
				isBrower=!isBrower;
				
			}
		});
		Hyperlink link2=new Hyperlink("",Constant.tm);
		link2.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				if(isBrower) {
					webEngine.load("https://www.tmall.com/");
					browser.setVisible(true);
				}
				else {
					browser.setVisible(false);
				}
				isBrower=!isBrower;
				
			}
		});
		
		VBox left=new VBox(hBox,Constant.front);
		VBox vBox2=new VBox(new HBox(left,scrollPane),new HBox(link,link2),browser);
		tab4.setContent(vBox2);
		/**
		 * 普通用户
		 */
		Tab tab5 = new Tab("我的");
		tab5.setGraphic(Constant.my);
		tab5.setClosable(false);
		login=new Hyperlink("登录/注册");
		userExit=new Hyperlink("退出");
		userExit.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				if(userExit.isVisible()) {
					login.setGraphic(null);
					login.setText("登录/注册");
					userExit.setVisible(false);
					tab6.setDisable(true);
					variable.init();
				}
				
			}
		});
		userExit.setVisible(false);
		head=new HBox();
		head.getChildren().addAll(login,userExit);
		head.setAlignment(Pos.TOP_CENTER);
		login.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				if(!variable.isLogin) {
					Login.Operate();
				}
			}
		});
		final Hyperlink borrowing=new Hyperlink("借阅中", Constant.ing);
		borrowing.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				pleaseLogin();
				if(variable.isLogin) {
					try {
						Operation.operate("", 5);
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			}
		});
		Hyperlink reserved=new Hyperlink("预订中", Constant.reservation);
		reserved.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				pleaseLogin();
				if(variable.isLogin) {
					try {
						Operation.operate("", 6);
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			}
		});
		Hyperlink returned=new Hyperlink("已归还", Constant.ok);
		returned.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				pleaseLogin();
				if(variable.isLogin) {
					try {
						Operation.operate("", 8);
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			}
		});
		Hyperlink timeout=new Hyperlink("已逾期", Constant.lack);
		timeout.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				pleaseLogin();
				if(variable.isLogin) {
					try {
						Operation.operate("", 10);
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			}
		});
		Hyperlink collected=new Hyperlink("收藏中", Constant.like);
		collected.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				pleaseLogin();
				if(variable.isLogin) {
					try {
						Operation.operate("", 7);
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			}
		});
		Hyperlink history=new Hyperlink("历史记录", Constant.statistics);
		history.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				pleaseLogin();
				if(variable.isLogin) {
					try {
						Operation.operate("", 9);
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			}
		});
		ScrollPane changeSP=new ScrollPane();
		TitledPane changePane=new TitledPane();
		changeSP.setMaxWidth(200);
		changeSP.setMinWidth(200);
		changeSP.setContent(changePane);
		changePane.setGraphic(Constant.mychange);
		TextField bookID=new TextField();
		bookID.setPromptText("请输入书号");
		Hyperlink borrowLink=new Hyperlink("",Constant.borrow);
		borrowLink.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				pleaseLogin();
				if(variable.isLogin&&Operation.checkPut(bookID.getText())) {
					Operation.borrowCheck(bookID.getText());
					
				}
			}
		});
		Hyperlink returnLink=new Hyperlink("",Constant.returnbook);
		returnLink.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				pleaseLogin();
				if(variable.isLogin&&Operation.checkPut(bookID.getText())) {
					Operation.returnCheck(bookID.getText());
				}
			}
		});
		final Hyperlink continueLink=new Hyperlink("",Constant.continuebook);
		continueLink.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				pleaseLogin();
				if(variable.isLogin&&Operation.checkPut(bookID.getText())) {
					variable.checkContinue(bookID.getText());
				}
			}
		});
		final Hyperlink reserveLink=new Hyperlink("",Constant.reserve);
		reserveLink.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				pleaseLogin();
				if(variable.isLogin&&Operation.checkPut(bookID.getText())) {
					variable.doSomething(bookID.getText(),1,1);
				}
			}
		});
		final Hyperlink cancelReserveLink=new Hyperlink("",Constant.cancelreserve);
		cancelReserveLink.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				pleaseLogin();
				if(variable.isLogin&&Operation.checkPut(bookID.getText())) {
					variable.doSomething(bookID.getText(),0,1);
				}
			}
		});
		final Hyperlink collectLink=new Hyperlink("",Constant.collect);
		collectLink.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				pleaseLogin();
				if(variable.isLogin&&Operation.checkPut(bookID.getText())) {
					variable.doSomething(bookID.getText(),1,0);
				}
				
			}
		});
		final Hyperlink cancelCollectLink=new Hyperlink("",Constant.cancelcollect);
		cancelCollectLink.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				pleaseLogin();
				if(variable.isLogin&&Operation.checkPut(bookID.getText())) {
					variable.doSomething(bookID.getText(),0,0);
				}
			}
		});
		changePane.setContent(new VBox(bookID,borrowLink,returnLink,continueLink,
				reserveLink,cancelReserveLink,collectLink,cancelCollectLink));
		HBox part=new HBox(reserved,returned,timeout);
		part.setAlignment(Pos.CENTER);
		HBox part1=new HBox(borrowing,collected,history);
		part1.setAlignment(Pos.CENTER);
		VBox part2=new VBox(part,part1);
		part2.setAlignment(Pos.CENTER);
		HBox cHBox=new HBox(changeSP);
		cHBox.setAlignment(Pos.CENTER);
		tab5.setContent(new VBox(head,part2,cHBox));
		/**
		 * 管理员权限
		 */
		tab6= new Tab("管理");
		tab6.setGraphic(Constant.manager);
		tab6.setClosable(false);
		tab6.setDisable(true);
		TitledPane increasePane=new TitledPane();
		increasePane.setMaxWidth(450);
		increasePane.setMinWidth(450);
		increasePane.setGraphic(Constant.dot);
		VBox con1=new VBox();
		TextField number=new TextField();
		number.setPromptText("书号");
		HBox h=new HBox(Constant.no,number);
		TextField author=new TextField();
		author.setPromptText("作者");
		HBox h1=new HBox(Constant.author,author);
		TextField bookname=new TextField();
		bookname.setPromptText("书名");
		HBox h2=new HBox(Constant.name,bookname);
		TextField bookplace=new TextField();
		bookplace.setPromptText("出版社");
		HBox h3=new HBox(Constant.company,bookplace);
		TextField booktime=new TextField();
		booktime.setPromptText("出版日");
		HBox h4=new HBox(Constant.time,booktime);
		ChoiceBox booktype = new ChoiceBox(FXCollections.observableArrayList(
			    "计算机","小说", "杂志","历史","其他")
			);
		booktype.setValue("计算机");
		booktype.setBackground(Background.EMPTY);
		HBox h5=new HBox(Constant.distinct,booktype);
		TextArea description=new TextArea();
		description.setPromptText("图书简介");
		description.setPrefWidth(160);
		HBox h6=new HBox(Constant.description,description);
		con1.getChildren().addAll(h,h1,h2,h3,h4,h5,h6);
		Hyperlink picture=new Hyperlink("修改图书",new ImageView(new Image(variable.bookPicture)));
		picture.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				Alert position = new Alert(Alert.AlertType.CONFIRMATION);
				position.setHeaderText("");
				position.setGraphic(null);
				Pane pane = new Pane();
				TextField imageUrl=new TextField();
				imageUrl.setText("file:bookimages/0.jpg");
				imageUrl.setPrefWidth(300.0);
				pane.getChildren().add(imageUrl);
				position.getDialogPane().getChildren().add(pane);
				position.showAndWait().ifPresent(response -> {
				     if (response == ButtonType.OK) {
				         variable.bookPicture=imageUrl.getText();
				         picture.setGraphic(new ImageView(new Image(variable.bookPicture)));
				         position.close();
				     }
				 });
				
			}
		});
		final Hyperlink putIn=new Hyperlink("",Constant.putbook);
		putIn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				pleaseLogin();
				if(variable.isLogin) {
					try {
						Connection connection = C3p0.getConnection();
						PreparedStatement preparedStatement = 
								connection.prepareStatement("SELECT doput(?,?,?,?,?,?,?,?,?)");
						preparedStatement.setInt(1, Integer.parseInt(number.getText()));
						preparedStatement.setString(2, bookname.getText());
						preparedStatement.setString(3, author.getText());
						preparedStatement.setString(4, booktime.getText());
						preparedStatement.setString(5, bookplace.getText());
						preparedStatement.setString(6, booktype.getValue().toString());
						preparedStatement.setString(7, description.getText());
						preparedStatement.setString(8, variable.bookPicture);
						preparedStatement.setString(9, nowTime());
						ResultSet resultSet=preparedStatement.executeQuery();
						while(resultSet.next()) {
							variable.infoHint(resultSet.getString(1));
						}
						connection.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
				
			}
		});
		final Hyperlink changeIn=new Hyperlink("",Constant.changing);
		Hyperlink deleteIn=new Hyperlink("",Constant.delete);
		VBox bVBox=new VBox(picture,putIn,changeIn,deleteIn);
		increasePane.setContent(new HBox(con1,bVBox));
		changeIn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				pleaseLogin();
				if(variable.isLogin) {
					try {
						Connection connection = C3p0.getConnection();
						PreparedStatement preparedStatement = 
								connection.prepareStatement("SELECT dochange(?,?,?,?,?,?,?,?,?)");
						preparedStatement.setString(1, bookname.getText());
						preparedStatement.setString(2, author.getText());
						preparedStatement.setString(3, booktime.getText());
						preparedStatement.setString(4, bookplace.getText());
						preparedStatement.setString(5, booktype.getValue().toString());
						preparedStatement.setString(6, description.getText());
						preparedStatement.setString(7, variable.bookPicture);
						preparedStatement.setString(8, nowTime());
						preparedStatement.setInt(9, Integer.parseInt(number.getText()));
						ResultSet resultSet=preparedStatement.executeQuery();
						while(resultSet.next()) {
							variable.infoHint(resultSet.getString(1));
						}
						connection.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			}
		});
		deleteIn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				pleaseLogin();
				if(variable.isLogin) {
					try {
						Connection connection=C3p0.getConnection();
						PreparedStatement preparedStatement=connection.prepareStatement("SELECT dodelete(?)");
						preparedStatement.setInt(1, Integer.parseInt(number.getText()));
						ResultSet resultSet=preparedStatement.executeQuery();
						while (resultSet.next()) {
							variable.infoHint(resultSet.getString(1));
							connection.close();
						}
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			}
		});
		final Hyperlink scanbooks=new Hyperlink("查看所有图书",Constant.scan);
		scanbooks.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				pleaseLogin();
				if(variable.isLogin) {
					try {
						Operation.operate("", 1);
					} catch (SQLException e) {
						e.printStackTrace();
					}
					
				}
			}
		});
		final Hyperlink scanBorrowing=new Hyperlink("查看借阅图书",Constant.read);
		scanBorrowing.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				pleaseLogin();
				if(variable.isLogin) {
					try {
						Operation.operate("", 2);
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
				
			}
		});
		final Hyperlink scanTimeOut=new Hyperlink("查看逾期图书",Constant.moretime);
		scanTimeOut.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				pleaseLogin();
				if(variable.isLogin) {
					try {
						Operation.operate("", 3);
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			}
		});
		final Hyperlink scanReturned=new Hyperlink("已归还图书",Constant.returned);
		scanReturned.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				pleaseLogin();
				if(variable.isLogin) {
					try {
						Operation.operate("", 4);
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			}
		});
		final Hyperlink scanType=new Hyperlink("图书分类占比",Constant.looktype);
		scanType.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				pleaseLogin();
				if(variable.isLogin) {
					try {
						Situation.operate();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			}
		});
		final TitledPane scanManagers=new TitledPane();
		scanManagers.setGraphic(Constant.allmanager);
		scanManagers.setText("查看管理员");
		scanManagers.setMaxWidth(150);
		scanManagers.setMinWidth(150);
		scanManagers.setOnMouseClicked(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				String con="";
				try {
					Connection connection = C3p0.getConnection();
					PreparedStatement preparedStatement=
							connection.prepareStatement("SELECT name FROM reader WHERE isManager=1");
					ResultSet resultSet=preparedStatement.executeQuery();
					while(resultSet.next()) {
						con+=resultSet.getString(1)+"\n";
					}
					scanManagers.setContent(new TextArea(con));
					
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		});
		final TextField managerField=new TextField();
		managerField.setPromptText("请输入用户名,按enter键执行");
		TitledPane managerPane=new TitledPane("添加管理员",managerField);
		managerPane.setGraphic(Constant.add);
		managerPane.setMinWidth(150);
		managerPane.setMaxWidth(150);
		managerPane.setOnMouseClicked(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				managerField.setText("");
			}
		});
		managerField.setOnKeyPressed(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {
				if(event.getCode()==KeyCode.ENTER) {
					try {
						Connection connection=C3p0.getConnection();
						PreparedStatement preparedStatement=connection.prepareStatement("SELECT checkmanager(?)");
						preparedStatement.setString(1,managerField.getText());
						ResultSet resultSet=preparedStatement.executeQuery();
						while(resultSet.next()) {
							variable.infoHint(resultSet.getString(1));
						}
						connection.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			}
		});
		managerPane.setContent(managerField);
		final VBox right=new VBox(scanbooks,scanBorrowing,scanTimeOut,scanReturned,scanType,managerPane,scanManagers);
		tab6.setContent(new HBox(increasePane,right));
		tabPane.getTabs().addAll(tab, tab2,tab3,tab4,tab5,tab6);
		/**
		 * 结束
		 */
		Scene scene = new Scene(tabPane);
		scene.getStylesheets().add(Login.class.getResource("application.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.setHeight(550);
		primaryStage.setWidth(710);
		primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {

			@Override
			public void handle(WindowEvent event) {
				timer2.cancel();
				timer.cancel();
			}
		});
		primaryStage.getIcons().add(Constant.logo);
		primaryStage.setTitle("在线图书馆");
		primaryStage.show();
	}
	// 函数
	final void pleaseLogin() {
		if(!variable.isLogin) {
			variable.infoHint("请先登录");
		}
	}
	static final String nowTime() {
		return sdf.format(new Date());		
	}
    public VBox createPage(int pageIndex) {        
        VBox box = new VBox();
        int page = pageIndex * 2;
        for (int i = page; i < (page+2)&&i<amount; i++) {
        	Element element=map.get(i);
        	HBox hBox=new HBox();
        	String key="书名: "+element.name+"\n"+"书号: "+element.number+"\n"+"借阅次数: "+element.cnt;
        	hBox.getChildren().addAll(new ImageView(new Image(element.photo)),new Label(key));
        	box.getChildren().add(hBox);
        }
        return box;
    }
    public VBox createPage2(int pageIndex) {        
        VBox box = new VBox();
        int page = pageIndex * 2;
        for (int i = page; i < (page+2)&&i<num; i++) {
        	NewBook element=map2.get(i);
        	HBox hBox=new HBox();
        	String key="书名: "+element.name+"\n"+"书号: "+element.number+"\n"+"上架时间: "+element.addtime;
        	hBox.getChildren().addAll(new ImageView(new Image(element.photo)),new Label(key));
        	box.getChildren().add(hBox);
        }
        return box;
    }
    final void rank() {
    	try {
    		int d=0;
			Connection connection=C3p0.getConnection();
			PreparedStatement preparedStatement=connection.prepareStatement("CALL printrank");
			ResultSet resultSet=preparedStatement.executeQuery();
			while (resultSet.next()) {
				map.put(d++,new Element(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4)));
			}
			amount=d;
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
    }
    final void newBook() {
    	try {
    		int d=0;
			Connection connection=C3p0.getConnection();
			PreparedStatement preparedStatement=connection.prepareStatement("CALL printnewbook");
			ResultSet resultSet=preparedStatement.executeQuery();
			while (resultSet.next()) {
				map2.put(d++,new NewBook(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4)));
			}
			num=d;
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
    }
	public static void main(String[] args) {
		launch(args);
	}

}
