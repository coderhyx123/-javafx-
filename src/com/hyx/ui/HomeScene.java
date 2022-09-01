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
	int index_song = 0;// ����ѡ�еĸ�������
	Media mediaaa;// ����ý��
	MediaPlayer mplayerss = null;// ���岥����
	String bjimgpath = "com/hyx/images/881.jpg";// ��ʼ������ͼƬ
	// *************
	TextField searchsong = new TextField();
	Button search = new Button(null, new ImageView("com/hyx/icon/����.png"));

	Button themin = new Button(null, new ImageView("com/hyx/icon/�¼�ͷ.png"));
	Button cancel_fullScreen = new Button(null, new ImageView("com/hyx/icon/ȡ��ȫ��.png"));
	Button big = new Button(null, new ImageView("com/hyx/icon/ȫ��.png"));
//	Button mores = new Button(null, new ImageView("com/hyx/icon/����.png"));
	MenuItem mt = new MenuItem("������ֽ", new ImageView("com/hyx/icon/���ͼƬ.png"));
	MenuButton mb = new MenuButton(null, new ImageView("com/hyx/icon/����.png"), mt);
	Button close = new Button(null, new ImageView("com/hyx/icon/ɾ��_�ر�.png"));

	HBox hboxx1 = new HBox(searchsong, search);
	HBox hboxx2 = new HBox(themin, cancel_fullScreen, big, mb, close);
	HBox hboxx3 = new HBox(hboxx1, hboxx2);
	// *************

	ImageView playOrStop = new ImageView("com/hyx/icon/����.png");

	Button btn_lrc = new Button(null, new ImageView("com/hyx/icon/�ϼ�ͷ.png"));// ��������
	Button btn_play = new Button(null, playOrStop);// ����
	Button btn_last = new Button(null, new ImageView("com/hyx/icon/��һ��.png"));// ��һ��
	Button btn_next = new Button(null, new ImageView("com/hyx/icon/��һ��.png"));// ��һ��
	Button stop_b = new Button(null, new ImageView("com/hyx/icon/ֹͣ.png"));// ֹͣ

	Button lastpage = new Button(null, new ImageView("com/hyx/icon/���ͷ.png"));// ��ҳ��һҳ
	Button nextpage = new Button(null, new ImageView("com/hyx/icon/�Ҽ�ͷ.png"));// ��ҳ��һҳ
	Slider s1 = new Slider(0, 1, 0.2);// ����
	Slider s2 = new Slider(0, 1, 0);// ������

	TableView<Music> table = new TableView<Music>();
	TableColumn<Music, String> c2 = new TableColumn<>("������");
	TableColumn<Music, String> c3 = new TableColumn<>("������");
	TableColumn<Music, String> c4 = new TableColumn<>("ʱ��");

	// ����Ҽ��˵�
	MenuItem m1 = new MenuItem("    ����    ", new ImageView("com/hyx/icon/����С.png"));
	MenuItem m2 = new MenuItem("    ����    ", new ImageView("com/hyx/icon/����.png"));
	ContextMenu cmenu = new ContextMenu(m1, m2);

	HBox hbox = new HBox(btn_lrc, btn_last, btn_play, btn_next, stop_b, lastpage, nextpage, s1);
	VBox vbox_big = new VBox(s2, hbox);

