import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class Main {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        String s = File.separator;
        String wayDirectoryGames = "C:" + s + "Users" + s + "Эльдо" + s + "IdeaProjects" + s +
                "Java Core(netology)" + s + "Gamess";
        String wayZip = wayDirectoryGames + s + "savegames" + s + "zip.zip";
        List<String> wayFile = new ArrayList<>();
        wayFile.add(wayDirectoryGames + s + "savegames" + s + "gameProgress.bin");
        wayFile.add(wayDirectoryGames + s + "savegames" + s + "gameProgress1.bin");
        wayFile.add(wayDirectoryGames + s + "savegames" + s + "gameProgress2.bin");

        StringBuilder sb = new StringBuilder();
        Installation installation = new Installation();
        //src
        sb.append(installation.installDirectory(wayDirectoryGames, "src"));
        sb.append("   ");
        sb.append(installation.installDirectory(wayDirectoryGames + s + "src", "test"));
        sb.append("   ");
        sb.append(installation.installDirectory(wayDirectoryGames + s + "src", "main"));
        sb.append(installation.installFile(wayDirectoryGames, "src" + s + "main", "Main.java"));
        sb.append(installation.installFile(wayDirectoryGames, "src" + s + "main", "Utils.java"));
        //res
        sb.append(installation.installDirectory(wayDirectoryGames, "res"));
        sb.append("   ");
        sb.append(installation.installDirectory(wayDirectoryGames + s + "src", "drawables"));
        sb.append("   ");
        sb.append(installation.installDirectory(wayDirectoryGames + s + "src", "vectors"));
        sb.append("   ");
        sb.append(installation.installDirectory(wayDirectoryGames + s + "src", "icons"));
        //savegames
        sb.append(installation.installDirectory(wayDirectoryGames, "savegames"));
        //temp
        sb.append(installation.installDirectory(wayDirectoryGames, "temp"));
        sb.append(installation.installFile(wayDirectoryGames, "temp", "temp.txt"));


        addStringBuilder(sb.toString(), wayDirectoryGames + s + "temp", "temp.txt");
        GameProgress gameProgress = new GameProgress(100, 50, 6, 20.5);
        GameProgress gameProgress1 = new GameProgress(500, 95, 30, 80.8);
        GameProgress gameProgress2 = new GameProgress(62, 25, 3, 10.3);


        saveGame(wayFile.get(0), gameProgress);
        saveGame(wayFile.get(1), gameProgress1);
        saveGame(wayFile.get(2), gameProgress2);

        zipFiles(wayZip, wayFile);
        deleteFile(wayDirectoryGames + s + "savegames");
        openZip(wayZip, wayDirectoryGames + s + "savegames");
        openProgress(wayFile);
    }

    public static void addStringBuilder(String stringBuilder, String way, String file) throws IOException {
        File temps = new File(way, file);
        if (temps.exists()) {
            FileWriter fl = new FileWriter(temps);
            fl.write(stringBuilder);
            fl.flush();
            fl.close();
        }
    }

    public static void saveGame(String wayFile, GameProgress gameProgress) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(wayFile))) {
            oos.writeObject(gameProgress);
        } catch (IOException e) {
            System.out.println("Error in File way");
        }
    }

    public static void zipFiles(String wayFileZip, List<String> wayFile) {
        try (ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(wayFileZip))) {
            for (String s : wayFile) {
                File fileZip = new File(s);
                FileInputStream fis = new FileInputStream(fileZip);
                ZipEntry zipEntry = new ZipEntry(fileZip.getName());
                zos.putNextEntry(zipEntry);
                byte[] bytes = new byte[1024];
                int lenght;
                while ((lenght = fis.read(bytes)) >= 0) {
                    zos.write(bytes, 0, lenght);
                    zos.closeEntry();
                }
                fis.close();
            }
        } catch (IOException e) {
            System.out.println("Error in ZIP way");
        }
    }

    public static void deleteFile(String s) {

        File file1 = new File(s);
        File[] file2 = file1.listFiles();
//        Arrays.stream(file2).filter(x -> !(x.getName().equals("zip.zip")) ? x.delete() : );
        for (File f : file2) {
            if (!(f.getName().equals("zip.zip"))) {
                f.delete();
            }
        }
    }

    public static void openZip(String wayZip, String wayDirectory) {
        try (ZipInputStream zis = new ZipInputStream(new FileInputStream(wayZip))) {
            byte[] buffer = new byte[1024];
            ZipEntry zipEntry = zis.getNextEntry();
            File wayDir = new File(wayDirectory);
            while (zipEntry != null) {
                File newFile = newFile(wayDir, zipEntry);
                File parent = newFile.getParentFile();
                if (!parent.isDirectory() && !parent.mkdirs()) {
                    throw new IOException("Failed to create directory " + parent);
                }
                FileOutputStream fos = new FileOutputStream(newFile);
                int len;
                while ((len = zis.read(buffer)) > 0) {
                    fos.write(buffer, 0, len);
                }

                zipEntry = zis.getNextEntry();
            }
        } catch (IOException e) {
            e.getMessage();
        }
    }

    private static File newFile(File wayDir, ZipEntry zipEntry) throws IOException {
        File destFile = new File(wayDir, zipEntry.getName());

        String destDirPath = wayDir.getCanonicalPath();
        String destFilePath = destFile.getCanonicalPath();

        if (!destFilePath.startsWith(destDirPath + File.separator)) {
            throw new IOException("Entry is outside of the target dir: " + zipEntry.getName());
        }

        return destFile;
    }

    public static void openProgress(List<String> wayFile) throws IOException, ClassNotFoundException {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(wayFile.get(0)));
        GameProgress gameProgress = (GameProgress) ois.readObject();
        System.out.println(gameProgress);
//        for (String way : wayFile) {
//            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(way));
//            GameProgress gameProgress = (GameProgress) ois.readObject();
//            System.out.println(gameProgress);
//        }
    }
}