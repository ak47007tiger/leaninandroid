package org.leanandroid;

import loc.sqliteops.DatabaseHelper;
import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class SqliteActivity extends Activity {

	DatabaseHelper databaseHelper;
	EditText sql;
	EditText txt;
	TextView table;
	int version = 1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sqlite);
		sql = (EditText) findViewById(R.id.sql);
		txt = (EditText) findViewById(R.id.txt);
		table = (TextView) findViewById(R.id.table);
		databaseHelper = new DatabaseHelper(this, "test.db3", version);
	}

	public void add(View v) {
		SQLiteDatabase database = databaseHelper.getReadableDatabase();
		database.execSQL("insert into test values(null,?)", new String[] { txt
				.getText().toString() });
	}

	public void del(View v) {
		databaseHelper.getReadableDatabase().execSQL(
				"delete from test where txt = ?",
				new String[] { txt.getText().toString() });
	}

	public void update(View v) {
		databaseHelper.getReadableDatabase().execSQL(
				"update test set txt = ? where id = 1",
				new String[] { txt.getText().toString() });
	}

	public void search(View v) {
		Cursor cursor = databaseHelper.getReadableDatabase().rawQuery(
				"select * from test where txt = ?",
				new String[] { txt.getText().toString() });
		while (cursor.moveToNext()) {
			txt.setText("id:" + cursor.getInt(0) + " | txt:" + cursor.getString(1));
		}
		updateTV_Table();
	}

	public void execsql(View v) {
		try{
			databaseHelper.getReadableDatabase().execSQL(sql.getText().toString());
		}catch(Exception e){
			e.getMessage();
		}
	}

	private void updateTV_Table(){
		table.setText("");
		Cursor cursor = databaseHelper.getReadableDatabase().rawQuery(
				"select * from test",
				new String[] {});
		while (cursor.moveToNext()) {
			table.append("id:" + cursor.getInt(0) + " | txt:" + cursor.getString(1) + "\n");
		}
	} 
	@Override
	protected void onDestroy() {
		databaseHelper.close();
		super.onDestroy();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.sqlite, menu);
		return true;
	}

}
