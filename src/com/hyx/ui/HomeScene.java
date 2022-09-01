package com.hyx.ui;

import java.io.File;
import java.net.MalformedURLException;

import com.hyx.base.CommScene;
import com.hyx.entity.Music;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Slider;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.util.Duration;

public class HomeScene extends CommScene {
	boolean mouse = false;//
	int index_song = 0;// 定义选中的歌曲行数
	Media mediaaa;// 定义媒体
	MediaPlayer mplayerss = null;// 定义播放器
	String bjimgpath = "com/hyx/images/881.jpg";// 初始化背景图片
	// *************
	TextField searchsong = new TextField();
	Button search = new Button(null, new ImageView("com/hyx/icon/搜索.png"));

	Button themin = new Button(null, new ImageView("com/hyx/icon/下箭头.png"));
	Button cancel_fullScreen = new Button(null, new ImageView("com/hyx/icon/取消全屏.png"));
	Button big = new Button(null, new ImageView("com/hyx/icon/全屏.png"));
//	Button mores = new Button(null, new ImageView("com/hyx/icon/更多.png"));
	MenuItem mt = new MenuItem("更换壁纸", new ImageView("com/hyx/icon/添加图片.png"));
	MenuButton mb = new MenuButton(null, new ImageView("com/hyx/icon/更多.png"), mt);
	Button close = new Button(null, new ImageView("com/hyx/icon/删除_关闭.png"));

	HBox hboxx1 = new HBox(searchsong, search);
	HBox hboxx2 = new HBox(themin, cancel_fullScreen, big, mb, close);
	HBox hboxx3 = new HBox(hboxx1, hboxx2);
	// *************

	ImageView playOrStop = new ImageView("com/hyx/icon/播放.png");

	Button btn_lrc = new Button(null, new ImageView("com/hyx/icon/上箭头.png"));// 歌曲详情
	Button btn_play = new Button(null, playOrStop);// 播放
	Button btn_last = new Button(null, new ImageView("com/hyx/icon/上一曲.png"));// 上一曲
	Button btn_next = new Button(null, new ImageView("com/hyx/icon/下一曲.png"));// 下一曲
	Button stop_b = new Button(null, new ImageView("com/hyx/icon/停止.png"));// 停止

	Button lastpage = new Button(null, new ImageView("com/hyx/icon/左箭头.png"));// 翻页上一页
	Button nextpage = new Button(null, new ImageView("com/hyx/icon/右箭头.png"));// 翻页下一页
	Slider s1 = new Slider(0, 1, 0.2);// 音量
	Slider s2 = new Slider(0, 1, 0);// 进度条

	TableView<Music> table = new TableView<Music>();
	TableColumn<Music, String> c2 = new TableColumn<>("歌曲名");
	TableColumn<Music, String> c3 = new TableColumn<>("歌手名");
	TableColumn<Music, String> c4 = new TableColumn<>("时长");

	// 添加右键菜单
	MenuItem m1 = new MenuItem("    播放    ", new ImageView("com/hyx/icon/播放小.png"));
	MenuItem m2 = new MenuItem("    下载    ", new ImageView("com/hyx/icon/下载.png"));
	ContextMenu cmenu = new ContextMenu(m1, m2);

	HBox hbox = new HBox(btn_lrc, btn_last, btn_play, btn_next, stop_b, lastpage, nextpage, s1);
	VBox vbox_big = new VBox(s2, hbox);

//	boolean flag = true;// 判断音乐暂停或者播放的标志
	boolean flags = false;

	int countPage = 1;// 当前歌曲信息第一页

