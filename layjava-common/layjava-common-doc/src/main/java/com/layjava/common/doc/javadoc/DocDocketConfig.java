package com.layjava.common.doc.javadoc;

import com.layjava.common.doc.javadoc.properties.DocsProperty;
import io.swagger.models.Scheme;
import org.noear.solon.Solon;
import org.noear.solon.annotation.Bean;
import org.noear.solon.annotation.Configuration;
import org.noear.solon.annotation.Import;
import org.noear.solon.annotation.Inject;
import org.noear.solon.core.BeanWrap;
import org.noear.solon.core.handle.Result;
import org.noear.solon.docs.DocDocket;
import org.noear.solon.docs.models.ApiContact;
import org.noear.solon.docs.models.ApiInfo;

@Import(profilesIfAbsent = "classpath:-solon-common-javadoc-plugin.yml")
@Configuration
public class DocDocketConfig {

    /**
     * 构建 DocDocket 实例，并配置相关信息
     */
    @Bean
    public void buildDocDocket(@Inject DocsProperty docsProperty) {
        if (!docsProperty.isEnable()) {
            return;
        }

        docsProperty.getGroupConfigs().forEach(groupConfig -> {
            //构建 DocDocket 实例
            DocDocket docDocket = new DocDocket();
            docDocket.groupName(groupConfig.getGroup());

            //配置API信息
            ApiInfo apiInfo = new ApiInfo().title(docsProperty.getTitle())
                    .description(docsProperty.getDescription())
                    .version("1.0");

            //配置联系人信息
            if (docsProperty.getContact() != null) {
                apiInfo.contact(new ApiContact()
                        .name(docsProperty.getContact().getName())
                        .url(docsProperty.getContact().getUrl())
                        .email(docsProperty.getContact().getEmail())
                );
            }

            docDocket.info(apiInfo)
                    // 配置扫描的包路径
                    .apis(groupConfig.getPackageName())
                    .globalResponseInData(true)
                    .globalResult(Result.class)
                    .globalResponseInData(true)
                    .globalResult(Result.class)
                    .schemes(Scheme.HTTP.toValue(), Scheme.HTTPS.toValue());

            //包装Bean（指定名字）
            BeanWrap beanWrap = Solon.context().wrap(DocDocket.class, docDocket);
            Solon.context().putWrap("DocDocket-" + groupConfig.getGroup(), beanWrap);
        });

    }


}
