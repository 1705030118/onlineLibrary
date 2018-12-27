package application;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

final class C3p0{
    private static ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource("mySource");
    static final Connection getConnection() throws SQLException{
        try {
            return comboPooledDataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }
}
final class variable{
	static boolean isLogin;
	static String myPicture;
	static String bookPicture;
	static String username;
	static boolean isManager;
	static final void init() {
		isLogin=false;
		bookPicture="file:bookimages/0.jpg";
		myPicture="file:image/0.png";
		username="";
		isManager=false;
	}
	static final void infoHint(String info) {
		Alert alert=new Alert(AlertType.INFORMATION);
		if(!info.contains("成功")) alert.setAlertType(AlertType.ERROR);
		alert.setHeaderText(info);
		alert.show();
	}
	static final void checkContinue(String id) {
		try {
			Connection connection=C3p0.getConnection();
			PreparedStatement preparedStatement=connection.prepareStatement("SELECT checkcontinue(?,?)");
			preparedStatement.setInt(1, Integer.parseInt(id));
			preparedStatement.setString(2,username);
			ResultSet resultSet=preparedStatement.executeQuery();
			while(resultSet.next()) {
				variable.infoHint(resultSet.getString(1));
			}
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	static final void doSomething(String id,int flag,int type) {
		try {
			Connection connection=C3p0.getConnection();
			PreparedStatement preparedStatement=null;
			if(type==1) {
				preparedStatement=connection.prepareStatement("SELECT checkreserve(?,?,?,?)");
			}
			else {
				preparedStatement=connection.prepareStatement("SELECT checkcollect(?,?,?,?)");
			}
			preparedStatement.setInt(1, Integer.parseInt(id));
			preparedStatement.setString(2,username);
			preparedStatement.setInt(3, flag);
			preparedStatement.setString(4, Main.nowTime());
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
final class Element{
	String photo;
	String name;
	int number;
	String cnt;
	public Element(int number,String photo,String name,String cnt) {
		this.photo=photo;
		this.name=name;
		this.number=number;
		this.cnt=cnt;
	}
}
final class NewBook{
	String photo;
	String name;
	int number;
	String addtime;
	public NewBook(int number,String name,String photo,String addtime) {
		this.photo=photo;
		this.name=name;
		this.number=number;
		this.addtime=addtime;
	}
}