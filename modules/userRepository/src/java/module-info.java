module userRepository {
    requires java.sql;

    exports com.userRepository.api;
    exports com.userRepository.applications.dto;
    exports com.userRepository.applications.exceptions;
}