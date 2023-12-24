package io.github.ncc0706.properties;


import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@EqualsAndHashCode(callSuper = false)
@ConfigurationProperties(prefix = CanalSimpleProperties.CANAL_PREFIX)
public class CanalKafkaProperties extends CanalProperties {

    private Integer partition;

    private String groupId;

}
