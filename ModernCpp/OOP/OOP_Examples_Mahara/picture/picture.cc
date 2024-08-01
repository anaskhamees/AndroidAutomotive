#include <SFML/Graphics.hpp>
#include <iostream>

class Point {
private:
    int x;
    int y;

public:
    Point() : x(0), y(0) {}
    Point(int _x, int _y) : x(_x), y(_y) {}
    
    int getX() const { return x; }
    int getY() const { return y; }
};

class Line {
private:
    Point start;
    Point end;

public:
    Line() : start(), end() {}
    Line(int startX, int startY, int endX, int endY) : start(startX, startY), end(endX, endY) {}
    
    void draw(sf::RenderWindow &window) const {
        sf::Vertex line[] = {
            sf::Vertex(sf::Vector2f(start.getX(), start.getY())),
            sf::Vertex(sf::Vector2f(end.getX(), end.getY()))
        };
        window.draw(line, 2, sf::Lines);
    }
};

class Rectangle {
private:
    Point upperLeft;
    Point lowerRight;

public:
    Rectangle() : upperLeft(), lowerRight() {}
    Rectangle(int upperLeftX, int upperLeftY, int lowerRightX, int lowerRightY)
        : upperLeft(upperLeftX, upperLeftY), lowerRight(lowerRightX, lowerRightY) {}

    void draw(sf::RenderWindow &window) const {
        sf::RectangleShape rectangle(sf::Vector2f(
            lowerRight.getX() - upperLeft.getX(),
            lowerRight.getY() - upperLeft.getY()
        ));
        rectangle.setFillColor(sf::Color::Green);
        rectangle.setPosition(upperLeft.getX(), upperLeft.getY());
        
        window.draw(rectangle);
    }
};

class Picture {
private:
    int rectangleNum;
    int lineNum;
    Rectangle* pRect;
    Line* pLines;

public:
    Picture() : rectangleNum(0), lineNum(0), pRect(nullptr), pLines(nullptr) {}

    Picture(int rectNum, int lineNum, Rectangle* ptrRect, Line* ptrLine)
        : rectangleNum(rectNum), lineNum(lineNum), pRect(ptrRect), pLines(ptrLine) {}

    void setRects(int rect_num, Rectangle* pRectangle) {
        rectangleNum = rect_num;
        pRect = pRectangle;
    }

    void setLines(int line_num, Line* p_line) {
        lineNum = line_num;
        pLines = p_line;
    }

    void draw(sf::RenderWindow &window) const {
        for (int i = 0; i < rectangleNum; ++i) {
            pRect[i].draw(window);
        }
        for (int i = 0; i < lineNum; ++i) {
            pLines[i].draw(window);
        }
    }
};

int main() {
    sf::RenderWindow window(sf::VideoMode(800, 600), "SFML Drawing Example");

    // Define some lines and rectangles
    Line lines[] = {
        Line(100, 100, 200, 200),
        Line(200, 200, 300, 300)
    };
    Rectangle rectangles[] = {
        Rectangle(100, 100, 300, 200),
        Rectangle(150, 150, 400, 300)
    };

    Picture picture(2, 2, rectangles, lines);

    while (window.isOpen()) {
        sf::Event event;
        while (window.pollEvent(event)) {
            if (event.type == sf::Event::Closed)
                window.close();
        }

        window.clear();
        picture.draw(window); // Draw all lines and rectangles
        window.display();
    }

    return 0;
}

