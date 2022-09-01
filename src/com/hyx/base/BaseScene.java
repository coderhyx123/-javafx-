package com.hyx.base;

import java.util.Optional;

import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * 场景基类
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

	// 根布局 绝对不会为null，总是会在第一时间被创建
	private BorderPane layout;

	/**
	 * 获取根布局
	 */
	public BorderPane getLayout() {
		return layout;
	}

	/**
	 * 获取舞台对象 （注意：没有被展示的场景，其舞台对象为 null）
	 */
	public Stage getStage() {
		return (Stage) this.getWindow();
	}

	/**
	 * 切换场景
	 */
	public void setScene(BaseScene scene) {
		Stage stage = getStage();
		stage.setScene(scene);
		stage.setTitle(scene.getTitle());
	}

	/**
	 * 场景标题
	 */
	private String title = "";

	/**
	 * 获取标题
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * 设置标题
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * 弹出消息框
	 */
	public void alert(String msg) {
		new Alert(Alert.AlertType.INFORMATION, msg).showAndWait();
	}

	/**
	 * 弹出询问框
	 */
	public boolean confirm(String msg) {
		Optional<ButtonType> r = new Alert(Alert.AlertType.CONFIRMATION, msg).showAndWait();
		return r.get() == ButtonType.OK;
	}

	/**
	 * 弹出错误提示
	 */
	public void alertError(String msg) {
		new Alert(Alert.AlertType.ERROR, msg).showAndWait();
	}
}
