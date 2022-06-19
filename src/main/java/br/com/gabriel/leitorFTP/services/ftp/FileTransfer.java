package br.com.gabriel.leitorFTP.services.ftp;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
public class FileTransfer {
    private String uuid;
    private String path;
    private String pathLocal;
}
