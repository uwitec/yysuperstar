package com.kfcreservation.modules;

import java.util.Vector;

import com.kfcreservation.core.Core;
import com.kfcreservation.core.MySQLite;

public class CRUD {

	// 站站查询
	public static Vector<Vector<String>> getSameVector(String start, String end) {
		// 查找车名,始发站,终点站和车类型
		String sql = "select Tname,Tstartstation,Tterminus,Ttype "
				+ "from train where Tid in "
				+ "(select Tid from relation where Sid in "
				+ "(select Sid from station where Sname='" + start + "') "
				+ "and Tid in " + "(select Tid from relation where Sid in "
				+ "(select Sid from station where Sname='" + end + "')))";
		// 得到有关火车信息的Vector
		Vector<Vector<String>> temp = MySQLite.query(sql);
		// 查找出发站和火车开车的时间
		String sql1 = "select Sname,Rstarttime from station,relation"
				+ " where Sname='" + start + "' and "
				+ "station.Sid=relation.Sid and " + "relation.Tid in "
				+ "(select Tid from relation where Sid in"
				+ "(select Sid from station where Sname='" + start + "') "
				+ "and Tid in" + "(select Tid from relation where Sid in "
				+ "(select Sid from station where Sname='" + end + "')))";
		// 查找终点站和火车到站时间
		String sql2 = "select Sname,Rarrivetime from station,relation"
				+ " where Sname='" + end + "' and "
				+ "station.Sid=relation.Sid and " + "relation.Tid in "
				+ "(select Tid from relation where Sid in"
				+ "(select Sid from station where Sname='" + start + "') "
				+ "and Tid in" + "(select Tid from relation where Sid in "
				+ "(select Sid from station where Sname='" + end + "')))";
		// 得到有关火车站的信息
		Vector<Vector<String>> temp1 = MySQLite.query(sql1);
		Vector<Vector<String>> temp2 = MySQLite.query(sql2);
		// 将查询结果组合到一起
		temp = Core.combine(temp, temp1, temp2);
		return temp;
	}
}