//	boolean flag = true;// �ж�������ͣ���߲��ŵı�־
	boolean flags = false;

	int countPage = 1;// ��ǰ������Ϣ��һҳ

	// ������
	public HomeScene() {

		// ���ð�ť��ʾ����
		themin.setTooltip(new Tooltip("��С����������"));
		cancel_fullScreen.setTooltip(new Tooltip("ȡ��ȫ��"));
		big.setTooltip(new Tooltip("ȫ��"));
		search.setTooltip(new Tooltip("����"));
		close.setTooltip(new Tooltip("�ر�"));
		btn_last.setTooltip(new Tooltip("��һ��"));
		btn_play.setTooltip(new Tooltip("����/��ͣ"));
		btn_next.setTooltip(new Tooltip("��һ��"));
		stop_b.setTooltip(new Tooltip("ֹͣ"));
		lastpage.setTooltip(new Tooltip("��һҳ"));
		nextpage.setTooltip(new Tooltip("��һҳ"));
		mb.setTooltip(new Tooltip("����"));
		//
		getLayout().setStyle("-fx-background-image:url(" + bjimgpath + ");-fx-background-size:cover;");
//		getLayout().setStyle("-fx-background-image-color:translucent;");
		getLayout().setBottom(vbox_big);
		getLayout().setCenter(table);

		// ���ر������
		c2.setCellValueFactory(new PropertyValueFactory<>("audio_name"));
		c3.setCellValueFactory(new PropertyValueFactory<>("author_name"));
		c4.setCellValueFactory(new PropertyValueFactory<>("timelength"));

		c2.setPrefWidth(400);
		c3.setPrefWidth(200);
		c4.setPrefWidth(200);

		table.getColumns().addAll(c2, c3, c4);

		// ����ر����������

		// ����

		hbox.setSpacing(40); // ���õײ������ʽ
		hbox.setAlignment(Pos.CENTER);

		// ����****************
		getLayout().setTop(hboxx3);
		searchsong.setPromptText("��������ָ�����");
		hboxx3.setAlignment(Pos.TOP_CENTER);
		hboxx3.setSpacing(220);
		hboxx1.setSpacing(30);
		hboxx2.setSpacing(30);
		// �����������С
		searchsong.setPrefHeight(40);

		// ���ð�ť͸��
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
		themin.setStyle("-fx-background-color: transparent;");// ��С���¼�͸��
		mb.setStyle("-fx-background-color: transparent;");
		btn_last.setStyle("-fx-background-color: transparent;");// ��С���¼�͸��
		btn_next.setStyle("-fx-background-color: transparent;");
		btn_lrc.setStyle("-fx-background-color: transparent;");

//		��С���¼�
		themin.setOnAction(m -> {
			getStage().setIconified(true);
		});

		// �ر��¼�
		close.setOnAction(a -> {
			Platform.exit();
		});
		// ������ֽ�¼�
		mt.setOnAction(p -> {
			FileChooser fc = new FileChooser();
			// �����ļ����͹�����
			ExtensionFilter ft = new FileChooser.ExtensionFilter("img", new String[] { "*.jpg", "*.png" });
			fc.getExtensionFilters().add(ft);
			File img = fc.showOpenDialog(getWindow());

			// �����û��ѡ���ļ�
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
		// ȫ���¼�
		big.setOnAction(a -> {
			getStage().setFullScreen(true);// ȫ��
//			getStage().setMaximized(true);// �������
		});
		cancel_fullScreen.setOnAction(a -> {
			getStage().setFullScreen(false);
		});

		// ����������
		search.setOnAction(s -> {
			flash();
		});
		//
		searchsong.setOnKeyReleased(a -> {
			// �жϰ����ǲ��ǻس���
			if (a.getCode() == KeyCode.ENTER) {
				flash();
			}
		});
		// �����������******************
		// ����ѡ�еı��
		table.setOnMouseClicked(d -> {
			try {
				String newplayurll = table.getSelectionModel().getSelectedItem().getPlay_url();
				if (newplayurll == null)
					return;
				// �ж���˫�������һ�
				if (d.getClickCount() == 2) {
					index_song = table.getSelectionModel().getSelectedIndex();
					String newplayurl = table.getSelectionModel().getSelectedItem().getPlay_url();
					if (newplayurl == null) {
						alertError("��ѡ��Ҫ���ŵĸ���");
						return;
					}
					if (mplayerss != null) {
						stop(mplayerss);
					}
					flags = true;
					playwebMusic(newplayurl, flags);
//				flag = true;
				} else if (d.getButton() == MouseButton.SECONDARY) {
//				System.out.println("�����һ�");
					cmenu.show(getStage(), d.getScreenX(), d.getScreenY());
				}
			} catch (Exception e) {
			}
		});

		// �򿪸���������Ϣ�Ĵ���
		btn_lrc.setOnAction(s -> {
			if (mplayerss != null) {
				String imageurl = table.getSelectionModel().getSelectedItem().getImageurl();
				String lrc = table.getSelectionModel().getSelectedItem().getLrc();
				setScene(new LrcScene(imageurl, lrc));
			}

		});
		// ������һ����ť
		btn_next.setOnAction(n -> {
			++index_song;
			table.getSelectionModel().clearAndSelect(index_song);
			String newplayurl = table.getSelectionModel().getSelectedItem().getPlay_url();

			if (newplayurl == null) {
				alertError("��ѡ��Ҫ���ŵĸ���");
				return;
			}
			if (mplayerss != null) {
				stop(mplayerss);
			}
			flags = true;
			playwebMusic(newplayurl, flags);
		});
		// ������һ����ť
		btn_last.setOnAction(l -> {
			--index_song;
			if (index_song < 0) {
				index_song = 0;
				alert("���Ѿ��ǵ�һ�׸���");
				stop(mplayerss);
				change(mplayerss, false);
				return;
			}
			if (index_song >= 0) {
				table.getSelectionModel().clearAndSelect(index_song);
			}
			String newplayurl = table.getSelectionModel().getSelectedItem().getPlay_url();

			if (newplayurl == null) {
				alertError("��ѡ��Ҫ���ŵĸ���");
				return;
			}
			if (mplayerss != null) {
				stop(mplayerss);
			}
			flags = true;
			playwebMusic(newplayurl, flags);
		});
		// �������Ű�ť
		btn_play.setOnAction(a -> {
			if (flags == true) {
				flags = false;
			} else {
				flags = true;
			}
			change(mplayerss, flags);

		});

		// ����ֹͣ��ť
		stop_b.setOnAction(b -> {
			stop(mplayerss);
			btn_play.setGraphic(new ImageView("com/hyx/icon/����.png"));
			flags = true;
		});

		// ������һҳ��ť
		nextpage.setOnAction(p -> {
			++countPage;
			flash();
		});
//		 ������һҳ��ť
		lastpage.setOnAction(p -> {
			if (countPage > 1) {
				--countPage;
			} else {
				alert("���Ѿ��ǵ�һҳ��");
				return;
			}
			flash();
		});

		// ���Ź���������
//		m1.setOnAction(a -> {
//			String newplayurl = table.getSelectionModel().getSelectedItem().getPlay_url();
//			if (newplayurl == null) {
//				alertError("����ѡ��Ҫ���ŵĸ�����");
//				return;
//			}
//			flag = true;
//			playerMusic(newplayurl);
//		});

		// ��д���Ź���
		m1.setOnAction(b -> {
			index_song = table.getSelectionModel().getSelectedIndex();
			String newplayurl = table.getSelectionModel().getSelectedItem().getPlay_url();
			if (newplayurl == null) {
				alertError("��ѡ��Ҫ���ŵĸ���");
				return;
			}
			if (mplayerss != null) {
				stop(mplayerss);
			}
			flags = true;
			playwebMusic(newplayurl, flags);

		});
		// ���ذ�ť
		m2.setOnAction(c -> {
			Downmusic downmusic = new Downmusic();
			String newplayurl = table.getSelectionModel().getSelectedItem().getPlay_url();
			String nameFile = table.getSelectionModel().getSelectedItem().getAudio_name();
			DirectoryChooser directoryChooser = new DirectoryChooser();
			File file = directoryChooser.showDialog(getWindow());
			String path = file.getPath();
			if (newplayurl == null || nameFile == null) {
				alertError("��ѡ�����");
				return;
			}

			File filess = downmusic.down(path, newplayurl, nameFile);
			boolean b = confirm("���سɹ����Ƿ񲥷Ÿø���");
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

		// ����
		s2.setOnMousePressed(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				mouse = true;
			}
		});

		// �ͷ�
		s2.setOnMouseReleased(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				try {
					mplayerss.seek(Duration.seconds(s2.getValue()));
				} catch (Exception e) {
					alertError("δ���Ÿ��� ");
				}

				mouse = false;

			}
		});
	}

	// ���ű��ظ���
	public void playerMusicYS(File filess, boolean flags) {

		try {
			mediaaa = new Media(filess.toURL().toString());
			mplayerss = new MediaPlayer(mediaaa);
			// ����������С
			adjustvolume();
		} catch (MalformedURLException e) {
			e.printStackTrace();

		}
		// �ñ���ʽ�¼�
		change(mplayerss, flags);

//		// ֹͣ�����¼�
//		if (stopflag) {
//			stop(mplayerss);
//		}

	}

	// �����������
	public void playwebMusic(String urlss, boolean flagss) {
		try {
			mediaaa = new Media(urlss.toString());
			mplayerss = new MediaPlayer(mediaaa);
			adjustvolume();
		} catch (Exception e) {
		}
		// �ñ���ʽ�¼�
		change(mplayerss, flags);
	}

	// ��ʽ�ı��װ
	public void change(MediaPlayer m, boolean flags) {

		if (flags) {
			btn_play.setGraphic(new ImageView("com/hyx/icon/��ͣ.png"));
			m.play();
			flags = false;
		} else {
			btn_play.setGraphic(new ImageView("com/hyx/icon/����.png"));
			m.pause();
			flags = true;
		}

	}

	// ֹͣ����
	public void stop(MediaPlayer mm) {
		mm.stop();
	}

	// ˢ�½���
	public void flash() {
		table.getItems().clear();
		GetRealPlayUrl getrealurl = new GetRealPlayUrl(searchsong, countPage);
		table.getItems().addAll(getrealurl.searchMusic());
	}

	// ����������С�ͽ�����
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
//// ��ҳ
//public void pagePuls() {
//	table.getItems().clear();
//	GetRealPlayUrl getrealurl = new GetRealPlayUrl(searchsong, countPage);
//	table.getItems().addAll(getrealurl.searchMusic());
//}

// ��ͣ���Ű�ť��ʽ����
//public void playerMusic(String newplayrul) {
//	if (!playurl.equals(newplayrul)) {
//		Media media = new Media(newplayrul);
//		MediaPlayer mplayer = new MediaPlayer(media);
//		new MediaView(mplayer);
//		boolean newflag = true;
//		if (newflag) {
//			btn_play.setGraphic(new ImageView("com/hyx/icon/��ͣ.png"));
//			mplayer.play();
//			newflag = false;
//		} else {
//			btn_play.setGraphic(new ImageView("com/hyx/icon/����.png"));
//			mplayer.pause();
//			newflag = true;
//		}
//	} else {
//		if (flag) {
//			btn_play.setGraphic(new ImageView("com/hyx/icon/��ͣ.png"));
//			mplayer.play();
//			flag = false;
//		} else {
//			btn_play.setGraphic(new ImageView("com/hyx/icon/����.png"));
//			mplayer.pause();
//			flag = true;
//		}
//	}
//
//}