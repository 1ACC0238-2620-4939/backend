package com.trakto.traktoroute.profiles.interfaces.rest.controllers;

import com.trakto.traktoroute.profiles.application.queries.GetAllProfilesQuery;
import com.trakto.traktoroute.profiles.application.queries.GetProfileByIdQuery;
import com.trakto.traktoroute.profiles.application.queries.GetProfileByUserReferenceIdQuery;
import com.trakto.traktoroute.profiles.application.services.ProfileQueryService;
import com.trakto.traktoroute.profiles.domain.model.valueobjects.ProfileId;
import com.trakto.traktoroute.profiles.domain.model.valueobjects.UserReferenceId;
import com.trakto.traktoroute.profiles.interfaces.rest.resources.responses.ProfileResponse;
import com.trakto.traktoroute.profiles.interfaces.rest.transform.ProfileResponseFromEntityAssembler;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/profiles")
@Tag(name = "Profile - Query",
        description = "Profile information queries")
public class ProfileQueryController {

    private final ProfileQueryService profileQueryService;

    public ProfileQueryController(
            ProfileQueryService profileQueryService
    ) {
        this.profileQueryService = profileQueryService;
    }

    @GetMapping("/{profileId}")
    @Operation(
            summary = "Get profile by ID",
            description = "Returns a profile using its unique identifier"
    )
    public ResponseEntity<ProfileResponse> getProfileById(
            @PathVariable("profileId") UUID profileId
    ) {
        var query = new GetProfileByIdQuery(
                new ProfileId(profileId)
        );

        var profile = profileQueryService.handle(query);

        var response =
                ProfileResponseFromEntityAssembler.toResourceFromEntity(
                        profile
                );

        return ResponseEntity.ok(response);
    }

    @GetMapping("/users/{userReferenceId}")
    @Operation(
            summary = "Get profile by user ID",
            description = "Returns the profile associated with an IAM user"
    )
    public ResponseEntity<ProfileResponse> getProfileByUserReferenceId(
            @PathVariable("userReferenceId") UUID userReferenceId
    ) {
        var query = new GetProfileByUserReferenceIdQuery(
                new UserReferenceId(userReferenceId)
        );

        var profile = profileQueryService.handle(query);

        var response =
                ProfileResponseFromEntityAssembler.toResourceFromEntity(
                        profile
                );

        return ResponseEntity.ok(response);
    }

    @GetMapping
    @Operation(
            summary = "Get all profiles",
            description = "Returns all registered profiles"
    )
    public ResponseEntity<List<ProfileResponse>> getAllProfiles() {
        var query = new GetAllProfilesQuery();

        var profiles = profileQueryService.handle(query);

        var response = profiles.stream()
                .map(ProfileResponseFromEntityAssembler::toResourceFromEntity)
                .toList();

        return ResponseEntity.ok(response);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String, String>> handleNotFound(
            IllegalArgumentException exception
    ) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Map.of(
                        "message",
                        exception.getMessage()
                ));
    }
}