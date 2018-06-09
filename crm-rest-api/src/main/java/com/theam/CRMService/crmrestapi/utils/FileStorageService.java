package com.theam.CRMService.crmrestapi.utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.nio.file.CopyOption;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class FileStorageService {
	Logger log = LoggerFactory.getLogger(this.getClass().getName());
	private final Path rootLocation = Paths.get("upload-dir");
 
	public URI store(MultipartFile file,String rootFolder,String subFolder) throws IOException {
		try {
			Path relative = this.rootLocation.resolve(rootFolder).resolve(subFolder);
			log.debug("RelativePath: " + relative);
			try {
				CreateDirIfNotExists(relative);
			}
			catch(IOException ioe) {
				throw ioe;
			}
			Path path = relative.resolve(file.getOriginalFilename());
			Files.copy(file.getInputStream(), path,StandardCopyOption.REPLACE_EXISTING);
			return path.toUri();
		} catch (IOException ioe) {
			throw ioe;
		}
	}
 
	public Resource loadFromPath(URI path) {
		try {
			Resource resource = new UrlResource(path);
			if(resource.exists()  || resource.isReadable() ) {
				return resource;
			}
			else {
				throw new RuntimeException("FAIL!");
			}
		}
		catch(MalformedURLException e) {
			throw new RuntimeException("FAIL!");
		}
	}

	public void deleteAll() {
		FileSystemUtils.deleteRecursively(rootLocation.toFile());
	}
 
	private void CreateDirIfNotExists(Path path) throws IOException {
		if(Files.notExists(path)) {
			try {
				Files.createDirectories(path);
			}
			catch(IOException ioe) {
				throw ioe;
			}
		}
	}
	public void init() {
		try {
			Files.createDirectory(rootLocation);
		} catch (IOException e) {
			throw new RuntimeException("Could not initialize storage!");
		}
	}
}
