package com.xivs.weblab44.logic;

import com.xivs.weblab44.model.Point;

public class PointFactory {

    private static boolean calculateHit(float x, float y, float r){
        if(x >= 0 && y >= 0) return 2*y + x <= r;
        else if (x >= 0) return x*x + y*y <= r*r/4;
        else return (y >= 0) && (y <= r) && (x >= -r/2);
    }
    public static Point getPoint(float x, float y, float r){
        Point point = new Point();
        point.setX(x);
        point.setY(y);
        point.setR(r);
        point.setHit(calculateHit(x, y, r));
        return point;
    }

}
