package common;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

//查看进度的方法
public class ProgressListener2 {
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

    public ProgressListener2() {

    }

    public ProgressListener2(int contentLength, String taskName, String MD5) {
        this.contentLength = contentLength;
        TaskName = taskName;
        this.MD5 = MD5;
        File file = new File(taskName);
        int length = (int) file.length();
        //progress  文件下载进度    len文件总大小
        while(length!=contentLength)
        {
            new Thread() {
                @Override
                public void run() {
                    while (true) {
                        File file = new File(taskName);
                        int length = (int) file.length();
                        if (length == contentLength) {
                            MD5verify md5verify = new MD5verify();
                            boolean VerifyResults = false;
                            try {
                                VerifyResults = md5verify.VerifyMD5(MD5, taskName);
                            } catch (NoSuchAlgorithmException e) {
                                e.printStackTrace();
                            }
                            if (VerifyResults) {
                                int i = taskName.lastIndexOf("/");
                                String substring = taskName.substring(i + 1);
                                System.out.println(substring + "MD5核验成功");
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

                        String format = String.format("%.2f", p);

                        System.out.println(TaskName + "已下载==》" + format + "%");
                /*File file = new File(task);
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
                    }
                }
            }.start();
        }
    }

    public ProgressListener2(int contentLength, String taskName) {
        this.contentLength = contentLength;
        TaskName = taskName;
    }

    public ProgressListener2(int contentLength) {
        this.contentLength = contentLength;
    }

    public ProgressListener2(String savPath, int contentLength, String taskName) {
        this.savaPath = savPath;
        this.contentLength = contentLength;
        TaskName = taskName;
    }

    public ProgressListener2(String savPath, int contentLength) {
        this.savaPath = savPath;
        this.contentLength = contentLength;
    }

    public ProgressListener2(String savPath) {
        this.savaPath = savPath;
    }

    public ProgressListener2(String savPath, int contentLength, String taskName, String MD5) {
        this.savaPath = savPath;
        this.contentLength = contentLength;
        this.TaskName = taskName;
        this.MD5 = MD5;
    }


}