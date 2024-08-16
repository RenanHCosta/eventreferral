package renan.costa.eventreferral.controllers.dto;

import jakarta.annotation.Nullable;

public record MemberRegistrationRequest(String email, String name, @Nullable String referralId) {
}
