package com.hyx.base;

import java.util.Optional;

import javafx.application.Application;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * �������
 */
public abstract class BaseApp extends Application {
	// ��̨
	private Stage stage;
	// ��ǰ����
	private BaseScene scene;

	// �������������Զ���������
	public BaseApp() {
	}

	// ���볡����ʹ�ô��ĳ���
	public BaseApp(BaseScene scene) {
		this.scene = scene;
	}

	/**
	 * ��ʼ
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
	 * ׼������
	 */
	public abstract void ready() throws Exception;

	/**
	 * ���µĴ���
	 */
	public void launch() {
		launch((Stage) null);
	}

	/**
	 * ���µĴ���
	 */
	public void launch(BaseApp owner) {
		launch(owner.stage);
	}

	/**
	 * ���µĴ���
	 */
	public void launch(BaseScene owner) {
		launch(owner.getStage());
	}

	/**
	 * ���µĴ���
	 */
	public void launch(Stage owner) {
		if (_launched) {
			new RuntimeException("һ��BaseAppֻ����launchһ�Σ����ⷢ���ݹ���ѭ��������").printStackTrace();
			return;
		}
		try {
			Stage st = new Stage();
			if (owner != null) { // ģʽ����
				st.initModality(Modality.APPLICATION_MODAL);
				st.initOwner(owner);
			}
			// ����
			this.start(st);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// ��ֹ�ݹ���ѭ����ֻ���� launch һ��
	private boolean _launched = false;

	/**
	 * ��ʾ��̨�������Եȴ�
	 */
	private void showAndTryWait() {
		try {
			stage.showAndWait();
		} catch (Exception ex) {
			stage.show();
		}
	}

	/**
	 * ���ô���ߴ�
	 */
	public void setSize(double width, double height) {
		stage.setWidth(width);
		stage.setHeight(height);
	}

	/**
	 * ������Ϣ��
	 */
	public void alert(String msg) {
		new Alert(Alert.AlertType.INFORMATION, msg).showAndWait();
	}

	/**
	 * ����������ʾ
	 */
	public void alertError(String msg) {
		new Alert(Alert.AlertType.ERROR, msg).showAndWait();
	}

	/**
	 * ����ѯ�ʿ�
	 */
	public boolean confirm(String msg) {
		Optional<ButtonType> r = new Alert(Alert.AlertType.CONFIRMATION, msg).showAndWait();
		return r.get() == ButtonType.OK;
	}

	/**
	 * ��ȡ��̨����
	 */
	public Stage getStage() {
		return stage;
	}

	/**
	 * ��ȡ��ǰ����
	 */
	public BaseScene getScene() {
		return scene;
	}

	/**
	 * �л�����
	 */
	public void setScene(BaseScene scene) {
		this.scene = scene;
		if (stage != null) {
			stage.setScene(scene);
			stage.setTitle(scene.getTitle());
		}
	}

	/**
	 * ��ȡ������
	 */
	public BorderPane getLayout() {
		if (scene == null)
			return null;
		return scene.getLayout();
	}
}
