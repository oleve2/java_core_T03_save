import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Main {
    public static void saveGame(String filePath, GameProgress gpObj) {
        try (
            FileOutputStream fos = new FileOutputStream(filePath);
            ObjectOutputStream oos = new ObjectOutputStream(fos)
        ) {
            oos.writeObject(gpObj);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static void zipFiles(String zipName, String[] filesList) {
        try {
            byte[] buffer = new byte[1024];
            FileOutputStream fos = new FileOutputStream(zipName);
            ZipOutputStream zos = new ZipOutputStream(fos);

            for (String name : filesList) {
                File srcFile = new File(name);
                FileInputStream fis = new FileInputStream(srcFile);
                zos.putNextEntry(new ZipEntry(srcFile.getName()));
                int length;
                while ((length = fis.read(buffer)) > 0) {
                    zos.write(buffer, 0, length);
                }
                zos.closeEntry();
                fis.close();
            }
            zos.close();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

    }


    public static void main(String[] args) {
        System.out.println("Task T03_save");

        GameProgress gpo1 = new GameProgress(100, 100, 1, 13.12);
        GameProgress gpo2 = new GameProgress(90, 120, 2, 23.22);
        GameProgress gpo3 = new GameProgress(80, 130, 3, 33.32);

        saveGame("./sav1.dat", gpo1);
        saveGame("./sav2.dat", gpo2);
        saveGame("./sav3.dat", gpo3);

        String[] fnames = new String[] {"./sav1.dat", "./sav2.dat", "./sav3.dat"};
        zipFiles("./zip_out2.zip", fnames);
    }
}