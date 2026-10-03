package com.trakto.traktoroute.profiles.interfaces.rest.controllers;

import com.trakto.traktoroute.shared.interfaces.rest.transform.ResponseEntityAssembler;
import com.trakto.traktoroute.profiles.application.services.ProfileCommandService;
import com.trakto.traktoroute.profiles.interfaces.rest.resources.requests.ChangeProfileFullNameRequest;
import com.trakto.traktoroute.profiles.interfaces.rest.resources.requests.CreateProfileRequest;
import com.trakto.traktoroute.profiles.interfaces.rest.transform.ChangeProfileFullNameCommandFromRequestAssembler;
import com.trakto.traktoroute.profiles.interfaces.rest.transform.CreateProfileCommandFromRequestAssembler;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/profiles")
@Tag(name = "Profile - Command",
        description = "Profile management")
public class ProfileCommandController {

    private final ProfileCommandService profileCommandService;

    public ProfileCommandController(
            ProfileCommandService profileCommandService
    ) {
        this.profileCommandService = profileCommandService;
    }

    @PostMapping
    @Operation(
            summary = "Create a profile",
            description = "Creates a profile associated with an IAM user"
    )
    public ResponseEntity<?> createProfile(
            @Valid @RequestBody CreateProfileRequest request
    ) {
        var command =
                CreateProfileCommandFromRequestAssembler.toCommand(request);

        var result = profileCommandService.handle(command);

        return ResponseEntityAssembler.toResponseEntityFromResult(
                result,
                profileId -> Map.of("profileId", profileId.value()),
                HttpStatus.CREATED
        );
    }

    @PatchMapping("/{profileId}/full-name")
    @Operation(
            summary = "Change a profile full name",
            description = "Changes the given names and surnames of a profile"
    )
    public ResponseEntity<?> changeFullName(
            @PathVariable("profileId") UUID profileId,
            @Valid @RequestBody ChangeProfileFullNameRequest request
    ) {
        var command =
                ChangeProfileFullNameCommandFromRequestAssembler.toCommand(
                        profileId,
                        request
                );

        var result = profileCommandService.handle(command);

        return ResponseEntityAssembler.toResponseEntityFromResult(
                result,
                ignored -> null,
                HttpStatus.NO_CONTENT
        );
    }
}