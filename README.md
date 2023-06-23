# spring-security6-proc

## AWS-EC2에서
- UTC -> KST
```
[ec2-user@ip-172-31-11-94 ~]cd /etc
[ec2-user@ip-172-31-11-94 etc]$ ll local*
-rw-r--r-- 1 root root  97 Jun 14 07:10 locale.conf
-rw-r--r-- 1 root root 127 Jun  2 18:21 localtime
[ec2-user@ip-172-31-11-94 etc]$ rm localtime 
[ec2-user@ip-172-31-11-94 etc]$ sudo ln -s /usr/share/zoneinfo/Asia/Seoul  /etc/localtime
[ec2-user@ip-172-31-11-94 etc]$ ll local*
-rw-r--r-- 1 root root 97 Jun 14 16:10 locale.conf
lrwxrwxrwx 1 root root 30 Jun 15 11:29 localtime -> /usr/share/zoneinfo/Asia/Seoul
[ec2-user@ip-172-31-11-94 etc]$ date
Thu Jun 15 11:29:40 KST 2023
```

-80->8080 prot  
```
[ec2-user@ip-172-31-11-94 ~]$ sudo iptables -A PREROUTING -t nat -i eth0 -p tcp --dport 80 -j REDIRECT --to-port 8080
[ec2-user@ip-172-31-11-94 ~]$ sudo iptables -t nat -L
```

# 쉘 스크립트 작성
- 실행중인 jar 프로젝트 종료 파일 생성
- vi stop
```
#!/bin/bash

JAR_PID=`ps -ef|grep java|grep -v grep|awk '{print $2}'`
JAR_CNT=`ps -ef|grep java|grep -v grep|wc -l`
DATE=`date +"[ %Y-%m-%d %H:%M:%S ]"`
DEPLOY_LOG="/home/ec2-user/script/deploy.log"

if [ $JAR_CNT -gt 0 ]
then
        sudo kill -TERM $JAR_PID
        echo "$DATE : jar file stop (PID : $JAR_PID)" >> $DEPLOY_LOG
else
        echo "$DATE : no jar file" >> $DEPLOY_LOG

fi
```
- 실행권한 부여 : chmod +x 파일명
```
chmod +x stop
./stop
```

- vi start
```
#!/bin/bash

#JAR_PID=`ps -ef|grep java|grep -v grep|awk '{print $2}'`
JAR_CNT=`ps -ef|grep java|grep -v grep|wc -l`
DATE=`date +"[ %Y-%m-%d %H:%M:%S ]"`
DEPLOY_LOG="/home/ec2-user/script/deploy.log"
JAR_FILE=/home/ec2-user/myweb/*.jar

if [ $JAR_CNT -eq 0 ]
then
        nohup java -jar $JAR_FILE &
        echo "$DATE : jar file start " >> $DEPLOY_LOG
else
        echo "$DATE : jar file is already running" >> $DEPLOY_LOG

fi
```
