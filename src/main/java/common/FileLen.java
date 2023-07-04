package common;

import java.io.File;
import java.util.Observable;
//被观察者
public class FileLen extends Observable {
    //保存路径
    private String savePath;
    //文件总长度
    private int contentLength;
    public FileLen() {
    }
    //有参构造方法
    public FileLen(String savePath,int contentLength) {
        this.setSavePath(savePath,contentLength);
    }

    public String getSavePath() {
        return savePath;
    }

    public int getContentLength() {
        return contentLength;
    }

    public void setSavePath(String savePath, int contentLength) {
        File file = new File(savePath);
        int  length = (int)file.length();
        super.setChanged();//设置变化点
        super.notifyObservers(length);//保存路径改变
        this.savePath = savePath;
        this.contentLength=contentLength;
    }
}
