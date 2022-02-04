package cn.chamas.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.util.concurrent.CompletableFuture;

@Service
public class FastDFSAsync extends FastDFSProve {

    @Async("asyncUserServiceExecutor")
    public CompletableFuture<String> uploadFile(MultipartFile file){
        return CompletableFuture.completedFuture(super.upload(file));
    }
    @Async("asyncUserServiceExecutor")
    public CompletableFuture<Boolean> deleteFile(String path){
        return CompletableFuture.completedFuture(super.delete(path));
    }

    @Override
    public String getPath(String path) {
        return super.getPath(path);
    }
}
