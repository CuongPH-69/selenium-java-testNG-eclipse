package testNG;

import org.testng.Assert;

public class Topic_01_Assert {

	public static void main(String[] args) {
		String name = "Cuong Pham";
		
//		Mong đợi trả về 1 điều kiện đúng
		Assert.assertTrue(name.contains("Cuong"));
		
//		Mong đợi trả về 1 điều kiện sai
		Assert.assertFalse(name.contains("hung"));
		
//		Mong đợi kết quả thực tế = kết quả mong đợi
		Assert.assertEquals(name, "Cuong Pham");
	}

}
