package com.songsir.util;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * @PackageName com.songsir.util
 * @ProjectName songsir-commont
 * @Author: SongYapeng
 * @Date: Create in 14:34 2021/12/3
 * @Description: 阿里OSS配置信息
 * @Copyright:
 */
@Data
public class AliOssFileConfig {

    /**
     * oss url
     */
    @JsonProperty("end_point")
    private String endPoint;

    /**
     * keyID
     */
    @JsonProperty("access_key_id")
    private String accessKeyId;

    /**
     * secret
     */
    @JsonProperty("access_key_secret")
    private String accessKeySecret;

    @JsonProperty("bucket_name_1")
    private String bucketName1;

    /**
     * 摘要算法SM4加密密钥
     */
    @JsonProperty("key")
    private String key;


    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this,
                ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
