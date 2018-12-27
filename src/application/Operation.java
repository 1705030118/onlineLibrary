package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

final class Operation {
	@SuppressWarnings("rawtypes")
	static ObservableList<Map> allData;
	final static void operate(String keyWord, int key) throws SQLException {
		allData = FXCollections.observableArrayList();
		Connection connection = C3p0.getConnection();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet;
		String sql = null;
		if(key==1) {// 查看所有图书
			sql = "SELECT id,bookname,author,booktime,bookplace,type,isdeleted from warehouse order by id";
			preparedStatement = connection.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Map<String, String> dataRow = new HashMap<>();
				int value1 = resultSet.getInt(1);
				String value2 = resultSet.getString(2);
				String value3 = resultSet.getString(3);
				String value4 = resultSet.getString(4);
				String value5 = resultSet.getString(5);
				String value6 = resultSet.getString(6);
				int value7 = resultSet.getInt(7);
				dataRow.put("书号", String.valueOf(value1));
				dataRow.put("书名", value2);
				dataRow.put("作者", value3);
				dataRow.put("出版社", value4);
				dataRow.put("出版日", value5);
				dataRow.put("类型", value6);
				dataRow.put("是否被借", value7==1?"是":"否");
				allData.add(dataRow);
			}
			connection.close();
			Result.operate();
		}
		else if(key==2){ // 查看借阅图书
			sql="SELECT borrowing.id,(SELECT bookname from warehouse WHERE id=borrowing.id) as bookname,username,borrowtime FROM borrowing order by borrowtime,id";
			preparedStatement=connection.prepareStatement(sql);
			resultSet=preparedStatement.executeQuery();
			while(resultSet.next()) {
				Map<String, String> dataRow = new HashMap<>();
				int value1 = resultSet.getInt(1);
				String value2 = resultSet.getString(2);
				String value3 = resultSet.getString(3);
				String value4 = resultSet.getString(4);
				dataRow.put("书号", String.valueOf(value1));
				dataRow.put("书名", value2);
				dataRow.put("借阅人", value3);
				dataRow.put("借阅时间", value4);
				allData.add(dataRow);
			}
			connection.close();
			BorrowSituation.operate();
		}
		else if(key==3){ //查看逾期图书
			sql="SELECT borrowing.id,(SELECT bookname from warehouse WHERE id=borrowing.id) as bookname,"
					+ "username,borrowtime,((TO_DAYS(NOW())-TO_DAYS(STR_TO_DATE(borrowtime,\"%Y-%m-%d\")))-cnt) "
					+ "AS t FROM borrowing WHERE (TO_DAYS(NOW())-TO_DAYS(STR_TO_DATE(borrowtime,\"%Y-%m-%d\")))>cnt ORDER BY t DESC,id";
			preparedStatement=connection.prepareStatement(sql);
			resultSet=preparedStatement.executeQuery();
			while(resultSet.next()) {
				Map<String, String> dataRow = new HashMap<>();
				int value1 = resultSet.getInt(1);
				String value2 = resultSet.getString(2);
				String value3 = resultSet.getString(3);
				String value4 = resultSet.getString(4);
				System.out.println(resultSet.getInt(5));
				String value5 = String.valueOf(resultSet.getInt(5));
				dataRow.put("书号", String.valueOf(value1));
				dataRow.put("书名", value2);
				dataRow.put("借阅人", value3);
				dataRow.put("借阅时间", value4);
				dataRow.put("超出余额", value5);
				allData.add(dataRow);
			}
			connection.close();
			timeOutBooks.operate("所有逾期记录");
		}
		else if(key==4) { //已归还图书
			sql="SELECT id,(SELECT bookname from warehouse WHERE id=returned.id)as bookname,username,returntime from returned ORDER BY returntime,id";
			preparedStatement=connection.prepareStatement(sql);
			resultSet=preparedStatement.executeQuery();
			while(resultSet.next()) {
				Map<String, String> dataRow = new HashMap<>();
				int value1 = resultSet.getInt(1);
				String value2 = resultSet.getString(2);
				String value3 = resultSet.getString(3);
				String value4 = resultSet.getString(4);
				dataRow.put("书号", String.valueOf(value1));
				dataRow.put("书名", value2);
				dataRow.put("借阅人", value3);
				dataRow.put("归还时间", value4);
				allData.add(dataRow);
			}
			connection.close();
			ReturnedSituation.operate();
		}
		else if(key==5) { // 借阅中
			sql="SELECT id,(SELECT bookname from warehouse WHERE id=borrowing.id) AS bookname,"
					+ "TO_DAYS(NOW())-TO_DAYS(STR_TO_DATE(borrowtime,\"%Y-%m-%d\")),cnt FROM borrowing WHERE username=? order by borrowtime,id";
			preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setString(1, variable.username);
			resultSet=preparedStatement.executeQuery();
			while(resultSet.next()) {
				Map<String, String> dataRow = new HashMap<>();
				int value1 = resultSet.getInt(1);
				String value2 = resultSet.getString(2);
				String value3 = resultSet.getString(3);
				String value4 = resultSet.getString(4);
				dataRow.put("书号", String.valueOf(value1));
				dataRow.put("书名", value2);
				dataRow.put("已借天数", value3);
				dataRow.put("可借天数", value4);
				allData.add(dataRow);
			}
			connection.close();
			OwnBorrowSituation.operate();
		}
		else if(key<10) {
			preparedStatement=connection.prepareStatement("CALL something(?,?)");
			preparedStatement.setString(1, variable.username);
			preparedStatement.setInt(2, key);
			resultSet=preparedStatement.executeQuery();
			while(resultSet.next()) {
				Map<String, String> dataRow = new HashMap<>();
				int value1 = resultSet.getInt(1);
				String value2 = resultSet.getString(2);
				String value3 = resultSet.getString(3);
				dataRow.put("书号", String.valueOf(value1));
				dataRow.put("书名", value2);
				switch (key) {
				case 6:
					dataRow.put("预约时间", value3);
					break;
				case 7:
					dataRow.put("收藏时间", value3);
					break;
				case 8:
					dataRow.put("归还时间", value3);
					break;
				default:
					dataRow.put("借书时间", value3);
					break;
				}
				allData.add(dataRow);
			}
			connection.close();
			switch (key) {
			case 6:
				OwnReservedSituation.operate("我的预约","预约时间");
				break;
			case 7:
				OwnReservedSituation.operate("我的收藏","收藏时间");
				break;
			case 8:
				OwnReservedSituation.operate("我的归还记录","归还时间");
				break;
			default:
				OwnReservedSituation.operate("我的借阅历史记录","借书时间");
				break;
			}
			
		}
		else { // 已逾期
			sql="SELECT id,(SELECT bookname from warehouse WHERE id=borrowing.id) as bookname,borrowtime," + 
					"((TO_DAYS(NOW())-TO_DAYS(STR_TO_DATE(borrowtime,\"%Y-%m-%d\")))-cnt) " + 
					"FROM borrowing WHERE (TO_DAYS(NOW())-TO_DAYS(STR_TO_DATE(borrowtime,\"%Y-%m-%d\")))>cnt AND username=?";
			preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setString(1,variable.username);
			resultSet=preparedStatement.executeQuery();
			while(resultSet.next()) {
				Map<String, String> dataRow = new HashMap<>();
				int value1 = resultSet.getInt(1);
				String value2 = resultSet.getString(2);
				String value3 = resultSet.getString(3);
				String value4 = resultSet.getString(4);
				dataRow.put("书号", String.valueOf(value1));
				dataRow.put("书名", value2);
				dataRow.put("借阅时间", value3);
				dataRow.put("超出余额", value4);
				allData.add(dataRow);
			}
			connection.close();
			OwnTimeOutBooks.operate("我的逾期记录");
		}
	}

	public static void searchBook(String type, String keyword) {
		try {
			allData = FXCollections.observableArrayList();
			Connection connection=C3p0.getConnection();
			PreparedStatement preparedStatement=connection.prepareStatement("CALL searchbook(?,?)");
			switch (type) {
			case "书名":
				preparedStatement.setString(1, "%"+keyword+"%");
				preparedStatement.setInt(2, 0);
				break;
			case "书号":
				preparedStatement.setString(1, keyword);
				preparedStatement.setInt(2, 1);
				break;
			case "作者":
				preparedStatement.setString(1, keyword);
				preparedStatement.setInt(2, 2);
				break;
			case "分类":
				preparedStatement.setString(1, keyword);
				preparedStatement.setInt(2, 3);
				break;
			case "出版社":
				preparedStatement.setString(1, keyword);
				preparedStatement.setInt(2, 4);
				break;
			default:
				preparedStatement.setString(1, keyword+"%");
				preparedStatement.setInt(2, 5);
				break;
			}
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Map<String, String> dataRow = new HashMap<>();
				int value1 = resultSet.getInt(1);
				String value2 = resultSet.getString(2);
				String value3 = resultSet.getString(3);
				String value4 = resultSet.getString(4);
				String value5 = resultSet.getString(5);
				String value6 = resultSet.getString(6);
				int value7 = resultSet.getInt(7);
				dataRow.put("书号", String.valueOf(value1));
				dataRow.put("书名", value2);
				dataRow.put("作者", value3);
				dataRow.put("出版社", value4);
				dataRow.put("出版日", value5);
				dataRow.put("类型", value6);
				dataRow.put("是否被借", value7==1?"是":"否");
				allData.add(dataRow);
			}
			connection.close();
			Result.operate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public static void borrowCheck(String id) {
		try {
			Connection connection=C3p0.getConnection();
			PreparedStatement preparedStatement=connection.prepareStatement("SELECT checkborrow(?,?,?)");
			preparedStatement.setInt(1, Integer.parseInt(id));
			preparedStatement.setString(2, variable.username);
			preparedStatement.setString(3,Main.nowTime());
			ResultSet resultSet=preparedStatement.executeQuery();
			while(resultSet.next()) {
				variable.infoHint(resultSet.getString(1));
			}
			connection.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public static void returnCheck(String id) {
		try {
			Connection connection=C3p0.getConnection();
			PreparedStatement preparedStatement=connection.prepareStatement("SELECT checkreturn(?,?,?)");
			preparedStatement.setInt(1, Integer.parseInt(id));
			preparedStatement.setString(2, variable.username);
			preparedStatement.setString(3,Main.nowTime());
			ResultSet resultSet=preparedStatement.executeQuery();
			while(resultSet.next()) {
				variable.infoHint(resultSet.getString(1));
			}
			connection.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static boolean checkPut(String key) {
		Pattern pattern = Pattern.compile("[0-9]{1,}");
		Matcher matcher = pattern.matcher(key);
		if (!matcher.matches()) {
			variable.infoHint("包含非数字,请正确输入");
		}
		return matcher.matches();
	}
}

