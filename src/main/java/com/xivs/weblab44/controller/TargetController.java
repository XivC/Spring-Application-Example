package com.xivs.weblab44.controller;

import com.xivs.weblab44.dto.ShotRequestDto;
import com.xivs.weblab44.logic.PointFactory;
import com.xivs.weblab44.model.Point;
import com.xivs.weblab44.model.User;
import com.xivs.weblab44.sequrity.jwt.JwtTokenProvider;
import com.xivs.weblab44.service.impl.PointService;
import com.xivs.weblab44.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/api/")
public class TargetController {
    @Autowired
    JwtTokenProvider jwtTokenProvider;

    @Autowired
    PointService pointService;

    @Autowired
    UserService userService;

    private boolean validateRequest(ShotRequestDto shotRequestDto){
        return !(shotRequestDto.getX() == null || shotRequestDto.getY() == null || shotRequestDto.getR() == null);

    }
    @PostMapping(value = "points", produces = "application/json")
    public ResponseEntity<?> getPoints(HttpServletRequest request){
        String token = jwtTokenProvider.resolveToken(request);
        if (token == null) return ResponseEntity.badRequest().body("Bad request");
        String username = jwtTokenProvider.getUsername(token);
        List<Point> points = pointService.getPointsByUser(userService.findByLogin(username));
        return ResponseEntity.ok(points);
    }

    @PostMapping(value = "shot", produces = "application/json")
    public ResponseEntity<?> performShot(@RequestBody ShotRequestDto shotRequestDto, HttpServletRequest request){
        if (!validateRequest(shotRequestDto)) return ResponseEntity.badRequest().body("Bad request");
        String token = jwtTokenProvider.resolveToken(request);
        if (token == null) return ResponseEntity.badRequest().body("Bad request");
        String username = jwtTokenProvider.getUsername(token);
        User user = userService.findByLogin(username);
        Point point = PointFactory.getPoint(shotRequestDto.getX(), shotRequestDto.getY(), shotRequestDto.getR());
        point.setUserId(user);
        pointService.savePoint(point);

        return ResponseEntity.ok(point);
    }
}
