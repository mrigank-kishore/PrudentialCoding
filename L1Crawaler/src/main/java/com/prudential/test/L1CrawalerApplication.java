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
		if(args.length > 1)	{
				System.out.println("Program is generating the page details for the website : " + 
						args[0] + " and the domain is : " + args[1]);
				System.out.println("Program is generating the output recusively. It will take some time, please wait....");
				
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
					System.err.println(e.getMessage());
					e.printStackTrace();
				}
			}else	{
				System.out.println("Please provide website link and domain detial for program to run");
				System.out.println("Example command is: java -jar L1Crawaler-0.0.1-SNAPSHOT.jar http://web.mit.edu/ mit.edu");
			
		}
		
		SpringApplication.run(L1CrawalerApplication.class, args);
	}

	private static void generateHtml(String data, Path filePath)  throws IOException
	{
		Files.write(filePath, data.getBytes(), StandardOpenOption.APPEND);
	}
}
