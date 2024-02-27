package storage;

import com.google.cloud.storage.*;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class Upload {

	public static void main(String[] args) throws URISyntaxException {
		//need to be run with service account having token sign capability
		//(service account token creator)
		URL url = getUploadUrl();

		System.out.println(url.toString());

		doUpload(url);
	}

	private static URL getUploadUrl() {

		Storage storage = StorageOptions.getDefaultInstance().getService();


		String fileName = UUID.randomUUID().toString();

		BlobInfo blobInfo = BlobInfo.newBuilder(BlobId.of("signed-example", fileName)).build();

		Map<String,String> extensionHeaders = new HashMap<>();
		extensionHeaders.put("Content-Type", "application/pdf");

		return storage.signUrl(
				blobInfo, 10,
				TimeUnit.MINUTES,
				Storage.SignUrlOption.httpMethod(HttpMethod.PUT),
				Storage.SignUrlOption.withExtHeaders(extensionHeaders),
				Storage.SignUrlOption.withV4Signature());

	}

	private static void doUpload(URL url) throws URISyntaxException {

		RestTemplate restTemplate = new RestTemplate();

		File file = new File("sample.pdf");
		FileSystemResource resource = new FileSystemResource(file);
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "application/pdf"); //should be the same of the created signed Url
		HttpEntity<FileSystemResource> requestEntity = new HttpEntity<FileSystemResource>(resource, headers);

		restTemplate.exchange(url.toURI(), org.springframework.http.HttpMethod.PUT, requestEntity, String.class);

	}

}
