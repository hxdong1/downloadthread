package common;

import java.io.File;
import java.security.NoSuchAlgorithmException;

//查看进度的方法
public class Progress  {
   private String savPath;
   private int contentLength;
   private String TaskName;
   private String MD5;

    public String getMD5() {
        return MD5;
    }

    public void setMD5(String MD5) {
        this.MD5 = MD5;
    }

    public String getSavPath() {
        return savPath;
    }

    public void setSavPath(String savPath) {
        this.savPath = savPath;
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



    public Progress() {
    }
    public Progress(String savPath, int contentLength, String taskName,String MD5) {
        this.savPath = savPath;
        this.contentLength = contentLength;
        this.TaskName = taskName;
        this.MD5=MD5;
        progress1();
    }


    void progress1()  {
//        File file = new File(savPath);
//        int length = (int) file.length();
        //progress  文件下载进度    len文件总大小
//        while(length!=contentLength) {
        new Thread(){
            @Override
            public void run() {
                while(true) {
                    File file = new File(savPath);
                    int length = (int) file.length();
                    if(length==contentLength){
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
                        break;
                    }
                    //System.out.println("ThreadDownload.progress="+ThreadDownload.progress+"--ThreadDownload.len="+ThreadDownload.len);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    //当前下载进度除以文件总长得到下载进度
                    double p=(double)length/(double)contentLength*100;
//            temp= progress-temp;
                    String format = String.format("%.2f", p);
//                String[] split = task.split("/");
//                String TaskName = split[split.length - 1];
                /*File file = new File(task);
                int length = (int)file.length();*/
//                if(length==contentLength) {
//                    MD5verify md5verify = new MD5verify();
//                    boolean VerifyResults = false;
//                    try {
//                        VerifyResults = md5verify.VerifyMD5(MD5, savPath);
//                    } catch (NoSuchAlgorithmException e) {
//                        e.printStackTrace();
//                    }
//                    if (VerifyResults){
//                        System.out.println(TaskName+"MD5核验成功");
//                    }
//
//            }
                    System.out.println(TaskName+"已下载==》"+format+"%");
                    //System.out.println(p);
//            sl.speed(temp,p);
                }
            }
        }.start();

//        sl.speed(temp,100);
//        System.out.println(TaskName+"下载完毕啦");
    }
}