	// 主场景
	public HomeScene() {

		// 设置按钮显示文字
		themin.setTooltip(new Tooltip("最小化到任务栏"));
		cancel_fullScreen.setTooltip(new Tooltip("取消全屏"));
		big.setTooltip(new Tooltip("全屏"));
		search.setTooltip(new Tooltip("搜索"));
		close.setTooltip(new Tooltip("关闭"));
		btn_last.setTooltip(new Tooltip("上一曲"));
		btn_play.setTooltip(new Tooltip("播放/暂停"));
		btn_next.setTooltip(new Tooltip("下一曲"));
		stop_b.setTooltip(new Tooltip("停止"));
		lastpage.setTooltip(new Tooltip("上一页"));
		nextpage.setTooltip(new Tooltip("下一页"));
		mb.setTooltip(new Tooltip("更多"));
		//
		getLayout().setStyle("-fx-background-image:url(" + bjimgpath + ");-fx-background-size:cover;");
//		getLayout().setStyle("-fx-background-image-color:translucent;");
		getLayout().setBottom(vbox_big);
		getLayout().setCenter(table);

		// 加载表格数据
		c2.setCellValueFactory(new PropertyValueFactory<>("audio_name"));
		c3.setCellValueFactory(new PropertyValueFactory<>("author_name"));
		c4.setCellValueFactory(new PropertyValueFactory<>("timelength"));

		c2.setPrefWidth(400);
		c3.setPrefWidth(200);
		c4.setPrefWidth(200);

		table.getColumns().addAll(c2, c3, c4);

		// 真加载表格在线数据

		// 设置

		hbox.setSpacing(40); // 设置底部组合样式
		hbox.setAlignment(Pos.CENTER);

		// 顶部****************
		getLayout().setTop(hboxx3);
		searchsong.setPromptText("请输入歌手歌曲名");
		hboxx3.setAlignment(Pos.TOP_CENTER);
		hboxx3.setSpacing(220);
		hboxx1.setSpacing(30);
		hboxx2.setSpacing(30);
		// 设置搜索框大小
		searchsong.setPrefHeight(40);

		// 设置按钮透明
		table.setStyle("-fx-background-color: transparent;");
		searchsong.setStyle("-fx-background-color: transparent;");
		big.setStyle("-fx-background-color: transparent;");
		close.setStyle("-fx-background-color: transparent;");
		cancel_fullScreen.setStyle("-fx-background-color: transparent;");
		search.setStyle("-fx-background-color: transparent;");
		btn_play.setStyle("-fx-background-color: transparent;");
		stop_b.setStyle("-fx-background-color: transparent;");
		lastpage.setStyle("-fx-background-color: transparent;");
		nextpage.setStyle("-fx-background-color: transparent;");
		themin.setStyle("-fx-background-color: transparent;");// 最小化事件透明
		mb.setStyle("-fx-background-color: transparent;");
		btn_last.setStyle("-fx-background-color: transparent;");// 最小化事件透明
		btn_next.setStyle("-fx-background-color: transparent;");
		btn_lrc.setStyle("-fx-background-color: transparent;");

//		最小化事件
		themin.setOnAction(m -> {
			getStage().setIconified(true);
		});

		// 关闭事件
		close.setOnAction(a -> {
			Platform.exit();
		});
		// 更换壁纸事件
		mt.setOnAction(p -> {
			FileChooser fc = new FileChooser();
			// 设置文件类型过滤器
			ExtensionFilter ft = new FileChooser.ExtensionFilter("img", new String[] { "*.jpg", "*.png" });
			fc.getExtensionFilters().add(ft);
			File img = fc.showOpenDialog(getWindow());

			// 检查有没有选中文件
			if (img == null) {
				return;
			}

			try {
				bjimgpath = img.toURL().toString();

			} catch (Exception e) {
				e.printStackTrace();
			}
			getLayout().setStyle("-fx-background-image:url(" + bjimgpath + ");-fx-background-size:cover;");
		});
		// 全屏事件
		big.setOnAction(a -> {
			getStage().setFullScreen(true);// 全屏
//			getStage().setMaximized(true);// 窗口最大化
		});
		cancel_fullScreen.setOnAction(a -> {
			getStage().setFullScreen(false);
		});

		// 监听搜索框
		search.setOnAction(s -> {
			flash();
		});
		//
		searchsong.setOnKeyReleased(a -> {
			// 判断按的是不是回车键
			if (a.getCode() == KeyCode.ENTER) {
				flash();
			}
		});
		// 顶部代码结束******************
		// 监听选中的表格
		table.setOnMouseClicked(d -> {
			try {
				String newplayurll = table.getSelectionModel().getSelectedItem().getPlay_url();
				if (newplayurll == null)
					return;
				// 判断是双击还是右击
				if (d.getClickCount() == 2) {
					index_song = table.getSelectionModel().getSelectedIndex();
					String newplayurl = table.getSelectionModel().getSelectedItem().getPlay_url();
					if (newplayurl == null) {
						alertError("请选择要播放的歌曲");
						return;
					}
					if (mplayerss != null) {
						stop(mplayerss);
					}
					flags = true;
					playwebMusic(newplayurl, flags);
//				flag = true;
				} else if (d.getButton() == MouseButton.SECONDARY) {
//				System.out.println("这是右击");
					cmenu.show(getStage(), d.getScreenX(), d.getScreenY());
				}
			} catch (Exception e) {
			}
		});

		// 打开歌曲详情信息的窗口
		btn_lrc.setOnAction(s -> {
			if (mplayerss != null) {
				String imageurl = table.getSelectionModel().getSelectedItem().getImageurl();
				String lrc = table.getSelectionModel().getSelectedItem().getLrc();
				setScene(new LrcScene(imageurl, lrc));
			}

		});
		// 监听下一曲按钮
		btn_next.setOnAction(n -> {
			++index_song;
			table.getSelectionModel().clearAndSelect(index_song);
			String newplayurl = table.getSelectionModel().getSelectedItem().getPlay_url();

			if (newplayurl == null) {
				alertError("请选择要播放的歌曲");
				return;
			}
			if (mplayerss != null) {
				stop(mplayerss);
			}
			flags = true;
			playwebMusic(newplayurl, flags);
		});
		// 监听上一曲按钮
		btn_last.setOnAction(l -> {
			--index_song;
			if (index_song < 0) {
				index_song = 0;
				alert("这已经是第一首歌了");
				stop(mplayerss);
				change(mplayerss, false);
				return;
			}
			if (index_song >= 0) {
				table.getSelectionModel().clearAndSelect(index_song);
			}
			String newplayurl = table.getSelectionModel().getSelectedItem().getPlay_url();

			if (newplayurl == null) {
				alertError("请选择要播放的歌曲");
				return;
			}
			if (mplayerss != null) {
				stop(mplayerss);
			}
			flags = true;
			playwebMusic(newplayurl, flags);
		});
		// 监听播放按钮
		btn_play.setOnAction(a -> {
			if (flags == true) {
				flags = false;
			} else {
				flags = true;
			}
			change(mplayerss, flags);

		});

		// 监听停止按钮
		stop_b.setOnAction(b -> {
			stop(mplayerss);
			btn_play.setGraphic(new ImageView("com/hyx/icon/播放.png"));
			flags = true;
		});

		// 监听下一页按钮
		nextpage.setOnAction(p -> {
			++countPage;
			flash();
		});
//		 监听上一页按钮
		lastpage.setOnAction(p -> {
			if (countPage > 1) {
				--countPage;
			} else {
				alert("这已经是第一页了");
				return;
			}
			flash();
		});

		// 播放功能有问题
//		m1.setOnAction(a -> {
//			String newplayurl = table.getSelectionModel().getSelectedItem().getPlay_url();
//			if (newplayurl == null) {
//				alertError("请先选择要播放的歌曲！");
//				return;
//			}
//			flag = true;
//			playerMusic(newplayurl);
//		});

		// 重写播放功能
		m1.setOnAction(b -> {
			index_song = table.getSelectionModel().getSelectedIndex();
			String newplayurl = table.getSelectionModel().getSelectedItem().getPlay_url();
			if (newplayurl == null) {
				alertError("请选择要播放的歌曲");
				return;
			}
			if (mplayerss != null) {
				stop(mplayerss);
			}
			flags = true;
			playwebMusic(newplayurl, flags);

		});
		// 下载按钮
		m2.setOnAction(c -> {
			Downmusic downmusic = new Downmusic();
			String newplayurl = table.getSelectionModel().getSelectedItem().getPlay_url();
			String nameFile = table.getSelectionModel().getSelectedItem().getAudio_name();
			DirectoryChooser directoryChooser = new DirectoryChooser();
			File file = directoryChooser.showDialog(getWindow());
			String path = file.getPath();
			if (newplayurl == null || nameFile == null) {
				alertError("请选择歌曲");
				return;
			}

			File filess = downmusic.down(path, newplayurl, nameFile);
			boolean b = confirm("下载成功，是否播放该歌曲");
			if (b) {
				try {
					if (mplayerss != null)
						stop(mplayerss);
					flags = true;
					playerMusicYS(filess, flags);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

		});

		// 按下
		s2.setOnMousePressed(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				mouse = true;
			}
		});

		// 释放
		s2.setOnMouseReleased(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				try {
					mplayerss.seek(Duration.seconds(s2.getValue()));
				} catch (Exception e) {
					alertError("未播放歌曲 ");
				}

				mouse = false;

			}
		});
	}

	// 播放本地歌曲
	public void playerMusicYS(File filess, boolean flags) {

		try {
			mediaaa = new Media(filess.toURL().toString());
			mplayerss = new MediaPlayer(mediaaa);
			// 调节音量大小
			adjustvolume();
		} catch (MalformedURLException e) {
			e.printStackTrace();

		}
		// 该变样式事件
		change(mplayerss, flags);

//		// 停止播放事件
//		if (stopflag) {
//			stop(mplayerss);
//		}

	}

	// 播放网络歌曲
	public void playwebMusic(String urlss, boolean flagss) {
		try {
			mediaaa = new Media(urlss.toString());
			mplayerss = new MediaPlayer(mediaaa);
			adjustvolume();
		} catch (Exception e) {
		}
		// 该变样式事件
		change(mplayerss, flags);
	}

	// 样式改变封装
	public void change(MediaPlayer m, boolean flags) {

		if (flags) {
			btn_play.setGraphic(new ImageView("com/hyx/icon/暂停.png"));
			m.play();
			flags = false;
		} else {
			btn_play.setGraphic(new ImageView("com/hyx/icon/播放.png"));
			m.pause();
			flags = true;
		}

	}

	// 停止播放
	public void stop(MediaPlayer mm) {
		mm.stop();
	}

	// 刷新界面
	public void flash() {
		table.getItems().clear();
		GetRealPlayUrl getrealurl = new GetRealPlayUrl(searchsong, countPage);
		table.getItems().addAll(getrealurl.searchMusic());
	}

	// 调节音量大小和进度条
	public void adjustvolume() {
		mplayerss.setOnReady(new Runnable() {
			@Override
			public void run() {
				mplayerss.volumeProperty().bind(s1.valueProperty());

				s2.setValue(0);
				s2.setMin(0);
				s2.setMax(mplayerss.getTotalDuration().toSeconds());

				mplayerss.currentTimeProperty().addListener(new ChangeListener<Duration>() {

					@Override
					public void changed(ObservableValue<? extends Duration> observable, Duration oldValue,
							Duration newValue) {
						if (mouse == false) {
							s2.setValue(newValue.toSeconds());
						}

					}
				});

			}
		});
	}

}

//
//// 翻页
//public void pagePuls() {
//	table.getItems().clear();
//	GetRealPlayUrl getrealurl = new GetRealPlayUrl(searchsong, countPage);
//	table.getItems().addAll(getrealurl.searchMusic());
//}

// 暂停播放按钮样式方法
//public void playerMusic(String newplayrul) {
//	if (!playurl.equals(newplayrul)) {
//		Media media = new Media(newplayrul);
//		MediaPlayer mplayer = new MediaPlayer(media);
//		new MediaView(mplayer);
//		boolean newflag = true;
//		if (newflag) {
//			btn_play.setGraphic(new ImageView("com/hyx/icon/暂停.png"));
//			mplayer.play();
//			newflag = false;
//		} else {
//			btn_play.setGraphic(new ImageView("com/hyx/icon/播放.png"));
//			mplayer.pause();
//			newflag = true;
//		}
//	} else {
//		if (flag) {
//			btn_play.setGraphic(new ImageView("com/hyx/icon/暂停.png"));
//			mplayer.play();
//			flag = false;
//		} else {
//			btn_play.setGraphic(new ImageView("com/hyx/icon/播放.png"));
//			mplayer.pause();
//			flag = true;
//		}
//	}
//
//}