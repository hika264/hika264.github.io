package com.example.demo;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class ColorName {
	public static final String url = "https://www.color-site.com/color_names";

	public static Map<String, String> scrapeColors() throws IOException {
		Map<String, String> colorMap = new HashMap<>();
		Document doc = Jsoup.connect(url).get();  // 指定したURLに接続してHTMLを取得
		Elements rows = doc.select("tr");  // HTMLのTable内の行を取得

		for (Element row : rows) {
			Elements columns = row.select("td");  // 各行の列を取得
			if (columns.size() >= 4) {  // 列が4つ以上あるか確認
				String colorCode = columns.select(".ctd2").text(); // カラーコードを取得
				String colorName = columns.select(".ctd4").text(); // カラー名を取得
				colorMap.put(colorCode, colorName);  // 色名とカラーコードのペアをマップに追加
			}
		}
		return colorMap;
	}
}
