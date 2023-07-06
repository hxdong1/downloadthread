package common;

//断点续传类
public class SequelTask {
    //两个方法
    //判断方法——》判断本地文件之前有没有下载过，如果有就返回true，执行继续下载，如果没有就返回false，下载新资源
    //断点续传方法，调用方法时使用if判断

    boolean LocalJudgment() {
        //调用这传来方法,判断本地是否存在，并返回布尔值
        if (true) {
            return true;
        }
        return false;
    }

    //继续下载方法
    void ContinueDownloading() {
    }
}
