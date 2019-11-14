package smokies_client;

import org.testng.TestNG;

public class RunTest {

	public static void main(String[] args) {
		TestNG test = new TestNG();
        test.setTestClasses(new Class[] { TestBlog.class });
        test.run();

	}

}
