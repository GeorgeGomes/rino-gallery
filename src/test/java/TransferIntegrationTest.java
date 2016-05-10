
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;

import org.junit.Test;

import br.com.rino.util.FileUtil;

public class TransferIntegrationTest {

	@Test
	public void test1() throws IOException {
		URL url = new URL("http://127.0.0.1:8080/rino-gallery/servlet/transfer");
		URLConnection conn = url.openConnection();
		conn.setDoOutput(true);
		OutputStreamWriter writer = new OutputStreamWriter(conn.getOutputStream());

		writer.write("name=" + FileUtil.generateUniqueFileName() + "jpg");
		writer.write("&");
		writer.write("image=" + FileUtil.convertImageToBase64("C://icon-red.png"));
		writer.flush();
		String line;
		BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		while ((line = reader.readLine()) != null) {
			System.out.println(line);
		}
		writer.close();
		reader.close();

	}

}
