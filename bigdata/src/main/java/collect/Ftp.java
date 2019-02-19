package collect;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.SocketException;

/**
 * 本地文件通过FTP客户端上传FTP服务器指定目录方法，指定目录如果不存在会保存在根目录
 * 使用apache.commons.net.ftp包
 */

public class Ftp {
    private static String ip = "172.16.148.3";//FTP 服务器IP地址
    private static int port = 9800;//FTP 服务器端口
    private static String username = "qoeym"; //FTP 登陆用户名
    private static String password = "Bjhmdys@QOE!@# "; //FTP 登陆密码

    /**
     * FTP服务器建立连接方法
     * @param localFilePath  本地文件路径
     * @param remoteDir      远程目录
     * @param remoteFileName 远程文件名
     * @return 返回FTPClient 实例
     */

    public static void FTPClient(String localFilePath, String remoteDir, String remoteFileName) {

        FTPClient ftpClient = null;
        try {
            System.out.println("连接FTP服务器 :" + ip + ":" + port);
            ftpClient = new FTPClient();
            ftpClient.setDataTimeout(60000);       //设置传输超时时间为60秒
            ftpClient.setConnectTimeout(60000);       //连接超时为60秒
            ftpClient.connect(ip, port);// 连接FTP服务器
            ftpClient.enterLocalPassiveMode();
            ftpClient.enterRemotePassiveMode();
            ftpClient.login(username, password);// 登陆FTP服务器

            if (!FTPReply.isPositiveCompletion(ftpClient.getReplyCode())) {
                System.err.println("未连接到FTP，用户名或密码错误");
                ftpClient.disconnect();
            } else {

                System.out.println("FTP连接成功");

                boolean upLoadRcord = upLoadFile(ftpClient, remoteDir, remoteFileName, localFilePath);

                if (upLoadRcord) {

                    System.out.println("文件上传成功");

                } else

                    System.out.println("文件上传失败");//远程目录包括文件返回此提示

            }
        } catch (SocketException e) {
            e.printStackTrace();
            System.err.println("FTP服务器IP地址错误");
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("FTP服务器端口错误");
        }

    }

    /**
     * 本地上传文件到FTP服务器
     *
     * @param ftpClient      FTP Client 实例
     * @param remoteFtpDir   FTP目录
     * @param remoteFileName FTP文件名
     * @param localFilePath  本地文件名
     * @return
     */
    public static boolean upLoadFile(FTPClient ftpClient, String remoteFtpDir, String remoteFileName, String localFilePath) {
        boolean success = false;
        try {
            FileInputStream input = new FileInputStream(new File(localFilePath));
            System.out.println("开始上传文件到" + remoteFtpDir + remoteFileName);
            int reply = ftpClient.getReplyCode();
        /*System.out.println("replyCode:" +reply);*/
            if (!FTPReply.isPositiveCompletion(reply)) {
                ftpClient.disconnect();
                return success;
            }
            success = ftpClient.changeWorkingDirectory(remoteFtpDir);
            if (success == false) {
                System.out.println("指定目录不存在");
                return success;
            }
            success = ftpClient.storeFile(remoteFileName, input);
        /*System.out.println("putReply:" +success);*/
            input.close(); //输入流关闭
            ftpClient.logout();//退出
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (ftpClient.isConnected()) {
                try {
                    ftpClient.disconnect();
                } catch (IOException ioe) {
                }
            }
        }
        return success;
    }
}

