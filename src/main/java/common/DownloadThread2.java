package common;

import java.io.File;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;

//下载任务
public class DownloadThread2 {
    private final String address;//下载地址
    private int threadLen;//每个线程下载的数量
    private int threadNum;//线程号
    private final String MD5;//MD5值
    private final String savePath;//绝对路径
    private final int start;//线程开始下载结点
    private final int end;//线程结束结点
    private final int contentLength;//总的文件大小
    private static int progress=0;
    private ProgressListener progressListener;
    //下载进度最终状态，默认是false，表示没有下载完毕
    //这个变量可以后期当任务下载完毕后修改为true，进行别的条件判断
    Boolean isBpDownload =false;
    //threadLen  //每个线程的处理字节的数量
    //threadNum  这是第几号线程
    public DownloadThread2(String address, int threadLen, int threadNum, String md5, String savePath, int start, int end, int contentLength, ProgressListener progressListener) {
        this.address = address;
        this.threadLen = threadLen;
        this.threadNum = threadNum;
        this.MD5 = md5;
        this.savePath = savePath;
        this.start = start;
        this.end = end;
        this.contentLength = contentLength;
        this.progressListener=progressListener;
        run();
    }

    public DownloadThread2(String address, int threadLen, int threadNum, String md5, String savePath, int start, int end, int contentLength) {
        this.address = address;
        this.threadLen = threadLen;
        this.threadNum = threadNum;
        this.MD5 = md5;
        this.savePath = savePath;
        this.start = start;
        this.end = end;
        this.contentLength = contentLength;
        run();
    }

    public void run() {
        String task = savePath;
        int totalLength = contentLength;
        String taskMd5 = MD5;
        new Thread(){
            @Override
            public void run() {
                String[] split = task.split("/");
                String TaskName = split[split.length - 1];
                try {
//    System.out.println(start+"--->"+end);
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
                    while ((bytesRead = inputStream.read(buffer)) != -1) {
                        output.write(buffer, 0, bytesRead);
                    }
                    output.close();
                    inputStream.close();
                } catch (Exception e) {
                }
            }
        }.start();
        new ProgressListener2(totalLength,task,taskMd5);
    }
}
