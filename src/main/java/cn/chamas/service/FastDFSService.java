package cn.chamas.service;

import org.springframework.web.multipart.MultipartFile;

public abstract class FastDFSService {
    protected abstract String upload(MultipartFile file);
    protected abstract Boolean delete(String path);
}
