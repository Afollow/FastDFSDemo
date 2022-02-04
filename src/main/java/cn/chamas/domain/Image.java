package cn.chamas.domain;

import org.springframework.stereotype.Component;

@Component
public class Image {
    private String maxPath;
    private String minPath;

    public String getMaxPath() {
        return maxPath;
    }

    public Image setMaxPath(String maxPath) {
        this.maxPath = maxPath;
        return this;
    }

    public String getMinPath() {
        return minPath;
    }

    public Image setMinPath(String minPath) {
        this.minPath = minPath;
        return this;
    }

    public Image ready(){
        this.minPath = null;
        this.maxPath = null;
        return this;
    }

    @Override
    public String toString() {
        return "图片路径：" + maxPath + "\n缩略图路径：" + minPath;
    }
}
