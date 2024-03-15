## log4j2主要由几个重要的组件构成
(1) 日志信息的优先级：由高到低为
    TRACE：
    DEBUG:
    INFO:
    WARN:
    ERROR:
    FATAL：
    级别搞得会自动屏蔽级别低的日志
    也就是说设置了WARN 那么INFO DEBUG 的日志级别的日志就不会显示