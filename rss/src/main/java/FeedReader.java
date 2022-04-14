import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.FeedException;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;

public class FeedReader {

	 public static void main(String[] args) {
		 
//		 try {
//			prova2();
//		} catch (KeyManagementException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (NoSuchAlgorithmException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IllegalArgumentException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (FeedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		List<String> urlList = new ArrayList<String>();
		urlList.add("https://feeds.feedburner.com/fiarebancaetica/EkZs?fmt=xml");

		for(String url : urlList) {
			try {
				prova3(url);
			} catch (KeyManagementException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (FeedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	
//		 try {
//			prova();
//		} catch (ClientProtocolException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IllegalArgumentException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (FeedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		 
	 }
		
	
	 private static void prova() throws ClientProtocolException, IOException, IllegalArgumentException, FeedException {
//		 String url = "https://www.nonconimieisoldi.org/feed/"; 											//NO
//		 String url = "https://www.eticasgr.it/feed/"; 														//OK
		 String url = "https://sociinrete.bancaetica.it/home/feed/";										//NO
//		 String url = "http://www.fcre.it/notizie?format=feed&type=rss";									//OK con user agent browser
//		 String url = "http://feeds.feedburner.com/fiarebancaetica/EkZs?fmt=xml";							//OK
//		 String url = "http://feeds.feedburner.com/bancaetica/blog?fmt=xml";								//OK
		 
		 
		 
		 try (CloseableHttpClient client = HttpClients.createMinimal()) {
		   HttpUriRequest request = new HttpGet(url);
		   request.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.95 Safari/537.11");
		   
		   try (CloseableHttpResponse response = client.execute(request);
		        InputStream stream = response.getEntity().getContent()) {
			   			   
		     SyndFeedInput input = new SyndFeedInput();
		     XmlReader reader = new XmlReader(stream);
		     SyndFeed feed = input.build(reader);
		     System.out.println(feed.getTitle());
		     System.out.println(feed.getDescription());
		     System.out.println(feed.getEntries().get(0).getTitle());
		   }
		   
		 }
	 }
	 
	 //VERSIONE CHE DISATTIVA SSL PER TUTTE LE CHIAMATE
	 private static void prova2() throws NoSuchAlgorithmException, KeyManagementException, IOException, IllegalArgumentException, FeedException {       
	      
	     URLConnection connection = new URL("https://www.nonconimieisoldi.org/feed/").openConnection();
	     connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.95 Safari/537.11");        
 
	     SSLContext sc = SSLContext.getInstance("SSL");
	     sc.init(null, ALL_TRUSTING_TRUST_MANAGER, new java.security.SecureRandom());
	        
	     HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
	      
	     HttpsURLConnection.setDefaultHostnameVerifier(ALL_TRUSTING_HOSTNAME_VERIFIER);
	 	        
	   	        
	     try(InputStream stream = connection.getInputStream()) {
	    	   
	    	   SyndFeedInput input = new SyndFeedInput();
			     XmlReader reader = new XmlReader(stream);
			     SyndFeed feed = input.build(reader);
			     System.out.println(feed.getTitle());
			     System.out.println(feed.getDescription());
			     System.out.println(feed.getEntries().get(0).getTitle());
	      }
	        
	 }
	 
	 //VERSIONE CHE DISATTIVA SSL SOLO PER UNA SINGOLA CHIAMATA
	 private static void prova3(String urlString) throws KeyManagementException, NoSuchAlgorithmException, IOException, IllegalArgumentException, FeedException {
			 
		 URL url = new URL(urlString);
		 HttpsURLConnection httpConnection = (HttpsURLConnection) url.openConnection();
		 httpConnection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.95 Safari/537.11");
		 
		 //Create socket Factory and install all trust manager
		 SSLContext sc = SSLContext.getInstance("SSL");
  		 sc.init(null, ALL_TRUSTING_TRUST_MANAGER, new java.security.SecureRandom());
  		 
  		 httpConnection.setSSLSocketFactory(sc.getSocketFactory());

  		 //install trust host name verifier
  		 httpConnection.setHostnameVerifier(ALL_TRUSTING_HOSTNAME_VERIFIER);
  		 		 
		 try(InputStream stream = httpConnection.getInputStream()) {
	    	   
	    	   SyndFeedInput input = new SyndFeedInput();
			     XmlReader reader = new XmlReader(stream);
			     SyndFeed feed = input.build(reader);
			     System.out.println(feed.getTitle());
			     System.out.println(feed.getDescription());
			     
			     for(SyndEntry entry : feed.getEntries()) {
			    	 System.out.println(entry.getTitle());
			    	 System.out.println(entry.getModules().get(0));
			     }
			     
			    
	    }

	 }
	 

	 private static final TrustManager[] ALL_TRUSTING_TRUST_MANAGER = new TrustManager[] {
		new X509TrustManager() {
            public X509Certificate[] getAcceptedIssuers() {
                return null;
            }
            public void checkClientTrusted(X509Certificate[] certs, String authType) {}
            public void checkServerTrusted(X509Certificate[] certs, String authType) {}
		}
	 };

	 private static final HostnameVerifier ALL_TRUSTING_HOSTNAME_VERIFIER  = new HostnameVerifier() {
		 public boolean verify(String hostname, SSLSession session) {
			 return true;
		 }
	 };
}
