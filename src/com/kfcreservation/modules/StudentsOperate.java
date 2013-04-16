package com.kfcreservation.modules;

import java.util.Vector;

import com.kfcreservation.core.MySQLiteHelper;

public class StudentsOperate {

	// All
	public static Vector<Vector<String>> getStudentsInfo() {
		String sql = "";
		Vector<Vector<String>> StudentsInfo = MySQLiteHelper.query(sql);
		return StudentsInfo;
	}

	// ById
	public static Vector<Vector<String>> getStudentsInfoById() {
		String sql = "";
		Vector<Vector<String>> StudentsInfoById = MySQLiteHelper.query(sql);
		return StudentsInfoById;
	}

	// ByType
	public static Vector<Vector<String>> getStudentsInfoByType() {
		String sql = "";
		Vector<Vector<String>> StudentsInfoByType = MySQLiteHelper.query(sql);
		return StudentsInfoByType;
	}

	// ByName
	public static Vector<Vector<String>> getStudentsInfoByName() {
		String sql = "";
		Vector<Vector<String>> StudentsInfoByName = MySQLiteHelper.query(sql);
		return StudentsInfoByName;
	}

}
