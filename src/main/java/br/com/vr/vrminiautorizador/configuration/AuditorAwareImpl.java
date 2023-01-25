package br.com.vr.vrminiautorizador.configuration;

import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

public class AuditorAwareImpl implements AuditorAware<String> {
    private static final String DEFAULT_USER = "system";

    @Override
    public Optional<String> getCurrentAuditor() {
        return Optional.of(DEFAULT_USER);
    }
}
