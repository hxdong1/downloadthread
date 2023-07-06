package common;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

//下载任务类
public class DownloadTask2 {
    private static int THREAD_COUNT = 8;
    private String address;//下载地址
    private int threadNum;//指定线程数
    private String MD5;//资源MD5值
    private String savePath;//存储地址
//    private String name;//资源名称
//    private String AbsolutPath;
//    private int contentLength;

    public DownloadTask2() {
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
    public void DownloadTask(String[] address, String[] MD5, int threadNum, String[] savePath) throws InterruptedException {
        this.threadNum = threadNum;
        for (int i = 0; i < address.length; i++) {
            this.address = address[i];
            this.MD5 = MD5[i];
            this.savePath = savePath[i];
            //
            run();
            Thread.sleep(100);
        }
    }

    public void DownloadTask(String[] address, String[] MD5, int threadNum, String savePath) throws IOException, InterruptedException {
        this.threadNum = threadNum;
        for (int i = 0; i < address.length; i++) {
            this.address = address[i];
            this.MD5 = MD5[i];
            this.savePath = savePath;
            run();
            Thread.sleep(100);
        }
    }
    public void run() {
        String add = address;
        String MD51 = MD5;
        new Thread() {
            @Override
            public void run() {
                /*String taskName = null;
                String AbsolutPath = null;*/
                try {
                    URL url = new URL(add);
                    //与目标资源建立连接
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    //获取目标资源的大小
                    int contentLength = conn.getContentLength();
                    //将资源目标地址进行分割
                    String[] split = add.split("/");
                    //最后一个索引位置即是文件名
                   String taskName = split[split.length - 1];
                    //绝对路径
                  String  AbsolutPath = savePath + "/" + taskName;
//                    System.out.println(taskName + "开始下载");
//                    System.out.println(name+"大小为"+contentLength);
                    //此处可以进行断点续传判断，进到本地查看本地资源
                    System.out.println("现在正在下载"+taskName);
                    //线程数默认值是8.判断调用者有传来指定的线程数
                    if (threadNum != 0) {
                        THREAD_COUNT = threadNum;
                    }
                    //平均每个线程分到多少下载资源
                    int threadLen = contentLength / THREAD_COUNT;
                    //起始字节
                    int start;
                    //结束字节
                    int end;
                    //进行遍历
                    //写一个监听器，监听所有的线程加载的进度
                    //update方法中进行加载，将所有的字节分片进行存储，命名规则，任务名+线程号+
                    //listener = new listener(total)
                    //创建一个监听对象，
                    //根据需要创建的线程数进行遍历
                    for (int i = 0; i < THREAD_COUNT; i++) {
                        start = i * threadLen;
                        if (i == (THREAD_COUNT - 1)) {
                            end = contentLength;
                        }
                        //如果i=0，就从0开始
                        else {
                            end = ((i + 1) * threadLen) - 1;
                        }
                        new DownloadThread2(add, threadLen, i, MD51, AbsolutPath, start, end, contentLength);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                //在Java中如果有3个下载任务，这3个下载任务进行同时下载，并且每个下载任务又分成了5个线程进行分块下载，我现在要要监听每一个下载任务的进度，根据以上需求，给出相应代码
            }
            //
//            private void Progress() {
//            }
        }.start();
    }
}
