package com.prudential.test.crawl;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.prudential.test.model.PageContainer;

public class Spider {
	public static Set<String> set = new HashSet<String>();

	public static void main(String[] args)	{

		getPage("http://web.mit.edu/", 0, "mit.edu");
	}

	public static PageContainer getPage(String URL, Integer level, String domain)	{

		PageContainer container = new PageContainer();
		container.setUrlkey(URL);
		container.setLevel(level);
		Map<String, String> mediaMap = new HashMap<>();
		Set<String> externallinkSet = new HashSet<>();
		Set<String> internalLinkSet = new HashSet<>();
		Document doc = null;
		try {
			doc = Jsoup.connect(URL).userAgent("Mozilla/5.0 (Windows; U; WindowsNT 5.1; en-US; rv1.8.1.6) Gecko/20070725 Firefox/2.0.0.6")
					.referrer("http://www.google.com").get();
		} catch (IOException e) {
			e.printStackTrace();
		}

		if(doc != null)	{
			Elements links = doc.select("a[href]");
			Elements media = doc.select("[src]");

			//print("\nMedia: (%d)", media.size());
			for (Element src : media) {
				mediaMap.put(src.attr("abs:src"), src.tagName());
				print(" * %s: <%s>", src.tagName(), src.attr("abs:src"));
			}
			container.setMediaContent(mediaMap);
			print("\nLinks: (%d)", links.size());
			for (Element link : links) {
				String linkStr = link.attr("abs:href");
				if(linkStr !=null && !linkStr.trim().equals("")) {
					if(linkStr.contains(domain))	{
						internalLinkSet.add(linkStr);
						print(" Internal * a: <%s>  (%s)", link.attr("abs:href"), trim(link.text(), 35));
					}else {
						externallinkSet.add(linkStr);
						print(" External * a: <%s>  (%s)", link.attr("abs:href"), trim(link.text(), 35));
					}
				}
			}
			container.setUniqueExternalUrl(externallinkSet);
			container.setUniqueInternalUrl(internalLinkSet);
		}

		return container;
	}

	private static void print(String msg, Object... args) {
		//System.out.println(String.format(msg, args));
	}

	private static String trim(String s, int width) {
		if (s.length() > width)
			return s.substring(0, width-1) + ".";
		else
			return s;
	}
}
