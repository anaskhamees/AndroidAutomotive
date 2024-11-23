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
    
    void print()
    {std::cout<<" print line\n";}
};

class Rectangle {
private:
    Point upperLeft;
    Point lowerRight;

public:
    Rectangle() : upperLeft(), lowerRight() {}
    Rectangle(int upperLeftX, int upperLeftY, int lowerRightX, int lowerRightY)
        : upperLeft(upperLeftX, upperLeftY), lowerRight(lowerRightX, lowerRightY) {}

   void print()
    {std::cout<<" print rectangle\n";}
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

    void print()
    {std::cout<<" print picture\n";}
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

