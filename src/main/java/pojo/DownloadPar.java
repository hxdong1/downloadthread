package pojo;

//下载参数类
public class DownloadPar {
    //目标资源
    String add;
    //MD5值
    String MD5;
    //线程数
    int threadNum;
    //保存路径
    String savePath;

    @Override
    public String toString() {
        return "DownloadPar{" +
                "add='" + add + '\'' +
                ", MD5='" + MD5 + '\'' +
                ", threadNum=" + threadNum +
                ", savePath='" + savePath + '\'' +
                '}';
    }
    public void setAdd(String add) {
        this.add = add;
    }

    public void setMD5(String MD5) {
        this.MD5 = MD5;
    }

    public void setThreadNum(int threadNum) {
        this.threadNum = threadNum;
    }

    public void setSavePath(String savePath) {
        this.savePath = savePath;
    }

    public String getAdd() {
        return add;
    }

    public String getMD5() {
        return MD5;
    }

    public int getThreadNum() {
        return threadNum;
    }

    public String getSavePath() {
        return savePath;
    }

    public DownloadPar(String add, String MD5, int threadNum, String savePath) {
        this.add = add;
        this.MD5 = MD5;
        this.threadNum = threadNum;
        this.savePath = savePath;
    }
    public DownloadPar() {
    }
}
