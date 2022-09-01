package com.hyx.ui;

import com.hyx.base.BaseApp;

import javafx.scene.image.Image;

public class HomeApp extends BaseApp {
	static HomeScene hc = new HomeScene();

	@Override
	public void ready() throws Exception {
		getStage().setTitle("��Ѹ������أ���������ѧϰ��vip����ֻ��������������");
		getStage().getIcons().add(new Image("com/hyx/icon/s.jpg"));
		getStage().setScene(hc);

//		getStage().initStyle(StageStyle.TRANSPARENT);// ����ͷ����
	}

	public static void main(String[] args) {
		launch(args);
	}

}
