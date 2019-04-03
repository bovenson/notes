package lnn;

import java.util.Scanner;

public class Main{
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        while(in.hasNext()){
            int n = in.nextInt();
            int[] array = new int[n];
            for(int i = 0; i < n ; i++){
                array[i] = in.nextInt();
            }
            int[] temp = new int[n];
            temp[0] = array[0];
            for(int i = 1; i < n; i++){
                if((array[i] ^ temp[i-1]) == 0 || array[i] == 0) temp[i] = 0;
                else temp[i] = array[i] ^ temp[i-1];
            }
            int num0 = 0;
            for(int i = 0; i < n; i++){
                if(temp[i] == 0) num0++;
            }
            System.out.println(num0);
        }
    }
}
