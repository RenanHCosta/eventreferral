package renan.costa.eventreferral.controllers;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import renan.costa.eventreferral.controllers.dto.MemberLeaderboardResponse;
import renan.costa.eventreferral.controllers.dto.MemberRegistrationRequest;
import renan.costa.eventreferral.controllers.dto.MemberRegistrationResponse;
import renan.costa.eventreferral.services.EventMemberService;

@RestController
@RequestMapping("/event/members")
public class EventMemberController {
    private final EventMemberService memberService;

    public EventMemberController(EventMemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping
    public ResponseEntity<MemberRegistrationResponse> registerMember(@RequestBody MemberRegistrationRequest registrationRequest) {
        var memberResponse = memberService.addMember(registrationRequest);

        return ResponseEntity.ok(memberResponse);
    }

    @GetMapping
    public Page<MemberLeaderboardResponse> getLeaderboard(Pageable pageable) {
        return memberService.listMembers(pageable);
    }
}
