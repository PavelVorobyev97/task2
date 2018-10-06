package hh2;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Hh2 {
    
    class point {
        
        public int x;
        public int y;
        public Character value;
    }
    
    public static void main(String[] args) throws FileNotFoundException, IOException {
        
        FileReader fr = new FileReader("input.txt");
        FileWriter fw = new FileWriter("output.txt");
        Scanner sc = new Scanner(fr);
        
        int oCounter = 0;//счетчик o (o МАЛЕНЬКАЯ o АНГЛИЙСКАЯ НЕ НОЛЬ!!!)
        int rowCount = 0;//количество рядов (количество элементов в столюце)
        int colCount = 0;//количество столбцов (количество элементов в строке)
        String ans = new String();//строка для записи в файл
        ArrayList arr = new ArrayList();//массив строк(рядов)

        //чтение из файла
        while (sc.hasNextLine()) {
            arr.add(sc.nextLine());
        }
        fr.close();

        //получаем значение 
        rowCount = arr.size();
        colCount = arr.get(0).toString().length();

        //заполнение матрицы
        char matrix[][] = new char[rowCount][colCount];
        for (int i = 0; i < rowCount; i++) {
            for (int j = 0; j < colCount; j++) {
                matrix[i][j] = arr.get(i).toString().charAt(j);
            }
        }

        //считаем О
        for (int i = 0; i < rowCount; i++) {
            for (int j = 0; j < colCount; j++) {
                if (matrix[i][j] == 'o') {
                    oCounter++;
                }
            }
        }

        //считаем площать
        int s = rowCount * colCount;
        int sSeg = s / oCounter;

        //разложение площадни на два множителя
        List<Integer> multi = new ArrayList();
        for (int i = 1; i <= rowCount || i <= colCount; i++) {
            if ((sSeg % i == 0) && (sSeg / i <= rowCount || sSeg / i <= colCount)) {
                multi.add(i);
            }
        }

        //количество строк и столбцов
        System.out.println("rows = " + rowCount);
        System.out.println("cols = " + colCount);
        System.out.println("O count = " + oCounter);
        System.out.println("s = " + s);
        System.out.println("sSeg = " + sSeg);
        System.out.println("multipliers of s = " + multi);

        ///*
        //вывод матрицы
        for (int i = 0; i < rowCount; i++) {
            for (int j = 0; j < colCount; j++) {
                System.out.print(matrix[i][j]);
            }
            System.out.println("");
        }
        // */

        //вывод конкретного элемента
        System.out.println("element = " + matrix[1][2]);

        //попытки посмотреть в какой точке будет прямоугольниу
        int mp;
        for (int m : multi) {
            mp = sSeg / m;
            
            if (m <= rowCount && mp <= colCount) {
                for (int i = 0; i < m && i < rowCount; i++) {
                    for (int j = 0; j < mp && j < colCount; j++) {
                        System.out.print(matrix[i][j]);
                    }
                    System.out.println("");
                }
                System.out.println("next piece");
            }
            
            
        }

        //запись в файл
        fw.write(ans);
        
        fw.close();
    }
    
}
