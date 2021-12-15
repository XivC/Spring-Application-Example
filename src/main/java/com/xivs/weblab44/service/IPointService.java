package com.xivs.weblab44.service;

import com.xivs.weblab44.model.Point;
import com.xivs.weblab44.model.User;
import com.xivs.weblab44.repository.PointRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public interface IPointService {

    List<Point> getPointsByUser(User user);
    void savePoint(Point point);
}
