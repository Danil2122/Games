import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Installation {
    //wayGeneraleGirectory --- to Gamess
    //wayToFinaleDirectory ---- src/main/
    //File ---- Main.java
    public String installFile(String wayGeneralDirectory, String wayToFinaleDirectory, String Files) throws
            IOException {
        String s = File.separator;
        StringBuilder sb = new StringBuilder();
        File newFile = new File(wayGeneralDirectory + s + wayToFinaleDirectory, Files);
        if (newFile.createNewFile()) {
            sb.append("      " + "Файл " + Files + " был записан.\n");
        }
        return sb.toString();

    }

    public String installDirectory(String wayGeneralDirectory, String wayFinaleDirectory) {
        String s = File.separator;
        StringBuilder sb = new StringBuilder();
        //src
        File directory = new File(wayGeneralDirectory + s + wayFinaleDirectory);
        if (directory.mkdir()) {
            sb.append("Директория " + wayFinaleDirectory + " была записана.\n");
        }
        return sb.toString();

    }
}
