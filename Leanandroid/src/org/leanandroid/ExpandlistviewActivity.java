package org.leanandroid;

import com.start.util.MenuUtil;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class ExpandlistviewActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.expandlistview);
		ExpandableListView expandableListView = (ExpandableListView) findViewById(R.id.expandablelistview);
        expandableListView.setAdapter(adapter);
      //设置item点击的监听器
        expandableListView.setOnChildClickListener(new OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                    int groupPosition, int childPosition, long id) {
                Toast.makeText(
                		ExpandlistviewActivity.this,
                        "你点击了" + adapter.getChild(groupPosition, childPosition),
                        Toast.LENGTH_SHORT).show();
                return false;
            }
        });
	}
	ExpandableListAdapter adapter = new BaseExpandableListAdapter() {
        //设置组视图的图片
        int[] logos = new int[] { R.drawable.ic_launcher, R.drawable.ic_launcher,R.drawable.ic_launcher};
        //设置组视图的显示文字
        private String[] generalsTypes = new String[] { "魏", "蜀", "吴" };
        //子视图显示文字
        private String[][] generals = new String[][] {
                { "夏侯惇", "甄姬", "许褚", "郭嘉", "司马懿", "杨修" },
                { "马超", "张飞", "刘备", "诸葛亮", "黄月英", "赵云" },
                { "吕蒙", "陆逊", "孙权", "周瑜", "孙尚香" }

        };
        //子视图图片
        public int[][] generallogos = new int[][] {
                { R.drawable.ic_launcher, R.drawable.ic_launcher,
                        R.drawable.ic_launcher, R.drawable.ic_launcher,
                        R.drawable.ic_launcher, R.drawable.ic_launcher },
                { R.drawable.ic_launcher, R.drawable.ic_launcher,
                        R.drawable.ic_launcher, R.drawable.ic_launcher,
                        R.drawable.ic_launcher, R.drawable.ic_launcher },
                { R.drawable.ic_launcher, R.drawable.ic_launcher, R.drawable.ic_launcher,
                        R.drawable.ic_launcher, R.drawable.ic_launcher } };
        
        //自己定义一个获得文字信息的方法
        TextView getTextView() {
            AbsListView.LayoutParams lp = new AbsListView.LayoutParams(
                    ViewGroup.LayoutParams.FILL_PARENT, 64);
            TextView textView = new TextView(
            		ExpandlistviewActivity.this);
            textView.setLayoutParams(lp);
            textView.setGravity(Gravity.CENTER_VERTICAL);
            textView.setPadding(36, 0, 0, 0);
            textView.setTextSize(20);
            textView.setTextColor(Color.BLACK);
            return textView;
        }

        
        //重写ExpandableListAdapter中的各个方法
        @Override
        public int getGroupCount() {
            return generalsTypes.length;
        }

        @Override
        public Object getGroup(int groupPosition) {
            return generalsTypes[groupPosition];
        }

        @Override
        public long getGroupId(int groupPosition) {
            return groupPosition;
        }

        @Override
        public int getChildrenCount(int groupPosition) {
            return generals[groupPosition].length;
        }

        @Override
        public Object getChild(int groupPosition, int childPosition) {
            return generals[groupPosition][childPosition];
        }

        @Override
        public long getChildId(int groupPosition, int childPosition) {
            return childPosition;
        }

        @Override
        public boolean hasStableIds() {
            return true;
        }

        @Override
        public View getGroupView(int groupPosition, boolean isExpanded,
                View convertView, ViewGroup parent) {
            LinearLayout ll = new LinearLayout(
            		ExpandlistviewActivity.this);
            ll.setOrientation(0);
            ImageView logo = new ImageView(ExpandlistviewActivity.this);
            logo.setImageResource(logos[groupPosition]);
            logo.setPadding(50, 0, 0, 0);
            ll.addView(logo);
            TextView textView = getTextView();
            textView.setTextColor(Color.BLACK);
            textView.setText(getGroup(groupPosition).toString());
            ll.addView(textView);

            return ll;
        }

        @Override
        public View getChildView(int groupPosition, int childPosition,
                boolean isLastChild, View convertView, ViewGroup parent) {
            LinearLayout ll = new LinearLayout(
                    ExpandlistviewActivity.this);
            ll.setOrientation(0);
            ImageView generallogo = new ImageView(
            		ExpandlistviewActivity.this);
            generallogo
                    .setImageResource(generallogos[groupPosition][childPosition]);
            ll.addView(generallogo);
            TextView textView = getTextView();
            textView.setText(getChild(groupPosition, childPosition)
                    .toString());
            ll.addView(textView);
            return ll;
        }
        @Override
        public boolean isChildSelectable(int groupPosition,
                int childPosition) {
            return true;
        }

    };
    
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.expandlistview, menu);
		getMenuInflater().inflate(R.menu.menugeneral, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.action_menuactivity:
			MenuUtil.goToMain(this);
			break;
		default:
			return super.onOptionsItemSelected(item);
		}
		return true;
	}
}
