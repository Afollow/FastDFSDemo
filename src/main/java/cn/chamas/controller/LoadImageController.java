package cn.chamas.controller;


import cn.chamas.domain.Image;
import cn.chamas.domain.ResultBody;
import cn.chamas.service.FastDFSAsync;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import java.util.concurrent.CompletableFuture;

@CrossOrigin
@RestController
public class LoadImageController {
    @Autowired
    private ResultBody resultBody;
    @Autowired
    private Image localImage;
    @Autowired
    private FastDFSAsync fastDfsAsync;

    @SneakyThrows
    @RequestMapping(value = "upload", method = RequestMethod.POST)
    public ResultBody upload(MultipartFile file) {

        if (file.isEmpty()){
            return resultBody.ready()
                    .setCode(100)
                    .setMsg("图片文件为空");
        }

        CompletableFuture<String> future = fastDfsAsync.uploadFile(file);
        // 图片地址（可储存到数据库）
        localImage.ready()
                .setMaxPath(future.get())
                .setMinPath(fastDfsAsync.getPath(future.get()));

        return resultBody.ready()
                .setMsg("上传成功")
                .setData(localImage);
    }

    @SneakyThrows
    @RequestMapping(value = "delete", method = RequestMethod.POST)
    private ResultBody delete(String path) {

        if(path == null || path.isEmpty() || "null".equals(path) ||
                "undefined".equals(path) || "not defined".equals(path)){
            return resultBody.ready()
                    .setCode(101)
                    .setMsg("图片路径不能为空");
        }

        CompletableFuture<Boolean> future1 = fastDfsAsync.deleteFile(path);
        CompletableFuture<Boolean> future2 = fastDfsAsync.deleteFile(fastDfsAsync.getPath(path));
        CompletableFuture.allOf(future1, future2).join();

        if(! future1.get() || ! future2.get()){
            return resultBody.ready()
                    .setCode(102)
                    .setMsg("图片删除失败");
        }

        return resultBody.ready()
                .setMsg("删除成功");
    }
}
