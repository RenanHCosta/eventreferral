package renan.costa.eventreferral.services;

import com.mongodb.DuplicateKeyException;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import renan.costa.eventreferral.controllers.dto.MemberLeaderboardResponse;
import renan.costa.eventreferral.controllers.dto.MemberRegistrationRequest;
import renan.costa.eventreferral.controllers.dto.MemberRegistrationResponse;
import renan.costa.eventreferral.entities.EventMemberEntity;
import renan.costa.eventreferral.repositories.EventMemberRepository;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EventMemberService {

    private final EventMemberRepository eventMemberRepository;

    public EventMemberService(EventMemberRepository eventMemberRepository) {
        this.eventMemberRepository = eventMemberRepository;
    }

    public Page<MemberLeaderboardResponse> listMembers(Pageable pageable) {
        Sort sort = Sort.by("points").descending();

        Pageable pagingSort = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), sort);

        Page<EventMemberEntity> eventMemberPage = eventMemberRepository.findAll(pagingSort);

        List<MemberLeaderboardResponse> eventMemberDTOs = eventMemberPage.stream()
                .map(entity -> new MemberLeaderboardResponse(entity.getName(), entity.getPoints()))
                .toList();

        return new PageImpl<>(eventMemberDTOs, pagingSort, eventMemberPage.getTotalElements());
    }

    public MemberRegistrationResponse addMember(MemberRegistrationRequest registrationRequest) {

        String referralId;
        Optional<EventMemberEntity> search;

        do {
            referralId = RandomStringUtils.randomAlphanumeric(5, 10);
            search = eventMemberRepository.findByReferralId(referralId);
        } while (search.isPresent());

        var initialPoints = 0;

        if (registrationRequest.referralId() != null) {
            Optional<EventMemberEntity> optionalReferringMember = eventMemberRepository.findByReferralId(registrationRequest.referralId());

            if (optionalReferringMember.isPresent()) {
                EventMemberEntity referringMember = optionalReferringMember.get();

                referringMember.setPoints(referringMember.getPoints() + 1);
                eventMemberRepository.save(referringMember);

                initialPoints = 1;
            }
        }

        try {
            EventMemberEntity newMember = new EventMemberEntity(
                    null, // id (MongoDB will generate this automatically)
                    registrationRequest.email(),
                    registrationRequest.name(),
                    initialPoints,
                    referralId,
                    Instant.now()
            );

            EventMemberEntity savedMember = eventMemberRepository.save(newMember);
            return new MemberRegistrationResponse(savedMember.getName(), savedMember.getPoints(), savedMember.getReferralId());
        } catch (DuplicateKeyException e) {
            throw new IllegalArgumentException("Email already exists");
        }
    }
}
