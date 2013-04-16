package com.kfcreservation.modules;

import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import com.kfcreservation.core.Core;
import com.kfcreservation.core.MySQLiteHelper;

public class ClassesOperate {

	static String TableName = "Classes";

	// Get Classes Info All
	public static Vector<Vector<String>> getClassesInfo() {
		String sql = "Select * From " + TableName + ";";
		Vector<Vector<String>> ClassesInfo = MySQLiteHelper.query(sql);
		return ClassesInfo;
	}

	// Get Classes Info ByType
	public static Vector<Vector<String>> getClassesInfoByType(String Type) {
		String sql = "Select * From " + TableName + " WHERE Type" + Type + ";";
		Vector<Vector<String>> ClassesInfo = MySQLiteHelper.query(sql);
		return ClassesInfo;
	}

	// Get Classes Info ById
	public static Vector<Vector<String>> getClassesInfoById(int id) {
		String sql = "Select * From " + TableName + " WHERE _cid = " + id + ";";
		// Vector<Vector<String>> ClassesInfo = MySQLite.query(sql);
		Vector<Vector<String>> ClassesInfo = MySQLiteHelper.query(sql);
		return ClassesInfo;
	}

	// Get Classes Info ForName
	public static Vector<Vector<String>> getClassesInfoForName() {
		String sql = "Select _cid,className,icon From " + TableName + ";";
		Vector<Vector<String>> ClassesName = MySQLiteHelper.query(sql);
		return ClassesName;
	}

	// Set Classes Info
	public static void setClassesInfo(HashMap<String, Object> ClassesInfo) {
		String sql = "INSERT INTO " + TableName + " "
				+ Core.insertFields(ClassesInfo) + ";";
		MySQLiteHelper.insert(sql);
	}

	// Update Classes Info Byid
	public static void updateClassesInfo(Map<String, String> ClassesInfo, int id) {
		// String sql = "UPDATE " +TableName+ "SET "
		// +Core.insertFields(ClassesInfo)+ "WHERE _cid = " +id+ ";";
	}
}
