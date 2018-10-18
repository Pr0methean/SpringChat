module messageRepository {
    requires java.sql;

    exports com.messageRepository.api;
    exports com.messageRepository.applications.dto;
    exports com.messageRepository.domain.port;

    requires java.naming;

}