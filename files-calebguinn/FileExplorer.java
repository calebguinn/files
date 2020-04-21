import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Date;

public class FileExplorer {
    public static void informAboutFile(String fileName) {
        File file = new File("last.dat");
        System.out.println(file.exists());
        System.out.println(file.length() + " bytes");
        System.out.println(file.canRead() );
        System.out.println(file.canWrite() );
        System.out.println(file.isDirectory() );
        System.out.println(file.isFile() );
        System.out.println(file.isHidden() );
        System.out.println(file.getAbsolutePath() );
        long epochTime = file.lastModified() ;

        // Modify a modification date
        Date date = new Date(epochTime);
        System.out.println(date);
        long assignmentDateValue = epochTime - 3*24 * 60 * 60 * 1000;
        
        Date assignmentDate = new Date(assignmentDateValue);
        System.out.println(assignmentDate);

        file.setLastModified(assignmentDateValue);

        epochTime = file.lastModified() ;

        date = new Date(epochTime);
        System.out.println(date);
    }
    public static void main(String[] args) throws IOException, ClassNotFoundException {

            // writing to a file
        try (DataOutputStream dos = new DataOutputStream(new FileOutputStream("last.dat"));) {
            dos.writeUTF("My best file ever");
            dos.writeDouble(23.34);
            dos.writeBoolean(true);
            dos.writeInt(3);
            dos.writeLong(Integer.MAX_VALUE);
        }
            // reading from a file

        try (DataInputStream ios = new DataInputStream(new FileInputStream("last.dat"));) {
            // It is super important to:
            // * write a unit test to check if what we read is what we write
            String str = ios.readUTF();
            double d = ios.readDouble();
            boolean b = ios.readBoolean();
            int i = ios.readInt();
            long l = ios.readLong();

            System.out.println(str);
            System.out.println(d);
            System.out.println(b);
            System.out.println(i);
            System.out.println(l);
        }

        try (DataOutputStream dos = new DataOutputStream(
                                    new BufferedOutputStream (
                                    new FileOutputStream("next.dat")));) {
            for (int i = 0; i < 100; ++i) {
                dos.writeUTF("My best file ever");
                dos.writeDouble(23.34 + i);
                dos.writeBoolean(true);
                dos.writeInt(3 * i);
                dos.writeLong(Integer.MAX_VALUE - i);
            }
        }

    }
}