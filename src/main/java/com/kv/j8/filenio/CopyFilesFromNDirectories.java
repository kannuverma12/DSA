package com.kv.j8.filenio;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CopyFilesFromNDirectories {

	public static void main(String[] args) {
		Path start = Paths.get("/Users/karanverma/Documents/backups/songs");
		int maxDepth = 15;
		try(Stream<Path> stream = Files.find(start, 
											maxDepth, 
											(path, attr) -> String.valueOf(path).endsWith(".mp3"))){
			
			List<Path> fileName = stream
									.sorted()
									.filter(path -> String.valueOf(path).endsWith(".mp3"))
									.collect(Collectors.toList());
			
			for(Path p : fileName) {
				Path path = Paths.get("/Users/karanverma/Desktop/TestCopy/"+p.getFileName());
			    Files.copy(p, path,StandardCopyOption.REPLACE_EXISTING);
			}
		}catch(Exception e){
			e.printStackTrace();
		}

	}

}
