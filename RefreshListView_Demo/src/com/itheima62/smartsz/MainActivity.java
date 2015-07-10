

package com.itheima62.smartsz;

import android.app.Activity;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.Gravity;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.itheima62.view.RefreshListView;
import com.itheima62.view.RefreshListView.OnRefreshDataListener;

public class MainActivity extends Activity
{

	
	private RefreshListView	rlv;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		rlv = (RefreshListView) findViewById(R.id.rlv_testlistview);
		rlv.setAdapter(new MyAdapter());
		
		//下拉刷新头的用法
		refreshPullDownUse();
		
		//加载更多数据的用法
		loadingMoreDataUse();
	
	}
	
	private void loadingMoreDataUse() {
		//1.设置可以加载更多数据
		rlv.setIsRefreshFoot(true);
		
		//2. 设置加加载更多数据的监听
		rlv.setOnRefreshDataListener(new OnRefreshDataListener() {
			
			@Override
			public void refresdData() {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void loadingMore() {
				//只需覆盖此方法
				//添加刷新数据的代码,模拟耗时操作
				new Thread(){
					public void run() {
						SystemClock.sleep(2000);
						runOnUiThread(new Runnable() {
							
							@Override
							public void run() {
								// TODO Auto-generated method stub
								//2秒后 假设数据获取成功
								rlv.refreshStateFinish();//调用listvew的这个方法处理刷新结果状态改变，显示视图
							}
						});
					
					};
				}.start();
			}
		});
	}

	private void refreshPullDownUse() {
		//如果想做下拉刷新，请看下面步骤
				//1. 设置可以下拉刷新
				rlv.setIsRefreshHead(true);
				//2. 设置下拉刷新数据的监听器:OnRefreshDataListener
				rlv.setOnRefreshDataListener(new OnRefreshDataListener() {
					
					@Override
					public void refresdData() {
						//添加刷新数据的代码,模拟耗时操作
						new Thread(){
							public void run() {
								SystemClock.sleep(2000);
								runOnUiThread(new Runnable() {
									
									@Override
									public void run() {
										// TODO Auto-generated method stub
										//2秒后 假设数据获取成功
										rlv.refreshStateFinish();//调用listvew的这个方法处理刷新结果状态改变，显示视图
									}
								});
							
							};
						}.start();
						
					}
					
					@Override
					public void loadingMore() {
						// TODO Auto-generated method stub
						
					}
				});		
	}

	private class MyAdapter extends BaseAdapter{

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return 80;
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			TextView tv = new TextView(getApplicationContext());
			tv.setGravity(Gravity.CENTER);
			tv.setTextSize(20);
			tv.setText("test" + position);
			return tv;
		}
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
