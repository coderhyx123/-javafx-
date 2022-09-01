package com.hyx.base;

import java.util.Optional;

import javafx.application.Application;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * 窗体基类
 */
public abstract class BaseApp extends Application {
	// 舞台
	private Stage stage;
	// 当前场景
	private BaseScene scene;

	// 不传场景将会自动创建场景
	public BaseApp() {
	}

	// 传入场景将使用传的场景
	public BaseApp(BaseScene scene) {
		this.scene = scene;
	}

	/**
	 * 开始
	 */
	@Override
	public final void start(Stage stage) throws Exception {
		_launched = true;
		this.stage = stage;
		if (scene == null)
			this.scene = new BaseScene(-1, -1) {
			};
		this.setScene(scene);
		this.ready();
		this.showAndTryWait();
	}

	/**
	 * 准备界面
	 */
	public abstract void ready() throws Exception;

	/**
	 * 打开新的窗体
	 */
	public void launch() {
		launch((Stage) null);
	}

	/**
	 * 打开新的窗体
	 */
	public void launch(BaseApp owner) {
		launch(owner.stage);
	}

	/**
	 * 打开新的窗体
	 */
	public void launch(BaseScene owner) {
		launch(owner.getStage());
	}

	/**
	 * 打开新的窗体
	 */
	public void launch(Stage owner) {
		if (_launched) {
			new RuntimeException("一个BaseApp只允许launch一次，以免发生递归死循环！！！").printStackTrace();
			return;
		}
		try {
			Stage st = new Stage();
			if (owner != null) { // 模式窗口
				st.initModality(Modality.APPLICATION_MODAL);
				st.initOwner(owner);
			}
			// 启动
			this.start(st);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 防止递归死循环，只允许 launch 一次
	private boolean _launched = false;

	/**
	 * 显示舞台，并尝试等待
	 */
	private void showAndTryWait() {
		try {
			stage.showAndWait();
		} catch (Exception ex) {
			stage.show();
		}
	}

	/**
	 * 设置窗体尺寸
	 */
	public void setSize(double width, double height) {
		stage.setWidth(width);
		stage.setHeight(height);
	}

	/**
	 * 弹出消息框
	 */
	public void alert(String msg) {
		new Alert(Alert.AlertType.INFORMATION, msg).showAndWait();
	}

	/**
	 * 弹出错误提示
	 */
	public void alertError(String msg) {
		new Alert(Alert.AlertType.ERROR, msg).showAndWait();
	}

	/**
	 * 弹出询问框
	 */
	public boolean confirm(String msg) {
		Optional<ButtonType> r = new Alert(Alert.AlertType.CONFIRMATION, msg).showAndWait();
		return r.get() == ButtonType.OK;
	}

	/**
	 * 获取舞台对象
	 */
	public Stage getStage() {
		return stage;
	}

	/**
	 * 获取当前场景
	 */
	public BaseScene getScene() {
		return scene;
	}

	/**
	 * 切换场景
	 */
	public void setScene(BaseScene scene) {
		this.scene = scene;
		if (stage != null) {
			stage.setScene(scene);
			stage.setTitle(scene.getTitle());
		}
	}

	/**
	 * 获取根布局
	 */
	public BorderPane getLayout() {
		if (scene == null)
			return null;
		return scene.getLayout();
	}
}
