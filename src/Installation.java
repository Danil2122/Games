import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Installation {
    public static void install() {
        String s = File.separator;
        StringBuilder sb = new StringBuilder();
        //src
        File src = new File("C:" + s + "Users" + s + "Эльдо" + s + "IdeaProjects" + s + "Java Core(netology)" +
                s + "Gamess" + s + "src");
        if (src.mkdir()) {
            sb.append("Директория src была записана.");
            File test = new File("C:" + s + "Users" + s + "Эльдо" + s + "IdeaProjects" + s +
                    "Java Core(netology)" + s + "Gamess" + s + "src" + s + "test");
            if (test.mkdir()) {
                sb.append("Директория test была записана.");
            }
            File main = new File("C:" + s + "Users" + s + "Эльдо" + s + "IdeaProjects" + s +
                    "Java Core(netology)" + s + "Gamess" + s + "src" + s + "main");
            try {
                if (main.mkdir()) {
                    sb.append("Директория main была записана.");
                    File mainFile = new File(main, "Main.java");
                    if (mainFile.createNewFile()) {
                        sb.append("Файл Main.java был записан");
                    }
                    File utilFile = new File(main, "Utils.java");
                    if (utilFile.createNewFile()) {
                        sb.append("Файл Utils.java был записан");
                    }
                }
            } catch (IOException e) {
                System.out.println("No correct way to the file");
            }
        }
        //res
        File res = new File("C:" + s + "Users" + s + "Эльдо" + s + "IdeaProjects" + s + "Java Core(netology)" +
                s + "Gamess" + s + "res");
        if (res.mkdir()) {
            sb.append("Директория res была записана.");
            File drawables = new File("C:" + s + "Users" + s + "Эльдо" + s + "IdeaProjects" + s +
                    "Java Core(netology)" + s + "Gamess" + s + "res" + s + "drawables");
            if (drawables.mkdir()) {
                sb.append("Директория drawables была записана.");
            }
            File vectors = new File("C:" + s + "Users" + s + "Эльдо" + s + "IdeaProjects" + s +
                    "Java Core(netology)" + s + "Gamess" + s + "res" + s + "vectors");
            if (vectors.mkdir()) {
                sb.append("Директория vectors была записана.");
            }
            File icons = new File("C:" + s + "Users" + s + "Эльдо" + s + "IdeaProjects" + s +
                    "Java Core(netology)" + s + "Gamess" + s + "res" + s + "icons");
            if (icons.mkdir()) {
                sb.append("Директория icons была записана.");
            }
        }
        //savegames
        File savegames = new File("C:" + s + "Users" + s + "Эльдо" + s + "IdeaProjects" + s +
                "Java Core(netology)" + s + "Gamess" + s + "savegames");
        if (savegames.mkdir()) {
            sb.append("Директория savegames была записана.");
        }
        //temp
        File temp = new File("C:" + s + "Users" + s + "Эльдо" + s + "IdeaProjects" + s + "Java Core(netology)"
                + s + "Gamess" + s + "temp");
        if (temp.mkdir()) {
            sb.append("Директория temp была записана.");
            try {
                File temps = new File(temp, "temp.txt");
                if (temps.createNewFile()) {
                    sb.append("Файл temp.txt был записан.");
                }
                FileWriter fl = new FileWriter(temps);
                String strin = sb.toString();
                fl.write(strin);
                fl.flush();
                fl.close();
            } catch (IOException e) {
                System.out.println("Error");
            }
        }
    }
}
