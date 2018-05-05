//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package Final;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ReadFile {
    private String path;

    public ReadFile(String file_path) {
        this.path = file_path;
    }

    public String[] OpenFile() throws IOException {
        FileReader fr = new FileReader(this.path);
        BufferedReader textReader = new BufferedReader(fr);
        int numberOfLines = this.readLines();
        String[] textData = new String[numberOfLines];
        String emp = "";

        for(int i = 0; i < numberOfLines; ++i) {
            String newLine = textReader.readLine();
            if (!newLine.isEmpty() && newLine != null){
                textData[i] = newLine;
            }
        }

        textReader.close();
        return textData;
    }

    int readLines() throws IOException {
        FileReader file_to_read = new FileReader(this.path);
        BufferedReader bf = new BufferedReader(file_to_read);

        int numberOfLines;
        for(numberOfLines = 0; bf.readLine() != null; ++numberOfLines) {
            ;
        }

        bf.close();
        return numberOfLines;
    }

    public static void main(String[] args) throws IOException {
        String file_name = "/Users/linshiyu/Desktop/gettysburg.txt";
        ReadFile file = new ReadFile(file_name);
        String[] gettysburg = file.OpenFile();
        System.out.println(gettysburg[0]);
        System.out.println(gettysburg[0].getClass().getName());
        System.out.println(gettysburg[1]);
        System.out.println(gettysburg[1].getClass().getName());
        System.out.println(gettysburg[2]);
        System.out.println(gettysburg[2].getClass().getName());
        System.out.println(gettysburg[3]);
        System.out.println(gettysburg[3].getClass().getName());

    }
}
