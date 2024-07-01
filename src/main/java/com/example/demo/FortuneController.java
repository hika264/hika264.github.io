package com.example.demo;

import java.io.IOException;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class FortuneController {

	private static Map<String, String> colorMap;

	static {
		try {
			colorMap = ColorName.scrapeColors();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	//	初期表示：フォーム（名前入力用）
	@GetMapping("/fortune")
	public String showForm() {
		return "form";
	}

	//	運勢を表示する(名前が未記入の場合はゲストと表示)
	@PostMapping("/fortune")
	public String getFortune(@RequestParam(value = "name", defaultValue = "ゲスト") String name, Model model) {
		String fortune = generateFortune();
		Entry<String, String> luckyColor = getLuckyColor();

		model.addAttribute("name", name);
		model.addAttribute("fortune", fortune);
		model.addAttribute("colorCode", luckyColor.getKey());
		model.addAttribute("colorName", luckyColor.getValue());
		return "fortune";
	}

	//	メソッド
	//	運勢
	private String generateFortune() {
		double fn = Math.random(); // 0.0～1.0の乱数
		String fortune;

		if (fn >= 0.7) {
			fortune = "おめでとう、大吉です!";
		} else if (fn >= 0.4) {
			fortune = "中吉です";
		} else if (fn >= 0.1) {
			fortune = "小吉です";
		} else {
			fortune = "残念、凶";
		}
		return fortune;
	}


	// ラッキーカラー
	private Entry<String, String> getLuckyColor() {
		Random random = new Random();
		Object[] entries = colorMap.entrySet().toArray();
		return (Entry<String, String>) entries[random.nextInt(entries.length)];
	}
}
