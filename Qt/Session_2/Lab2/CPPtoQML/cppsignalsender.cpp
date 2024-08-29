#include "cppsignalsender.h"

CppSignalSender::CppSignalSender(QObject *parent):QObject(parent),m_value(0)
{
    m_timer=new QTimer(this);
    connect(m_timer,&QTimer::timeout,this,[=](){
        ++m_value;
        emit cppToQML((QString::number(m_value)));
    }) ;
}
