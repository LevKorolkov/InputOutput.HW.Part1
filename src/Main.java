import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Main {
    public static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
    public static StringBuilder logInTemp = new StringBuilder();
    static String path = "C://Games";
    static String[] namesOfDirsInGames = {"/src", "/res", "/savegames", "/temp"};
    static String[] namesOfDirsInSrc = {"/main", "/test"};
    static String[] namesOfDirsInRes = {"/drawables", "/vectors", "/icons"};
    static String[] namesOfFilesInMain = {"/Main.java", "/Utils.java"};
    static String nameOfFileInTemp = "/temp.txt";
    static List<File> files = new ArrayList<>();
    // Список с путями до папок src, main, savegames и temp
    static List<File> directoriesOfGames = new ArrayList<>();

    public static void main(String[] args) {
        // Создаём директории и добавляем их адреса в ArrayList
        for (int i = 0; i < namesOfDirsInGames.length; i++) {
            File directory = new File(path + namesOfDirsInGames[i]);
            directory.mkdir();
            if (directory.exists()) {
                logInTemp.append(LocalDateTime.now().format(formatter) + "    :    "
                        + '"' + directory.getName() + '"' + " directory is created successfully" + "\n");
            }
            directoriesOfGames.add(directory);
        }

        // Создаём директории, итерируясь по ArrayList директорий в папке Games
        for (int i = 0; i < directoriesOfGames.size(); i++) {
            if (i == 0) {
                toCreateDirs(namesOfDirsInSrc, namesOfDirsInSrc, directoriesOfGames, i);
            } else if (i == 1) {
                toCreateDirs(namesOfDirsInSrc, namesOfDirsInRes, directoriesOfGames, i);
            } else {
                break;
            }
        }

        // Добавляем файлы с разрешением .jar. Создаём переменные типа String с конечным путём до папки main
        String pathToFolderMain = directoriesOfGames.get(0).toString() + namesOfDirsInSrc[0];
        files.add(new File(pathToFolderMain + namesOfFilesInMain[0]));
        files.add(new File(pathToFolderMain + namesOfFilesInMain[1]));
        for (File file : files) {
            try {
                file.createNewFile();
                if (file.exists()) {
                    logInTemp.append(LocalDateTime.now().format(formatter) + "    :    "
                            + '"' + file.getName() + '"' + " file is created successfully" + "\n");
                }
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }

        String pathToFolderTemp = directoriesOfGames.get(3).toString();
        toCreateTemp(pathToFolderTemp);
    }

    // Метод, создающий temp файл с логом
    public static void toCreateTemp(String path) {
        File fileTemp = new File(path + nameOfFileInTemp);
        try {
            fileTemp.createNewFile();
            if (fileTemp.exists()) {
                logInTemp.append(LocalDateTime.now().format(formatter) + "    :    " + '"' + fileTemp.getName()
                        + '"' + " file with log is created successfully" + "\n");
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        try (FileWriter str = new FileWriter(fileTemp)) {
            str.write(logInTemp.toString());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    // Метод, отвечающий за создание директорий
    public static void toCreateDirs(String[] namesOfDirsInSrc, String[] namesOfDirsInRes, List<File> directoriesOfGames, int i) {
        for (int g = 0; g < namesOfDirsInRes.length; g++) {
            if (g == 0) {
                File directory1 = new File(directoriesOfGames.get(i) + namesOfDirsInRes[g]);
                directory1.mkdir();
                if (directory1.exists()) {
                    logInTemp.append(LocalDateTime.now().format(formatter) + "    :    " + '"' + directory1.getName()
                            + '"' + " directory is created successfully" + "\n");
                }
            }
            if (g == 1) {
                File directory2 = new File(directoriesOfGames.get(i) + namesOfDirsInSrc[g]);
                directory2.mkdir();
                if (directory2.exists()) {
                    logInTemp.append(LocalDateTime.now().format(formatter) + "    :    " + '"' + directory2.getName()
                            + '"' + " directory is created successfully" + "\n");
                }
            }
        }
    }


}


