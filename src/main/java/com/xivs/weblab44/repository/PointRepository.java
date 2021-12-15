package com.xivs.weblab44.repository;

import com.xivs.weblab44.model.Point;
import com.xivs.weblab44.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PointRepository extends JpaRepository<Point, Long> {
    List<Point> getAllByUserId(User user);
}
