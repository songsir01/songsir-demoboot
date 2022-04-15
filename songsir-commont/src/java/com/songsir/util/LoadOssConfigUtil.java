package com.songsir.util;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

/**
 * @PackageName com.songsir.util
 * @ProjectName songsir-commont
 * @Author: SongYapeng
 * @Date: Create in 14:32 2021/12/3
 * @Description: 加载oss配置
 * @Copyright:
 */
@Slf4j
public class LoadOssConfigUtil {

    /**
     * 配置文件路径, 文件地址勿动
     */
    private static final String CONFIG_FILE = "config/ali_oss.json";

    /**
     * 配置
     */
    private static final AliOssFileConfig OSS_CONFIG = new AliOssFileConfig();

    /**
     * @param
     * @return com.songsir.util.AliOssFileConfig
     * @Description
     * @MethodName getOssConfig
     * @Auther SongYapeng
     * @Date 2022/4/15 14:43
     * @Since JDK 1.8
     */
    public static AliOssFileConfig getOssConfig() {
        if (StringUtils.isNotBlank(OSS_CONFIG.getAccessKeyId())) {
            return OSS_CONFIG;
        }
        AliOssFileConfig sdkConfig = new AliOssFileConfig();
        try {
            String configFile = ResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX + CONFIG_FILE;
            Resource[] resources = new PathMatchingResourcePatternResolver().getResources(configFile);
            for (int i = resources.length - 1; i >= 0; i--) {
                Resource resource = resources[i];
                StringBuilder script = new StringBuilder();
                try (InputStreamReader isr = new InputStreamReader(resource.getInputStream(), StandardCharsets.UTF_8);
                     BufferedReader bufferReader = new BufferedReader(isr)) {
                    String tempString;
                    while ((tempString = bufferReader.readLine()) != null) {
                        script.append(tempString).append("\n");
                    }
                } catch (IOException e) {
                    log.error("读取配置文件失败：", e);
                }
                if (script.length() > 0) {
                    AliOssFileConfig sdkConfig0 = JSON.parseObject(script.toString(), AliOssFileConfig.class);
                    BeanUtils.copyProperties(sdkConfig0, sdkConfig);
                }
            }
        } catch (IOException e) {
            log.error("解析OSS配置文件失败");
        }
        BeanUtils.copyProperties(sdkConfig, OSS_CONFIG);
        return sdkConfig;
    }

}
