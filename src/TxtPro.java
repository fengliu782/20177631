import java.io.*;

public class TxtPro {
    public int[] GetTxtData(String filePath){
        System.out.println("----------------开始处理数据----------------");
        File orgFile = new File(filePath);
        String totalstr = "";
        int[]a=new int[9];
        if(orgFile.exists()){
            try {
                FileReader fr = new FileReader(orgFile);
                BufferedReader br = new BufferedReader(fr);
                String line = "";
                int s = 0;
                while ((line = br.readLine()) != null){  //按行读取文件流的内容
                    String[] lData =  line.split(" ");
                    for(int i=0;i<lData.length;i++){
                        try {
                            a[s]=Integer.parseInt(lData[i]);
                            s= s + 1;
                            if(s == 9){
                                break;
                            }
                        }catch (Exception e){
                            System.out.println(e.getMessage());
                        }

                    }
                }
                fr.close();
                br.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else{
            System.out.println("文件不存在");
        }
        return a;
    }


    public void WriteTxt(int a[],String filepath){
        System.out.println("----------------数据处理完成--------------");
        try {
            File tarFile = new File(filepath);
            if(!tarFile.isFile()){
                tarFile.createNewFile();
            }
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(tarFile),"utf-8"));
            for(int i=0; i<a.length; i++){
                if(i == 0){
                    bw.write(a[i]+"\t");
                }else {
                    if(i % 3 == 0){
                        bw.write("\r\n");
                    }
                    bw.write(a[i]+"\t");
                }
            }
            bw.close();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
