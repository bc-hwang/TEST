# [Dataus] Deverse

개발자 커뮤니티 Deverse 구축 가이드 <br>

# 개발 환경
* Ubuntu 20.x (OS)
* mariadb 10.4.18 (DB Service)
* tomcat 9.0.45 (WAS Service) 
* OpenJDK 1.8.x
* Maven 3.8.1
* NodeJS 14.6.1

# Docker 를 이용하여 사용해 보기
`* 설치 내용은 /install 폴더를 참고 하시기 바랍니다.`
<br>
A. Docker Images 만들기(Tomcat, Mariadb)
1. Dockerfile 을 이용하여 Mariadb 이미지 만들기
   - /install/mariadb/db_user.sql (DB 계정 스크립트)
   - /install/mariadb/devers.sql (DB 스키마 스크립트)
   - /install/mariadb/dockerfile (Docker Image 스크립트)
2. Dockerfile Build 방법
   - /install/mariadb 의 폴더에 dockerfile 을 혹인 후 아래 명령어 실행<br>
     -> docker build -t tomcat_mariadb:mariadb .
   - docker images (tomcat_mariadb:mariadb 이미지 확인)
3. Dockerfile 을 이용하여 tomcat 이미지 만들기
   - /install/tomcat/apache-maven-3.8.1.tar.gz (maven src 파일)
   - /install/tomcat/tomcat-9.0.45.tar.gz (tomcat src 파일)
   - /install/tomcat/build.tar.gz (src build 파일)
   - /install/tomcat/dockerfile (Docker Image 스크립트)
4. Dockerfile Build 하는 방법
   - /install/tomcat 의 폴더에 dockerfile 을 혹인 후 아래 명령어 실행<br>
     -> docker build -t tomcat_mariadb:tomcat .
   - docker images (tomcat_mariadb:tomcat 이미지 확인)
5. Docker Hub push 하는 방법
   - docker login 을 함.
   - docker tag tomcat_mariadb:tomcat bchwang/tomcat_mariadb:tomcat (tag 설정)
   - docker tag tomcat_mariadb:mariadb bchwang/tomcat_mariadb:mariadb (tag 설정)
   - docker push bchwang/tomcat_mariadb:tomcat (docker hub 에 업로드 진행)
   - docker push bchwang/tomcat_mariadb:mariadb (docker hub 에 업로드 진행)
<br>

B. Build 된 Tomcat, Mariadb Images 실행 하기
1. docker images 확인 하기
   - docker images
2. Local Image 로 실행 하기
   - docker run -d --name mariadb tomcat_mariadb:mariadb
   - docker run -d --name tomcat -p 80:80 --link mariadb tomcat_mariadb:tomcat
3. docker Hub Image 로 실행 하기
   - docker run -d --name mariadb bchwang/tomcat_mariadb:mariadb
   - docker run -d --name tomcat -p 80:80 --link mariadb bchwang/tomcat_mariadb:tomcat
<br>

C. Docker-Compose 사용하기
1. Local Images 로 실행 하기
   - /install/docker-compose_local.yml (docker-compose 설정 값 Local Images 사용)
2. Docker Hub 에 Push 한 Images 로 실행 하기
   - /install/docker-compose.yml (docker-compose 설정 값 Docker Hub Images 사용)
<br>


Build 설정 파일<br>
 build/application.yml 에 DB 접속 정보 설정<br>
 deverse/frontend/.env.build 에 url 접속 정보 설정<br>
 
# 참고 자료
[Dataus] NHN Cloud Docker & Kubernetes GitBook<br> 
* http://doc.skill.or.kr (빠른 개발을 위한 docker 활용법(5분 완성))<br>
* https://doc.skill.or.kr/docker (시스템 별 도커(docker) 설치 방법) <br>
* https://doc.skill.or.kr/docker-2 (Lean Startup 을 위한 docker 사용법)<br>
* https://doc.skill.or.kr/windows (Windows 컨테이너 실행 가이드)<br>
* https://doc.skill.or.kr/nhn-cloud (NHN Cloud Kubernetes 활용법)<br>
* https://doc.skill.or.kr/kubernetes-opensource (NHN Cloud Kubernetes 관리용 OpenSource 설치 방법)<br>
* https://doc.skill.or.kr/nhn-cloud-kubernetes-loadbalancer (TIPs. NHN Cloud Kubernetes 의 LoadBalancer 사용법)<br>
* https://www.slideshare.net/DataUs/dataus ([DataUs]클라우드 입문자를 위한 보안 가이드)<br>
