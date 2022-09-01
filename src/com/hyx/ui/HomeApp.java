package com.hyx.ui;

import com.hyx.base.BaseApp;

import javafx.scene.image.Image;

public class HomeApp extends BaseApp {
	static HomeScene hc = new HomeScene();

	@Override
	public void ready() throws Exception {
		getStage().setTitle("免费歌曲下载，仅供交流学习，vip歌曲只能下载试听部分");
		getStage().getIcons().add(new Image("com/hyx/icon/s.jpg"));
		getStage().setScene(hc);

//		getStage().initStyle(StageStyle.TRANSPARENT);// 隐藏头标题
	}

	public static void main(String[] args) {
		launch(args);
	}

}
