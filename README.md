# [Dataus] Deverse

개발자 커뮤니티 Deverse 구축 가이드 <br>

# 개발 환경
* Ubuntu/20.x (OS)
* mariadb/10.4.18 (DB Service)
* tomcat/9.0.45 (WAS Service) 
* nginx/1.18.0 (WEB Service)
* OpenJDK/1.8.x
* Maven/3.8.1
* NodeJS/14.6.1

# Docker 를 이용하여 사용해 보기
`* 설치 내용은 /install 폴더를 참고 하시기 바랍니다.`
<br>
A. Docker Images 만들기(Tomcat, Mariadb)
1. Dockerfile 을 이용하여 Mariadb 이미지 만들기
   - /install/mariadb/db_user.sql (DB 계정 스크립트)
   - /install/mariadb/devers.sql (DB 스키마 스크립트)
   - /install/mariadb/dockerfile (Docker Image 스크립트)
2. Dockerfile Build 방법
   - /install/mariadb 의 폴더에 dockerfile 을 확인 후 아래 명령어 실행<br>
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

D. NHN Cloud Kubernetes 로 사용하기
1. NHN Cloud 회원 가입 및 Console 로그인 : http://toast.com
2. NHN Cloud Console 에 Kubernetes Manager Instance 생성
   - NHN Cloud Console 접속 -> Compute -> Instance -> 인스턴스 생성
   - 이미지(Ubuntu Server 20.04 LTS) -> 인스턴스 이름(kube-manager) -> 인스턴스 타입(Standard : t2.c1m1) -> 키 페어 선택(생성 or 기존에 사용하던것)
     -> 블록 스토리지 타입(HDD or SDD) -> 블록 스토리지 크기(50~100GB) -> 인스턴스 생성
3. NHN Cloud Console 에서 Kubernetes 생성 
   - NHN Cloud Console 접속 -> Container -> Kubernetes -> 클러스터 생성
   - 클러스터 이름(kube-master) -> 인스턴스 타입(Standard : m2.c8m16) -> 노드 수(2~3개) -> 키 페어 선택(생성 or 기존에 사용하던것)
     -> 블록 스토리지 타입(HDD or SDD) -> 블록 스토리지 크기(50~100GB) -> 오토 스케일러(사용 or 사용 안 함) -> 클러스터 생성
4. NHN Cloud Console 에서 SSH 접속 설정
   - NHN Cloud Console 접속 -> Network -> Security Groups -> Default 선택 -> 보안정책 생성 -> 포트: 22, 원격 공인IP 입력(ex 포트: 22, CIDR: 111.111.111.111/32) 후 확인
   - 자세한 사항은 https://docs.toast.com/ko/Compute/Instance/ko/overview/#linux (SSH 접속 방법) 참고
5. NHN Cloud 에서 생성된 Instance(Kube-manager) 접속 방법 및 Kubernetes 연결 방법
   - https://doc.skill.or.kr/nhn-cloud#5-2-kube-manager-api 에서 NHN Cloud Kubernetes API 설정 방법
6. NHN Cloud 에서 Kubernetes 를 이용하여 서비스 사용하기
   - /install/1.kubernetes_mariadb+service.yaml (mariadb Pod/Service 등 설정 값)
     -> kubectl apply -f 1.kubernetes_mariadb+service.yaml (mariadb pod 와 Service 실행) -> kubectl get pod -n project -o wide (Mariaddb IP 확인)
   - /install/2.kubernetes_nginx+tomcat.yaml (tomcat Pod 설정 값. 아래의 Build 정보 수정)
     -> vi 2.kubernetes_nginx+tomcat.yaml (- ip: "10.100.4.9" 의 값을 Mariadb IP 로 수정) -> kubectl apply -f 2.kubernetes_nginx+tomcat.yaml (Nginx 와 Tomcat Pod 실행) 
   - Build 설정 파일
     - nginx+tomcat Server 접속
     - /home/dev/build/application.yml 에 DB 접속 정보 설정
     - /home/dev/deverse/frontend/.env.build 에 url 접속 정보 설정
 
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
