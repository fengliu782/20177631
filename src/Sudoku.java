import java.io.File;
import java.util.Scanner;

public class Sudoku {

    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        TxtPro txtPro = new TxtPro();
        while(true){
            int[][]a=new int[3][3];

            String ss =  "";
            System.out.println("请输入文件路径，输入完成后按回车键结束");
            while (sc.hasNextLine()){
                ss =  sc.nextLine();
                if(!new File(ss).exists()){
                    System.out.println("该文件不存在，请重新输入");
                    if(!ss.endsWith("txt")){
                        System.out.println("文件类型错误，请输入txt文件路径");
                    }
                }else {
                    break;
                }
            }
            int b[] = txtPro.GetTxtData(ss);
            int bs=0;
            int out[] = new int[9];
            int outs=0;


            boolean[][] cols = new boolean[3][3];
            boolean[][] rows = new boolean[3][3];

            for (int i = 0; i < a.length; i++) {
                for (int j = 0; j < a.length; j++) {
                    a[i][j]=b[bs++];
                    if(a[i][j]!=0){

                        int val=a[i][j]-1;
                        rows[i][val] = true;
                        cols[j][val] = true;
                        //blocks[k][val] = true;
                    }
                }
            }//数据装载完毕

            DFS(a, cols, rows);
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 2; j++) {
                    out[outs++]=a[i][j];
                }
                out[outs++]=a[i][2];
            }

            int fileIndex = ss.lastIndexOf(".");
            String nFile = ss.substring(0,fileIndex)+"_out_"+System.currentTimeMillis();
            String ext = ss.substring(fileIndex);

            txtPro.WriteTxt(out,nFile+ext);

/*            System.out.println("是否结束：y/n?");

            String ter="";
            while(sc.hasNextLine()){
               String sa = sc.nextLine();
               if("y".equals(sa) || "Y".equals(sa) || "n".equals(sa) || "N".equals(sa)){
                   System.out.println("输入错误，请输入y/n");
               } else {
                   ter = sa;
                   break;
               }

            }*/


/*            if("y".equals(ter) || "Y".equals(ter)){
                break;
            }*/
        }
    }




    public static boolean DFS(int[][] a,boolean[][] cols,boolean[][] rows) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if(a[i][j]==0){

                    for (int l = 0; l < 3; l++) {
                        if(!cols[j][l]&&!rows[i][l]){//l对于的数字l+1没有在行列块中出现
                            rows[i][l] = cols[j][l] = true;
                            a[i][j] = 1 + l;//下标加1
                            if(DFS(a, cols, rows)) {
                                return true;//递进则返回true
                            }else {
                                rows[i][l] = cols[j][l] = false;//递进失败则回溯
                                a[i][j] = 0;
                            }
                        }
                    }
                    return false;//a[i][j]==0时，l发现都不能填进去
                }//the end of a[i][j]==0
            }
        }
        return true;//没有a[i][j]==0,则返回true
    }
}
