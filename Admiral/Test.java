package Admiral;

import java.util.Scanner;

public class Test {
    public static void main(String[] args) throws Exception
    {

        java.util.Random generator = new java.util.Random(System.currentTimeMillis());

        //gemilerin yerleştirildiği matris
        int board[][] = new int [10][10];

        //oyuncunun hamlelerinin tutulduğu matris
        int player[][] = new int [10][10];

        //gemilerin adedini tutan dizi
        //0 nolu eleman kullanılmıyor
        int ship[] = {0, 4, 3, 2, 1};

        int i, j, row, col, size;
        int num, dir, shipSize, count, r, c, rl, cl, flag;
        int right, down, hit;

        row = 10;
        col = 10;
        size = 5;

        count = 0;
        shipSize = 1;

        while (shipSize <= 4) {
            //yon üretiliyor (0 sağa, 1 aşağıya)
            dir = generator.nextInt(2);

            //yöne göre satır sütun sınırları ve
            //yerleştirilecek geminin sınırları belirleniyor
            if (dir == 0) {
                rl = row;
                cl = col - shipSize + 1;
                right = 2 + shipSize;
                down = 3;
            }
            else {
                rl = row - shipSize + 1;
                cl = col;
                right = 3;
                down = 2 + shipSize;
            }

            //geminin nereye yerleştirileceği üretiliyor
            r = generator.nextInt(rl);
            c = generator.nextInt(cl);

            //geminin yerleştirileceği yerin
            //boş olup olmadığına bakılıyor
            flag = 0;
            for (i = r-1; i < (r-1) + down; i = i + 1)
                for (j = c-1; j < (c-1) + right; j = j + 1)
                    if (i >=0 && i < row && j >= 0 && j < col)
                        if (board[i][j] != 0)
                            flag = 1;

            //flag'in 0'a eşit olması geminin yerleştirileceği yerin
            //boş olduğunu gösteriyor.
            if (flag == 0) {
                //gemi yerleştiriliyor
                for (i = r; i < r + down - 2; i = i + 1)
                    for (j = c; j < c + right - 2; j = j + 1)
                        board[i][j] = shipSize;

                //adet bir arttırılıyor
                count = count + 1;

                //gemiden istenen adet yerleştirilmişse
                //bir sonraki gemiye geçiliyor
                if (ship[shipSize] == count) {
                    shipSize = shipSize + 1;
                    count = 0;

                }
            }
        }


        //gemilerin yerleştirildiği matris yazdırılıyor
        for (i = 0; i < row; i = i + 1) {
            for (j = 0; j < col; j = j + 1)
                System.out.print(board[i][j] + " ");
            System.out.println();
        }

        System.out.println();

        //toplam 20 isabetli atış yapıldığında oyun bitiyor
        count = 0;
        hit = 0;
        while(hit < 20) {
            //atış sayısı bir arttırılıyor
            count = count + 1;

            //atış yapılıyor
            Scanner scan = new Scanner(System.in);
            System.out.print("Satiri giriniz : ");

            r = scan.nextInt();
            System.out.print("Sutunu giriniz : ");
            c = scan.nextInt();



            //atışın isabetli olup olmadığına bakılıyor
            //eğer isabetli ise oyuncunun matrisine
            //gemi numarası yazdırılıyor
            //değilse 9 sayısı yazdırılıyor
            if (board[r][c] != 0){
                hit = hit + 1;
                player[r][c] = board[r][c];
            }
            else
                player[r][c] = 9;

            //gemilerin matrisi yazdırılıyor
            for (i = 0; i < row; i = i + 1) {
                for (j = 0; j < col; j = j + 1)
                    System.out.print(board[i][j] + " ");
                System.out.println();
            }

            System.out.println();

            //oyuncunun matrisi yazdırılıyor
            for (i = 0; i < row; i = i + 1) {
                for (j = 0; j < col; j = j + 1)
                    System.out.print(player[i][j] + " ");
                System.out.println();
            }
        }
        System.out.println("Tebrikler! " + count + " adimda bildiniz.");
    }
}

