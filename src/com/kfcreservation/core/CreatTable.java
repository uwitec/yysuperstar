package com.kfcreservation.core;

public class CreatTable {
	public static void creattable() {
		try {
			String sql[] = new String[] 
			{
					
			};
			
			for (String o : sql) {
				MySQLite.createTable(o);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
