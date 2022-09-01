package com.hyx.base;

import java.util.Optional;

import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * ��������
 */
public abstract class BaseScene extends Scene {
	public BaseScene(double width, double height) {
		super(new BorderPane(), width, height);
		try {
			layout = (BorderPane) this.getRoot();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// ������ ���Բ���Ϊnull�����ǻ��ڵ�һʱ�䱻����
	private BorderPane layout;

	/**
	 * ��ȡ������
	 */
	public BorderPane getLayout() {
		return layout;
	}

	/**
	 * ��ȡ��̨���� ��ע�⣺û�б�չʾ�ĳ���������̨����Ϊ null��
	 */
	public Stage getStage() {
		return (Stage) this.getWindow();
	}

	/**
	 * �л�����
	 */
	public void setScene(BaseScene scene) {
		Stage stage = getStage();
		stage.setScene(scene);
		stage.setTitle(scene.getTitle());
	}

	/**
	 * ��������
	 */
	private String title = "";

	/**
	 * ��ȡ����
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * ���ñ���
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * ������Ϣ��
	 */
	public void alert(String msg) {
		new Alert(Alert.AlertType.INFORMATION, msg).showAndWait();
	}

	/**
	 * ����ѯ�ʿ�
	 */
	public boolean confirm(String msg) {
		Optional<ButtonType> r = new Alert(Alert.AlertType.CONFIRMATION, msg).showAndWait();
		return r.get() == ButtonType.OK;
	}

	/**
	 * ����������ʾ
	 */
	public void alertError(String msg) {
		new Alert(Alert.AlertType.ERROR, msg).showAndWait();
	}
}
