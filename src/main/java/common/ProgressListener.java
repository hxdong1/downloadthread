package common;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

//查看进度的方法
public class ProgressListener {
    private String savaPath;
    private int contentLength;
    private String TaskName;
    private String MD5;
    public volatile int progress;
//    private static int progress;


    //临时文件存放处
    private final String tempFileSave = "E:\\ProgramData";

    public String getMD5() {
        return MD5;
    }

    public void setMD5(String MD5) {
        this.MD5 = MD5;
    }

    public String getSavPath() {
        return savaPath;
    }

    public void setSavPath(String savPath) {
        this.savaPath = savPath;
    }

    public int getContentLength() {
        return contentLength;
    }

    public void setContentLength(int contentLength) {
        this.contentLength = contentLength;
    }

    public String getTaskName() {
        return TaskName;
    }

    public void setTaskName(String taskName) {
        TaskName = taskName;
    }

    public ProgressListener() {

    }

    public ProgressListener(int contentLength, String taskName, String MD5) {
        this.contentLength = contentLength;
        TaskName = taskName;
        this.MD5 = MD5;
    }

    public ProgressListener(int contentLength, String taskName) {
        this.contentLength = contentLength;
        TaskName = taskName;
    }

    public ProgressListener(int contentLength) {
        this.contentLength = contentLength;
    }

    public ProgressListener(String savPath, int contentLength, String taskName) {
        this.savaPath = savPath;
        this.contentLength = contentLength;
        TaskName = taskName;
    }

    public ProgressListener(String savPath, int contentLength) {
        this.savaPath = savPath;
        this.contentLength = contentLength;
    }

    public ProgressListener(String savPath) {
        this.savaPath = savPath;
    }

    public ProgressListener(String savPath, int contentLength, String taskName, String MD5) {
        this.savaPath = savPath;
        this.contentLength = contentLength;
        this.TaskName = taskName;
        this.MD5 = MD5;
    }

    //        File file = new File(savPath);
//        int length = (int) file.length();
    //progress  文件下载进度    len文件总大小
//        while(length!=contentLength) {
/*        new Thread() {
            @Override
            public void run() {
                while (true) {
                    File file = new File(savPath);
                    int length = (int) file.length();
                    if (length == contentLength) {
                        MD5verify md5verify = new MD5verify();
                        boolean VerifyResults = false;
                        try {
                            VerifyResults = md5verify.VerifyMD5(MD5, savPath);
                        } catch (NoSuchAlgorithmException e) {
                            e.printStackTrace();
                        }
                            if (VerifyResults) {
                                System.out.println(TaskName + "MD5核验成功");
                            }
                        break;
                    }
                    //System.out.println("ThreadDownload.progress="+ThreadDownload.progress+"--ThreadDownload.len="+ThreadDownload.len);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    //当前下载进度除以文件总长得到下载进度
                    double p = (double) length / (double) contentLength * 100;
            temp= progress-temp;
                    String format = String.format("%.2f", p);
                String[] split = task.split("/");
                String TaskName = split[split.length - 1];
                *//*File file = new File(task);
                int length = (int)file.length();*/
        /*
                if(length==contentLength) {
                    MD5verify md5verify = new MD5verify();
                    boolean VerifyResults = false;
                    try {
                        VerifyResults = md5verify.VerifyMD5(MD5, savPath);
                    } catch (NoSuchAlgorithmException e) {
                        e.printStackTrace();
                    }
                    if (VerifyResults){
                        System.out.println(TaskName+"MD5核验成功");
                    }
            }
                    System.out.println(TaskName + "已下载==》" + format + "%");
                    //System.out.println(p);
            sl.speed(temp,p);
                }
            }
        }.start();*/
  /*      sl.speed(temp,100);
        System.out.println(TaskName+"下载完毕啦");*/
//    public String progress2(){
//    }
    public String progress1(int bytesRead, int threadLen, int threadNum, String TaskName, int contentLength, String task, String taskMd5) {
        String format = null;

        // 延迟1秒后执行任务
        double i = 0;

        File file = null;
        try {
            File file1 = new File(tempFileSave);
            if (file1.exists()) {
                file = new File(file1, TaskName + threadNum + ".temp");
            }
//                    RandomAccessFile info = new RandomAccessFile(getTemp_path() + getFileName() + threadId + ".temp", "rwd");
            //
            assert file != null;
            FileOutputStream fileOutputStream = new FileOutputStream(file, true);
                  /*  RandomAccessFile info = new RandomAccessFile(file,"rwd");
                    info.write(String.valueOf(total + startIndex).getBytes());
                    info.close();*/

            fileOutputStream.write(bytesRead);

            fileOutputStream.close();

            progress = progress + bytesRead;
            i = (double) progress / (double) contentLength * 100;
            //                        System.out.println("===="+progress);
            format = String.format("%.2f", i);


//                        System.out.println();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return TaskName + "====" + format + "%";
    }
//        }.start();
}







