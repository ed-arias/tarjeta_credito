package com.co.scotiabank.tarjeta_credito.config;

import org.drools.decisiontable.DecisionTableProviderImpl;
import org.kie.api.KieServices;
import org.kie.api.builder.KieBuilder;
import org.kie.api.builder.KieFileSystem;
import org.kie.api.builder.KieModule;
import org.kie.api.builder.KieRepository;
import org.kie.api.builder.ReleaseId;
import org.kie.api.io.Resource;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.internal.builder.DecisionTableConfiguration;
import org.kie.internal.builder.DecisionTableInputType;
import org.kie.internal.builder.KnowledgeBuilderFactory;
import org.kie.internal.io.ResourceFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
public class DroolsConfig {

    private KieServices kieServices = KieServices.Factory.get();
    private final String ruleFilePath = "scotiabank/brms/Rules.xls";

    private KieFileSystem getKieFileSystem() {
        log.info("Obteniendo instancia de Kie File System...");
        KieFileSystem kieFileSystem = kieServices.newKieFileSystem();
        log.info("Reglas DRL desde XLS:\n" + getDrlFromExcel(ruleFilePath));
        kieFileSystem.write(ResourceFactory.newClassPathResource(ruleFilePath));
        return kieFileSystem;
    }

    @Bean
    public KieContainer getKieContainer() {
        log.info("Creando Kei Container...");
        getKieRepository();
        KieBuilder kieBuilder = kieServices.newKieBuilder(getKieFileSystem());
        kieBuilder.buildAll();
        KieModule kieModule = kieBuilder.getKieModule();
        KieContainer kieContainer = kieServices.newKieContainer(kieModule.getReleaseId());
        return kieContainer;
    }

    private void getKieRepository() {
        final KieRepository kieRepository = kieServices.getRepository();
        kieRepository.addKieModule(new KieModule() {
            public ReleaseId getReleaseId() {
                return kieRepository.getDefaultReleaseId();
            }
        });
    }

    @Bean
    public KieSession getKieSession() {
        log.info("Sesión creada...");
        return getKieContainer().newKieSession();
    }

    public String getDrlFromExcel(String excelFile) {
        DecisionTableConfiguration configuration = KnowledgeBuilderFactory.newDecisionTableConfiguration();
        configuration.setInputType(DecisionTableInputType.XLS);
        Resource dt = ResourceFactory.newClassPathResource(excelFile, getClass());
        DecisionTableProviderImpl decisionTableProvider = new DecisionTableProviderImpl();
        String drl = decisionTableProvider.loadFromResource(dt, configuration);
        return drl;
    }
}
