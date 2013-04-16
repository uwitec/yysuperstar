package com.kfcreservation.modules;

import java.util.Vector;

import com.kfcreservation.core.MySQLiteHelper;

public class ClassesTypeOperate {

	//All
	public static Vector<Vector<String>> getClassesType() {
		String sql = "SELECT _tid, classesType FROM ClassesType;";
		Vector<Vector<String>> ClassesType = MySQLiteHelper.query(sql);
		return ClassesType;
	}
	
	//ById
	public static Vector<Vector<String>> getClassesTypeById(int id) {
		String sql = "SELECT classesType FROM ClassesType WHERE _tid = " + id + ";";
		Vector<Vector<String>> ClassesTypeById = MySQLiteHelper.query(sql);
		return ClassesTypeById;
	}
}
