package com.prudential.test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.HashSet;
import java.util.Set;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.prudential.test.crawl.Spider;
import com.prudential.test.model.PageContainer;

@SpringBootApplication
public class L1CrawalerApplication {

	public static void main(String[] args) {
		final Path filePath = Paths.get("./PageDetail.html");
		System.out.println(args[0] + "==" + args[1]);
		final int MAX_DEPTH_TO_SEARCH = 2;
		final Set<String> rootUrl = new HashSet<>();rootUrl.add(args[0].toString());
		//Set<String> pagesVisited = new HashSet<String>();//for global uniquness

		try {
			if (Files.exists(filePath)) {
				Files.delete(filePath);
				Files.createFile(filePath);
			}else {
				Files.createFile(filePath);
			}

			PageContainer pc = new PageContainer();
			pc.setUniqueInternalUrl(rootUrl);

			for(int pageDepth = 0; pageDepth<MAX_DEPTH_TO_SEARCH; pageDepth++)	{
				for(String link : pc.getUniqueInternalUrl()) {
					pc = Spider.getPage(link, pageDepth, args[1].toString());
					generateHtml(pc.toString(), filePath);
				}

			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		SpringApplication.run(L1CrawalerApplication.class, args);
	}

	private static void generateHtml(String data, Path filePath)  throws IOException
	{
		Files.write(filePath, data.getBytes(), StandardOpenOption.APPEND);
	}
}
