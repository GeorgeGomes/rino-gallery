
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
public class TransferIntegrationTest {

//	private UploadedFile file;
//	 
//    public UploadedFile getFile() {
//        return file;
//    }
// 
//    public void setFile(UploadedFile file) {
//        this.file = file;
//    }
    
    @Test
    public void test() throws IOException{
    	
    	//byte[] b = file.getContents();
    	
    	FileInputStream fis = new FileInputStream("C:/Users/georgeg/Desktop/teste.csv");
    	
    	//InputStream i = new ByteArrayInputStream(b);
    	InputStreamReader r = new InputStreamReader(fis);
    	BufferedReader bf = new BufferedReader(r);
    	
    	String line = null;
    	
    	while((line = bf.readLine()) != null) {
    	    System.out.println(line);
    	    String[] e = line.split(",");
    	    
    	}
    }
    
	
//	@Test
//	public void test1() throws IOException {
//		URL url = new URL("http://127.0.0.1:8080/rino-gallery/servlet/transfer");
//		URLConnection conn = url.openConnection();
//		conn.setDoOutput(true);
//		OutputStreamWriter writer = new OutputStreamWriter(conn.getOutputStream());
//
//		writer.write("name=" + FileUtil.generateUniqueFileName() + "jpg");
//		writer.write("&");
//		writer.write("image=" + FileUtil.convertImageToBase64("C://icon-red.png"));
//		writer.flush();
//		String line;
//		BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
//		while ((line = reader.readLine()) != null) {
//			System.out.println(line);
//		}
//		writer.close();
//		reader.close();
//		
//		
//	
//		
//
//	}

}
