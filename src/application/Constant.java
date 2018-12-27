package application;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

final class Constant{
	final static ImageView first=new ImageView(new Image("file:images/first.png"));
	final static ImageView contact=new ImageView(new Image("file:images/contact.png"));
	final static ImageView tel=new ImageView(new Image("file:images/tel.png"));
	final static ImageView email=new ImageView(new Image("file:images/emaill.png"));
	final static ImageView rule=new ImageView(new Image("file:images/rule.png"));
	final static ImageView problem=new ImageView(new Image("file:images/problem.png"));
	final static ImageView rank=new ImageView(new Image("file:images/rank.png"));
	final static ImageView newBook=new ImageView(new Image("file:images/new.png"));
	final static ImageView search=new ImageView(new Image("file:images/search.png"));
	final static ImageView searchDynamic=new ImageView(new Image("file:images/searchDynamic.gif"));
	final static ImageView jd=new ImageView(new Image("file:images/jd.png"));
	final static ImageView tm=new ImageView(new Image("file:images/tm.png"));
	final static ImageView my=new ImageView(new Image("file:images/my.png"));
	final static ImageView ing=new ImageView(new Image("file:images/ing.png"));
	final static ImageView reservation=new ImageView(new Image("file:images/reservation.png"));
	final static ImageView ok=new ImageView(new Image("file:images/ok.png"));
	final static ImageView like=new ImageView(new Image("file:images/like.png"));
	final static ImageView lack=new ImageView(new Image("file:images/lack.png"));
	final static ImageView statistics=new ImageView(new Image("file:images/statistics.png"));
	final static ImageView mychange=new ImageView(new Image("file:images/mychange.png"));
	final static ImageView borrow=new ImageView(new Image("file:images/borrow.png"));
	final static ImageView returnbook=new ImageView(new Image("file:images/return.png"));
	final static ImageView continuebook=new ImageView(new Image("file:images/continue.png"));
	final static ImageView reserve=new ImageView(new Image("file:images/reserve.png"));
	final static ImageView cancelreserve=new ImageView(new Image("file:images/cancelreserve.png"));
	final static ImageView collect=new ImageView(new Image("file:images/collect.png"));
	final static ImageView cancelcollect=new ImageView(new Image("file:images/cancelcollect.png"));
	final static ImageView manager=new ImageView(new Image("file:images/manager.png"));
	final static ImageView dot=new ImageView(new Image("file:images/dot.png"));
	final static ImageView no=new ImageView(new Image("file:images/no.png"));
	final static ImageView look=new ImageView(new Image("file:images/look.png"));
	final static ImageView author=new ImageView(new Image("file:images/author.png"));
	final static ImageView name=new ImageView(new Image("file:images/name.png"));
	final static ImageView company=new ImageView(new Image("file:images/company.png"));
	final static ImageView time=new ImageView(new Image("file:images/time.png"));
	final static ImageView distinct=new ImageView(new Image("file:images/distinct.png"));
	final static ImageView description=new ImageView(new Image("file:images/description.png"));
	final static ImageView putbook=new ImageView(new Image("file:images/putbook.png"));
	final static ImageView changing=new ImageView(new Image("file:images/changing.png"));
	final static ImageView delete=new ImageView(new Image("file:images/delete.png"));
	final static ImageView scan=new ImageView(new Image("file:images/scan.png"));
	final static ImageView read=new ImageView(new Image("file:images/read.png"));
	final static ImageView moretime=new ImageView(new Image("file:images/moretime.png"));
	final static ImageView returned=new ImageView(new Image("file:images/returned.png"));
	final static ImageView looktype=new ImageView(new Image("file:images/looktype.png"));
	final static ImageView allmanager=new ImageView(new Image("file:images/allmanager.png"));
	final static ImageView add=new ImageView(new Image("file:images/add.png"));
	final static ImageView registerImage=new ImageView(new Image("file:image/0.png"));
	final static ImageView register=new ImageView(new Image("file:images/register.png"));
	final static ImageView head=new ImageView(new Image("file:images/head.jpg"));
	final static ImageView user=new ImageView(new Image("file:images/user.png"));
	final static ImageView password=new ImageView(new Image("file:images/password.png"));
	final static ImageView login=new ImageView(new Image("file:images/login.png"));
	final static Image logo=new Image("file:images/main.png");
	final static Image result=new Image("file:images/result.png");
	final static ImageView[] imgs = { 
			new ImageView(new Image("file:library/1.png")),
			new ImageView(new Image("file:library/2.png")),
			new ImageView(new Image("file:library/3.png")),
			new ImageView(new Image("file:library/4.png")),
			new ImageView(new Image("file:library/5.png")),
			new ImageView(new Image("file:library/6.png")),
			new ImageView(new Image("file:library/7.png")),
			new ImageView(new Image("file:library/8.jpg")),
			new ImageView(new Image("file:library/9.jpg"))			
			};
	final static Label TEL=new Label("17774621488",tel);
	final static Label EMAIL=new Label("2639368532@qq.com",email);
	final static Label labelHead = new Label("", head);
	final static Label labelUser = new Label("", user);
	final static Label labelPassword = new Label("",password);
	final static Label front = new Label("",searchDynamic);
	final static String PROBLEM="http://lib.hnust.cn/tsgfw/dzzn/cjwt/77586.htm";
	final static String RULE="http://lib.hnust.cn/tsgfw/dzzn/gzzd/51887.htm";
	final static void display() {
		final Alert info = new Alert(AlertType.INFORMATION);
		info.setGraphic(null);
		info.setHeaderText("");
		final Pane pane = new Pane();
		pane.getChildren().add(new VBox(TEL,EMAIL));
		info.getDialogPane().getChildren().add(pane);
		info.show();
	}
	final static void toUrl(String url) {
		Desktop desktop=Desktop.getDesktop();
		try {
			desktop.browse(new URI(url));
		} catch (IOException | URISyntaxException e) {
			e.printStackTrace();
		}
	}
}
