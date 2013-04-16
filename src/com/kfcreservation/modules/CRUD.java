package com.kfcreservation.modules;

import java.util.Vector;

import com.kfcreservation.core.Core;
import com.kfcreservation.core.MySQLite;

public class CRUD {

	// վվ��ѯ
	public static Vector<Vector<String>> getSameVector(String start, String end) {
		// ���ҳ���,ʼ��վ,�յ�վ�ͳ�����
		String sql = "select Tname,Tstartstation,Tterminus,Ttype "
				+ "from train where Tid in "
				+ "(select Tid from relation where Sid in "
				+ "(select Sid from station where Sname='" + start + "') "
				+ "and Tid in " + "(select Tid from relation where Sid in "
				+ "(select Sid from station where Sname='" + end + "')))";
		// �õ��йػ���Ϣ��Vector
		Vector<Vector<String>> temp = MySQLite.query(sql);
		// ���ҳ���վ�ͻ𳵿�����ʱ��
		String sql1 = "select Sname,Rstarttime from station,relation"
				+ " where Sname='" + start + "' and "
				+ "station.Sid=relation.Sid and " + "relation.Tid in "
				+ "(select Tid from relation where Sid in"
				+ "(select Sid from station where Sname='" + start + "') "
				+ "and Tid in" + "(select Tid from relation where Sid in "
				+ "(select Sid from station where Sname='" + end + "')))";
		// �����յ�վ�ͻ𳵵�վʱ��
		String sql2 = "select Sname,Rarrivetime from station,relation"
				+ " where Sname='" + end + "' and "
				+ "station.Sid=relation.Sid and " + "relation.Tid in "
				+ "(select Tid from relation where Sid in"
				+ "(select Sid from station where Sname='" + start + "') "
				+ "and Tid in" + "(select Tid from relation where Sid in "
				+ "(select Sid from station where Sname='" + end + "')))";
		// �õ��йػ�վ����Ϣ
		Vector<Vector<String>> temp1 = MySQLite.query(sql1);
		Vector<Vector<String>> temp2 = MySQLite.query(sql2);
		// ����ѯ�����ϵ�һ��
		temp = Core.combine(temp, temp1, temp2);
		return temp;
	}
}
