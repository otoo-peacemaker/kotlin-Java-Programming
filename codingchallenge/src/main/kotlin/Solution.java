
import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;

class Solution {
    public int solution( int[] X, int[] Y, String S) {
        PriorityQueue<Point> priorityQueue = new PriorityQueue(Y.length, Comparator.comparingDouble(Point::distance));
        for (int i = 0; i < X.length; i++) {
            priorityQueue.add(new Point(X[i], Y[i], S.charAt(i)));
        }

        HashMap<Character, Point> map = new HashMap<>();

        while(map.size() != X.length) {
            Point point = priorityQueue.poll();
            assert point != null;
            if (map.containsKey(point.getTag())) {
                Point firstPoint = map.get(point.getTag());
                return Double.compare(firstPoint.distance(), point.distance()) == 0 ? map.size() - 1 : map.size();
            } else {
                map.put(point.getTag(), point);
            }
        }

        return map.size();
    }
}