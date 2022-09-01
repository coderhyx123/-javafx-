package com.hyx.ui;

import com.hyx.base.CommScene;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

public class LrcScene extends CommScene {

	public LrcScene(String imageurl, String lrc) {
		Button btn_back = new Button(null, new ImageView("com/hyx/icon/下箭头.png"));

		ImageView image = new ImageView(imageurl);
		// 匹配歌词
//		System.out.println(lrc);
//		Pattern p = Pattern.compile("([\\s\\S+])+");
//		Matcher m = p.matcher(lrc);
//		if (m.find()) {
//			System.out.println(m.group());
//		}
		Text t = new Text(lrc);
		ScrollPane sp = new ScrollPane();
		sp.setContent(t);

		HBox hbox = new HBox(btn_back);
		HBox hbox2 = new HBox(image, sp);
//		HBox hbox2 = new HBox(image);
		// 设置中间布

		btn_back.setStyle("-fx-background-color: transparent;");

		hbox2.setAlignment(Pos.TOP_CENTER);
		hbox2.setSpacing(10);
		getLayout().setCenter(hbox2);
		getLayout().setTop(hbox);
//		getStage().setTitle("歌词，歌手信息");

		image.setFitHeight(300);
		image.setFitWidth(300);

		btn_back.setOnAction(s -> {
			setScene(com.hyx.ui.HomeApp.hc);
		});
	}

}
