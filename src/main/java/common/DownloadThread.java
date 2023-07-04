package common;

import java.io.File;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

//下载任务
public class DownloadThread extends Thread  {
    private final String address;
 /*   private  int threadLen;
    private  int threadCount;*/
    private final String MD5;
    private final String savePath;
    private final int start;
    private final int end;
    private final int contentLength;
    private Object object = new Object();

    //threadLen  每个线程下载的目标文件总长度   threadCount  开启线程数
   /* public DownloadThread(String address, int threadLen, int threadCount, String MD5, String savePath) {
        this.address = address;
        this.threadLen = threadLen;
        this.threadCount = threadCount;
        this.MD5 = MD5;
        this.savePath = savePath;
    }*/
    public  DownloadThread(String address, int threadLen, int threadCount, String md5, String savePath, int start, int end,int contentLength) {
        this.address = address;
/*        this.threadLen = threadLen;
        this.threadCount = threadCount;*/
        this.MD5 = md5;
        this.savePath = savePath;
        this.start = start;
        this.end = end;
        this.contentLength = contentLength;
    }
    @Override
    public void run() {
        String task=savePath;
        int totalLength=contentLength;
        String taskMd5=MD5;
        String[] split = task.split("/");
        String TaskName = split[split.length - 1];
        try {
//                System.out.println(start+"--->"+end);
            URL downloadUrl = new URL(address);
//            System.out.println("正在下载"+address);
            HttpURLConnection connection = (HttpURLConnection) downloadUrl.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Range", "bytes=" + start + "-" + end);
            File outputFile = new File(task);
//            System.out.println("分片下载"+savePath);
            RandomAccessFile output = new RandomAccessFile(outputFile, "rw");
            output.seek(start);
            InputStream inputStream = connection.getInputStream();
            byte[] buffer = new byte[1024];
            int bytesRead;
//            new Progress(task,totalLength,TaskName,taskMd5);
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                output.write(buffer, 0, bytesRead);
                //listener.update(bytesRead)
            }
                output.close();
                inputStream.close();
       /*         String[] split = task.split("/");
                String TaskName = split[split.length - 1];*/
          /*  File file = new File(task);
            int length = (int)file.length();
            if(length==totalLength) {
                MD5verify md5verify = new MD5verify();
                boolean VerifyResults = md5verify.VerifyMD5(taskMd5, task);
                if (VerifyResults){
                    System.out.println(TaskName+"MD5核验成功");
                }
            }*//*else {
                new Progress(task,contentLength,TaskName,taskMd5);
            }*/
         } catch (Exception e) {
        }
    }
}
