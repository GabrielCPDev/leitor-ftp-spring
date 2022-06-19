package br.com.gabriel.leitorFTP.services.ftp;

import lombok.extern.java.Log;
import lombok.extern.log4j.Log4j;
import org.apache.commons.net.PrintCommandListener;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.*;
@Log
@Service
public class TransferFTPService {
    @Value("${ftp.server}")
    private String SERVER;
    @Value("${ftp.port}")

    private String PORT;
    @Value("${ftp.user}")
    private String USER;
    @Value("${ftp.password}")
    private String PASSWORD;

    public void execute() throws IOException {
        FTPClient ftpClient = new FTPClient();
        try{

            //CConfiguração do ftp client
            ftpClient.addProtocolCommandListener(new PrintCommandListener(new PrintWriter(System.out)));
            ftpClient.connect(SERVER, Integer.parseInt(PORT));
            ftpClient.login(USER, PASSWORD);
            ftpClient.enterLocalPassiveMode();
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);

            //lista os arquivos
            String[] arquivos = ftpClient.listNames();

            for (String itemFile : arquivos) {
                String remoteFile = itemFile;
                File tmpDownload = new File(itemFile);

                //baixa o arquivo
                OutputStream outputStream = new BufferedOutputStream(new FileOutputStream(tmpDownload));
                boolean success = ftpClient.retrieveFile(remoteFile, outputStream);
                outputStream.close();
                if (success){
                    log.info("Arquivo recebido com sucesso");
                }

            }
        } catch (Exception e) {
            log.info(e.getMessage());
            e.printStackTrace();
        } finally {
            if (ftpClient.isConnected()) {
                ftpClient.logout();
            } else {
                ftpClient.disconnect();
            }
        }
    }
}
