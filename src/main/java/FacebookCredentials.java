import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class FacebookCredentials {
    private static Properties properties;

    private static void loadCredentials(){
        try{
            properties = new Properties();
            FileInputStream input = new FileInputStream("src/config.properties");
            properties.load(input);
            input.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getUsername() {
        loadCredentials();
        return properties.getProperty("username");
    }

    public String getPassword() {
        loadCredentials();
        return properties.getProperty("password");
    }
}
