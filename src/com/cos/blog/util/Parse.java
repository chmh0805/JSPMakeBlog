package com.cos.blog.util;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class Parse {
	public static String makeYoutube(String content) {
		Document doc = Jsoup.parse(content);
		Elements p_tag = doc.select("p");
		
		String iFrame = "";
		// https://www.youtube.com/watch?v=3mWmZ2AnmIY
		for (int i = 0; i < p_tag.size(); i++) {
			if (p_tag.get(i).text().contains("youtube.com")) {
				String href = p_tag.get(i).text();
				String sp[] = href.split("=");
				String value = sp[1]; // 3mWmZ2AnmIY
				iFrame = "<iframe width=\"560\" height=\"315\" src=\"https://www.youtube.com/embed/"
				+value
				+"\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media;"
				+ " gyroscope; picture-in-picture\" allowfullscreen></iframe>";
				p_tag.get(i).after(iFrame); // a 태그를 iFrame으로 변경
			}
		}
		return doc.toString();
		
	}
}
