package io.github.ncc0706.properties;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@EqualsAndHashCode(callSuper = false)
@ConfigurationProperties(prefix = CanalSimpleProperties.CANAL_PREFIX)
public class CanalSimpleProperties extends CanalProperties {

    private String userName;

    private String password;

}
