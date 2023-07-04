package common;

import java.io.File;
import java.util.Observable;
import java.util.Observer;

//查看进度的方法
//观察者
public class Progress2 implements Observer {
   private String savPath;
   private int contentLength;
   private String TaskName;

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
    public Progress2() {
    }
    public Progress2(String savPath, int contentLength, String taskName) {
        this.savPath = savPath;
        this.contentLength = contentLength;
        this.TaskName = taskName;
        progress();
    }

    void progress(){
        File file = new File(savPath);
        int length = (int) file.length();
        //循环监控网速，如果下载进度达到100%就结束监控
        //progress  文件下载进度    len文件总大小
        while(length!=contentLength) {
            //System.out.println("ThreadDownload.progress="+ThreadDownload.progress+"--ThreadDownload.len="+ThreadDownload.len);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //当前下载进度除以文件总长得到下载进度
            double p=(double)length/(double)contentLength*100;
            //当前下载进度减去前一秒的下载进度就得到一秒内的下载速度
//            temp= progress-temp;
            String format = String.format("%.2f", p);
            System.out.println(TaskName+"已下载==》"+format+"%");
            //System.out.println(p);
//            sl.speed(temp,p);
        }
//        sl.speed(temp,100);
        System.out.println(TaskName+"下载完毕啦");
    }
    @Override
    public void update(Observable o, Object arg) {
        
    }
}
