# RefreshListView

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
