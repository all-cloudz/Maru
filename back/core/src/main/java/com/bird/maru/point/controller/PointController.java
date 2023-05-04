package com.bird.maru.point.controller;

import com.bird.maru.auth.service.dto.CustomUserDetails;
import com.bird.maru.point.service.PointService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/points")
@Slf4j
public class PointController {

    private final PointService pointService;

    /**
     * 랜드마크 방문 시 포인트 부여
     * */
    @PutMapping("/landmark/visit")
    public void landmarkVisiting(@AuthenticationPrincipal CustomUserDetails member) {

    }

    /**
     * 랜드마크 점유 시 포인트 부여
     * */
    @PutMapping("/landmark/occupy")
    public void landmarkOccupying(@AuthenticationPrincipal CustomUserDetails member) {

    }

    /**
     * 랜드마크에 사진 등록 시 포인트 부여
     * */
    @PutMapping("landmark/photo")
    public void landmarkPhoto(@AuthenticationPrincipal CustomUserDetails member) {

    }

    /**
     * 스팟 생성 시 포인트 부여
     * */
    @PutMapping("/spot")
    public void spotMaking(@AuthenticationPrincipal CustomUserDetails member) {

    }

    /**
     * 사진 좋아요 받을 시 포인트 부여
     * */
    @PutMapping("/like")
    public void photoLike(@AuthenticationPrincipal CustomUserDetails member) {

    }

}
