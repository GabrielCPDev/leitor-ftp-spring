package br.com.gabriel.leitorFTP.services;

import br.com.gabriel.leitorFTP.services.ftp.TransferFTPService;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Log
@Service
public class TrasnferFileService {

    @Autowired
    private TransferFTPService ftpService;

    @Scheduled(fixedRate = 100*600)
    public void execute() throws IOException {
        ftpService.execute();

    }
}
