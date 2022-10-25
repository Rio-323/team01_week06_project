package com.week06.team01_week06_project.controller;

import com.week06.team01_week06_project.dto.GlobalResDto;
import com.week06.team01_week06_project.dto.request.GamepostReqDto;
import com.week06.team01_week06_project.dto.request.PutGamepostReqDto;
import com.week06.team01_week06_project.dto.request.RecruitMemberDto;
import com.week06.team01_week06_project.security.UserDetailsImpl;
import com.week06.team01_week06_project.serevice.GamePostService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class GamePostController {

    private final GamePostService gamePostService;

    //게임모집글 작성
    @PostMapping("/gamepost")
    public GlobalResDto<?> generateGamePost(@AuthenticationPrincipal UserDetailsImpl userDetails,
                                            @RequestPart(value = "json") GamepostReqDto gamepostReqDto,
                                            @Valid @RequestPart("file") MultipartFile multipartFile) {
        Long memberid = userDetails.getAccount().getMemberId();
        return gamePostService.generateGamePost(memberid, gamepostReqDto, multipartFile);
    }

    //게임모집글 수정
    @PutMapping("/gamepost/{gamepostid}")
    public GlobalResDto<?> putGamePost(@AuthenticationPrincipal UserDetailsImpl userDetails,
                                       @RequestBody PutGamepostReqDto putGamepostReqDto,
                                       @PathVariable Long gamepostid) {
        return gamePostService.putGamePost(userDetails, putGamepostReqDto, gamepostid);
    }

    //게임모집글 삭제
    @DeleteMapping("/gamepost/{gamepostid}")
    public GlobalResDto<?> deleteGamePost(@PathVariable Long gamepostid, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return gamePostService.deleteGamePost(gamepostid, userDetails);
    }

    //모든 게임 모집글
    @GetMapping("/showpost")
    public GlobalResDto<?> getAllGamePost() {
        return gamePostService.getAllGamePost();
    }

    //모집글 1개
    @GetMapping("/showpost/{gamepostid}")
    public GlobalResDto<?> getGamePost(@PathVariable Long gamepostid) {
        return gamePostService.getGamePost(gamepostid);
    }

    @GetMapping("/showpost/search")
    public GlobalResDto<?> searchPost(@RequestParam(name = "gameName") String gameName) {
        return gamePostService.searchPost(gameName);
    }

}
