package serialization;

// Java imports
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.util.Scanner;

// Java map imports
import java.util.HashMap;
import java.util.Map;


public class Serialization {

    private File file;
    private FileWriter fileWriter;
    private Map<String, String> map;

    public Serialization() {
        file = new File("/Users/Karol/Desktop/SpotifyAppConfig.txt");
        fileWriter = null;
        map = new HashMap<String, String>();
    }

    public void saveToFile() {
        try {
            fileWriter = new FileWriter(file);

            for (Map.Entry<String, String> entry: map.entrySet())
                fileWriter.write(entry.getKey() + " = " + entry.getValue() + "\n");

            fileWriter.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void readFromFile() {
        try {
            Scanner scanner = new Scanner(file);
            
            while (scanner.hasNextLine()) {
                String[] data = scanner.nextLine().split(" = ");
                map.put(data[0], data[1]);
            }

            scanner.close();
        } catch (IOException e) { System.out.println(e.getMessage()); }
    }

    // Adds element to map variable that will be saved to a SpotifyAppConfig.txt file
    public void addElement(String key, String value) throws IOException {
        if (key.indexOf(" ") == -1 && value.indexOf(" ") == -1) {
            map.put(key, value);
        } else { throw new IOException(); }
    }

    public boolean checkIfFileIsEmpty() {
        return file.length() == 0;
    }

    public String getAccessToken() {
        return map.get("accessToken");
    }

    public String getRefreshToken() {
        return map.get("refreshToken");
    }

}
