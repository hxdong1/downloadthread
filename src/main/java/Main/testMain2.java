package Main;

import common.DownloadTask;
import common.DownloadTask2;
//QQ的MD5值：15F3CE0B9803D5C58B3916E7B376E47D
//15F3CE0B9803D5C58B3916E7B376E47D
//vx的MD5值：C7EBE930AB300FBE5E197FD6E3BAA18F
//C7EBE930AB300FBE5E197FD6E3BAA18F
//钉钉的MD5值：E1434773D5CA2A868BBD95E61AD5C25E

public class testMain2 {
    public static void main(String[] args) throws InterruptedException {
        DownloadTask2 downloadTask2 = new DownloadTask2();
        String[] add = new String[]{
                "https://55c2ea717e501f920ca73c73193f409e.rdt.tfogc.com:49156/dldir1.qq.com/weixin/Windows/WeChatSetup.exe",
                "https://dldir1.qq.com/qqfile/qq/PCQQ9.7.9/QQ9.7.9.29065.exe",
                "https://dtapp-pub.dingtalk.com/dingtalk-desktop/win_downloader/dingtalk_downloader.exe"
        };
        String[] md5 = new String[]{"C7EBE930AB300FBE5E197FD6E3BAA18F", "15F3CE0B9803D5C58B3916E7B376E47D", "E1434773D5CA2A868BBD95E61AD5C25E"};
        String[] savePath = new String[]{"E:\\视频", "E:\\视频", "E:\\视频"};
        downloadTask2.DownloadTask(add, md5, 5, savePath);
    }
}
