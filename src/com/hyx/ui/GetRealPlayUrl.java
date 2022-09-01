package com.hyx.ui;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hyx.entity.Music;

import javafx.scene.control.TextField;

public class GetRealPlayUrl {

	static TextField searchsong;
	static int count = 1;

	public GetRealPlayUrl(TextField s, int page) {
		searchsong = s;
		count = page;// 把页数传进来
	}

	// 搜索歌曲
	public List<Music> searchMusic() {
		String searchsongAndsinger = searchsong.getText();
		String urlencoder = null;// 搜索的关键字
		try {
			urlencoder = URLEncoder.encode(searchsongAndsinger, "utf-8");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		String web = "http://mobilecdn.kugou.com/api/v3/search/song?format=json&keyword=" + urlencoder + "&page="
				+ count + "&pagesize=20&showtype=1";
		List<Music> listss = getsubMusic(web);
//		System.out.println(list);
		return listss;

	}

	/**
	 * 获取哈希值和id方便进行歌曲详情地址拼接
	 */
	public List<Music> getsubMusic(String web) {
		List<Music> list = new ArrayList<>();
		try {
			URL url = new URL(web);
			URLConnection con = url.openConnection();
			con.addRequestProperty("User-Agent",
					"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/103.0.0.0 Safari/537.36");
			InputStream is = con.getInputStream();
			InputStreamReader isr = new InputStreamReader(is, "utf8");
			BufferedReader br = new BufferedReader(isr);
			StringBuilder sb = new StringBuilder();
			String line;
			while ((line = br.readLine()) != null) {
				sb.append(line);
			}

			JSONObject jsonobiect = JSONObject.parseObject(sb.toString());
			JSONObject jb = jsonobiect.getJSONObject("data");
			JSONArray jarray = jb.getJSONArray("info");

			for (int i = 0; i < jarray.size(); i++) {
				JSONObject music = jarray.getJSONObject(i);
				String hash = music.getString("hash");
				String album_id = music.getString("album_id");

				// 获取歌曲详情必要参数hash和album_id
				String realMusicUrl = "https://wwwapi.kugou.com/yy/index.php?r=play/getdata&hash=" + hash
						+ "&mid=a21739e52db7d8d623c6f6f83b103d59&album_id=" + album_id;
				try {
					URL realurl = new URL(realMusicUrl);
					URLConnection openconn = realurl.openConnection();
					openconn.addRequestProperty("User-Agent",
							"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/103.0.0.0 Safari/537.36");
					InputStream is2 = openconn.getInputStream();
					InputStreamReader isr2 = new InputStreamReader(is2, "utf8");
					BufferedReader br2 = new BufferedReader(isr2);
					StringBuilder sbreal = new StringBuilder();
					String line2;
					while ((line2 = br2.readLine()) != null) {
						sbreal.append(line2);
					}
					JSONObject jb2 = JSONObject.parseObject(sbreal.toString());
					JSONObject data = jb2.getJSONObject("data");
//					List<Music> list = new ArrayList<>();//一个可行但消耗非常大的想法
					Music m = new Music();
					String audio_name = data.getString("audio_name");// 文件名
					String author_name = data.getString("author_name");// 歌手名
					String play_url = data.getString("play_url"); // 播放地址
					String tl = data.getString("timelength");// 歌曲时长
					String songname = data.getString("song_name");// 歌名
					String imageurl = data.getString("img");// 图片地址
					String lrc = data.getString("lyrics");// 歌词

					String timelength = null;// 歌曲时长有异常，try一下
					try {
						Long sj = Long.parseLong(tl);
						SimpleDateFormat sdf = new SimpleDateFormat("mm:ss");
						timelength = sdf.format(new Date(sj));
					} catch (Exception e) {

					}

					m.setAudio_name(audio_name);
					m.setAuthor_name(author_name);
					m.setPlay_url(play_url);
					m.setTimelength(timelength);
					m.setSongname(songname);
					m.setImageurl(imageurl);
					m.setLrc(lrc);
					list.add(m);

				} catch (Exception e) {
					e.printStackTrace();
				}

				/**
				 * 
				 * @param hash     歌曲哈希值
				 * @param album_id 歌曲标识id
				 */
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

}
