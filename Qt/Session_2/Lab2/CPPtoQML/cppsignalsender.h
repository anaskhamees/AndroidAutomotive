#ifndef CPPSIGNALSENDER_H
#define CPPSIGNALSENDER_H
#include <QObject>
#include <QTimer>
class CppSignalSender:public QObject
{
    Q_OBJECT
public:
    explicit CppSignalSender(QObject *parent=nullptr);
    int m_value;
    QTimer *m_timer;
signals:
    void cppToQML(QString value);
};

#endif // CPPSIGNALSENDER_H
