package common;
import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.NoSuchAlgorithmException;

//下载任务类
public class DownloadTask{
    private static int THREAD_COUNT = 8;
    private String address;//下载地址
    private  int threadNum;//指定线程数
    private  String MD5;//资源MD5值
    private  String savePath;//存储地址
//    private String name;//资源名称
//    private String AbsolutPath;
//    private int contentLength;

    public DownloadTask() {
    }
  /*  public void DownloadTask(String address,  int threadNum, String savePath) throws IOException {
        this.address = address;
        this.threadNum = threadNum;
        this.savePath = savePath;
        DownloadTask downloadTask = new DownloadTask();
        downloadTask.setName(address);
        System.out.println(downloadTask.getName()+"开始下载");
        run();
    }*/
    public void DownloadTask(String[] address, String[] MD5, int threadNum, String[] savePath) throws IOException, InterruptedException {
        this.threadNum = threadNum;
        for (int i = 0; i < address.length; i++) {

            this.address=address[i];
            this.MD5=MD5[i];
            this.savePath=savePath[i];
            //
            run();
            Thread.sleep(100);
        }
    }
    public void DownloadTask(String[] address, String[] MD5, int threadNum, String savePath) throws IOException, InterruptedException {
        this.threadNum = threadNum;
        for (int i = 0; i < address.length; i++) {

            this.address=address[i];
            this.MD5=MD5[i];
            this.savePath=savePath;
            //
            run();




//            Thread.sleep(100);
        }
    }
    //构造方法
  /*  public void DownloadTask(String address, String MD5, int threadNum, String savePath) throws IOException {
        this.address = address;
        this.threadNum = threadNum;
        this.MD5 = MD5;
        this.savePath = savePath;
        DownloadTask downloadTask = new DownloadTask();
        downloadTask.setName(address);
        System.out.println(downloadTask.getName()+"开始下载");
        run();
    }*/
  /*    public   void xiazai1(){
          DownloadTask downloadTask = new DownloadTask();
          downloadTask.setName(address);
          System.out.println(downloadTask.getName()+"开始下载");
            run();
      }*/
    public void run() {
        String add=address;
        String MD51=MD5;

        new Thread(){
            @Override
            public void run() {
                String taskName=null;
                String AbsolutPath=null;

                try {
                    URL url = new URL(add);
                    //与目标资源建立连接
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    //获取目标资源的大小
                  int contentLength = conn.getContentLength();
                    String[] split = add.split("/");
                    taskName= split[split.length - 1];
                    AbsolutPath = savePath+"/"+taskName;
                    System.out.println(taskName+"开始下载");
//                    System.out.println(name+"大小为"+contentLength);
                    //进到本地查看本地资源
                    if (threadNum != 0) {
                        THREAD_COUNT = threadNum;
                    }
//                  System.out.println("一共开启"+THREAD_COUNT+"个线程");
                    //平均每个线程分到多少下载资源
                    int threadLen = contentLength / THREAD_COUNT;
                    //起始字节
                    int start;
                    //结束字节
                    int end;
                    //进行遍历

                    //listener = new listener(total)
                    //update(long process)
                    //
                    for (int i = 0; i < THREAD_COUNT; i++) {
                        start = i * threadLen;
                        if (i == (THREAD_COUNT - 1)) {
//                            end = threadLen * THREAD_COUNT;
                            end = contentLength;
                        }
                        //如果i=0，就从0开始
                        else {
                            end =((i+1)*threadLen)-1;
                        }
                        //将开头和结尾调用下载任务方法进行下载
//                        System.out.println((i+1)+"线程处理范围是"+start+"----"+end);
                        DownloadThread downloadThread = new DownloadThread(add, threadLen, THREAD_COUNT, MD51, AbsolutPath, start, end,contentLength);
                        new Thread(downloadThread).start();
                       /* File file = new File(AbsolutPath);
                        int length = (int)file.length();
                        if(length==contentLength) {
                            MD5verify md5verify = new MD5verify();
                            boolean VerifyResults = false;
                            try {
                                VerifyResults = md5verify.VerifyMD5(MD5,AbsolutPath);
                            } catch (NoSuchAlgorithmException e) {
                                e.printStackTrace();
                            }
                            if (VerifyResults){
                                System.out.println(taskName+"MD5核验成功");
                            }

                        }*/
                    }
                    new Progress(AbsolutPath,contentLength,taskName,MD51);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            //
//            private void Progress() {
//            }
        }.start();
       /* try {
            URL url = new URL(address);
            //与目标资源建立连接
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            //获取目标资源的大小
            int contentLength = conn.getContentLength();
            System.out.println(name+contentLength);
            //进到本地查看本地资源
            if (threadNum != 0) {
                THREAD_COUNT = threadNum;
            }
            System.out.println("一共开启"+THREAD_COUNT+"个线程");
            //平均每个线程分到多少下载资源
            int threadLen = contentLength / THREAD_COUNT;
            //起始字节
            int start;
            //结束字节
            int end;
            //进行遍历
            for (int i = 0; i < THREAD_COUNT; i++) {
                start = i * threadLen;
                if (i == (THREAD_COUNT - 1)) {
                    end = threadLen * THREAD_COUNT;
                }
                //如果i=0，就从0开始
                else {
                    end =((i+1)*threadLen)-1;
                }
                //将开头和结尾调用下载任务方法进行下载
                System.out.println((i+1)+"线程处理范围是"+start+"----"+end);
                DownloadThread downloadThread = new DownloadThread(
                        address, threadLen, THREAD_COUNT, MD5, savePath, start, end);
                downloadThread.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
*/
    }
   /* //下载方法  暂未开启线程
   public void download(DownloadPar downloadPar) throws IOException {
       //下载之前进行校验，看本地之前有无下载
       URL url = new URL(downloadPar.getAdd());
        //与目标资源建立连接
       HttpURLConnection conn = (HttpURLConnection) url.openConnection();
       //获取目标资源的大小
       int contentLength = conn.getContentLength();
       //进到本地查看本地资源
       if (downloadPar.getThreadNum()!=0){
           THREAD_COUNT=downloadPar.getThreadNum();
       }
       }
       //平均每个线程分到多少下载资源
       int threadLen = contentLength / THREAD_COUNT;
   }*/
}
