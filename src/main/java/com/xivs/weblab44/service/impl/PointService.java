package com.xivs.weblab44.service.impl;

import com.xivs.weblab44.logic.PointFactory;
import com.xivs.weblab44.model.Point;
import com.xivs.weblab44.model.User;
import com.xivs.weblab44.repository.PointRepository;
import com.xivs.weblab44.service.IPointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PointService implements IPointService {
    @Autowired
    private PointRepository pointRepository;

    @Override
    public List<Point> getPointsByUser(User user) {
        return pointRepository.getAllByUserId(user);
    }

    @Override
    public void savePoint(Point point) {
        pointRepository.save(point);
    }
}
