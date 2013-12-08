package ca.ulaval.glo4003.config;

import javax.inject.Inject;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import ca.ulaval.glo4003.domain.match.MatchRepository;
import ca.ulaval.glo4003.domain.matchCatalog.MatchCatalog;
import ca.ulaval.glo4003.domain.matchCatalog.MatchCatalogFactory;
import ca.ulaval.glo4003.domain.matchCatalog.MatchQueryFactory;
import ca.ulaval.glo4003.domain.payment.CreditCardFactory;
import ca.ulaval.glo4003.domain.payment.TransactionManager;
import ca.ulaval.glo4003.domain.payment.TransactionService;
import ca.ulaval.glo4003.infrastructure.matchCatalog.JSONMatchCatalogFactory;
import ca.ulaval.glo4003.infrastructure.matchCatalog.JSONMatchQueryFactory;
import ca.ulaval.glo4003.service.mailsender.MailSender;
import ca.ulaval.glo4003.service.mailsender.SimpleMailMessageBuilder;
import ca.ulaval.glo4003.service.transaction.TransactionServiceStub;

@Configuration
public class AppConfig {

    @Inject
    MatchRepository matchRepository;

    @Bean
    public MatchCatalogFactory matchCatalogFactory() throws Exception {
        return new JSONMatchCatalogFactory(matchRepository);
    }

    @Bean
    public MatchCatalog matchCatalog() throws Exception {
        return matchCatalogFactory().createMatchCatalog();
    }

    @Bean
    public MatchQueryFactory matchQueryFactory() throws Exception {
        return new JSONMatchQueryFactory();
    }

    @Bean
    public TransactionService transactionService() throws Exception {
        return new TransactionServiceStub();
    }

    @Bean
    public TransactionManager transactionManager() throws Exception {
        CreditCardFactory creditCardFactory = new CreditCardFactory();
        return new TransactionManager(creditCardFactory);
    }

    @Bean
    public JavaMailSenderImpl mailServer() {
        JavaMailSenderImpl mailServer = new JavaMailSenderImpl();
        mailServer.setHost("smtp.gmail.com");
        mailServer.setPort(465);
        mailServer.setUsername("userglo4003@gmail.com");
        mailServer.setPassword("user4003");
        mailServer.setProtocol("smtps");

        return mailServer;
    }

    @Bean
    public MailSender mailSender() {
        SimpleMailMessageBuilder simpleMailMessageBuilder = new SimpleMailMessageBuilder();
        return new MailSender(simpleMailMessageBuilder);
    }
}
