package com.app.roomify.controller;

import com.app.roomify.repository.domain.Member;
import com.app.roomify.controller.response.MemberResponse;
import com.app.roomify.exception.RoomifyException;
import com.app.roomify.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/members")
public class MemberController {

    private final MemberService memberService;
    private final ConversionService conversionService;

    @GetMapping
    public ResponseEntity<List<MemberResponse>> getAllMembers() {
        try {
            List<Member> members = memberService.getAllMembers();
            List<MemberResponse> memberResponses = members.stream()
                    .map(member -> conversionService.convert(member, MemberResponse.class))
                    .collect(Collectors.toList());
            return ResponseEntity.ok(memberResponses);
        } catch (RoomifyException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<MemberResponse> getMemberById(@PathVariable int id) {
        try {
            Member member = memberService.getMemberById(id);
            MemberResponse memberResponse = conversionService.convert(member, MemberResponse.class);
            return ResponseEntity.ok(memberResponse);
        } catch (RoomifyException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping
    public ResponseEntity<MemberResponse> createMember(@Valid @RequestBody MemberResponse memberResponse) {
        try {
            Member member = conversionService.convert(memberResponse, Member.class);
            member = memberService.saveMember(member);
            MemberResponse response = conversionService.convert(member, MemberResponse.class);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (RoomifyException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<MemberResponse> updateMember(
            @PathVariable int id,
            @Valid @RequestBody MemberResponse memberResponse) {
        try {
            memberResponse.setId(id);
            Member member = conversionService.convert(memberResponse, Member.class);
            member = memberService.saveMember(member);
            MemberResponse response = conversionService.convert(member, MemberResponse.class);
            return ResponseEntity.ok(response);
        } catch (RoomifyException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMember(@PathVariable int id) {
        try {
            memberService.deleteMember(id);
            return ResponseEntity.noContent().build();
        } catch (RoomifyException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/search")
    public ResponseEntity<List<MemberResponse>> searchMembersByName(@RequestParam String name) {
        try {
            List<Member> members = memberService.searchMembersByName(name);
            List<MemberResponse> memberResponses = members.stream()
                    .map(member -> conversionService.convert(member, MemberResponse.class))
                    .collect(Collectors.toList());
            return ResponseEntity.ok(memberResponses);
        } catch (RoomifyException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/{followerId}/follow/{followeeId}")
    public ResponseEntity<Void> followMember(@PathVariable int followerId, @PathVariable int followeeId) {
        try {
            memberService.followMember(followerId, followeeId);
            return ResponseEntity.ok().build();
        } catch (RoomifyException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @DeleteMapping("/{followerId}/unfollow/{followeeId}")
    public ResponseEntity<Void> unfollowMember(@PathVariable int followerId, @PathVariable int followeeId) {
        try {
            memberService.unfollowMember(followerId, followeeId);
            return ResponseEntity.ok().build();
        } catch (RoomifyException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping("/{id}/followers")
    public ResponseEntity<List<MemberResponse>> getFollowers(@PathVariable int id) {
        try {
            List<Member> followers = memberService.getFollowers(id);
            List<MemberResponse> responses = followers.stream()
                    .map(member -> conversionService.convert(member, MemberResponse.class))
                    .collect(Collectors.toList());
            return ResponseEntity.ok(responses);
        } catch (RoomifyException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/{id}/following")
    public ResponseEntity<List<MemberResponse>> getFollowing(@PathVariable int id) {
        try {
            List<Member> following = memberService.getFollowing(id);
            List<MemberResponse> responses = following.stream()
                    .map(member -> conversionService.convert(member, MemberResponse.class))
                    .collect(Collectors.toList());
            return ResponseEntity.ok(responses);
        } catch (RoomifyException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping("/{id}/activate")
    public ResponseEntity<Void> activateMember(@PathVariable int id) {
        try {
            memberService.activateMember(id);
            return ResponseEntity.ok().build();
        } catch (RoomifyException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping("/{id}/deactivate")
    public ResponseEntity<Void> deactivateMember(@PathVariable int id) {
        try {
            memberService.deactivateMember(id);
            return ResponseEntity.ok().build();
        } catch (RoomifyException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
